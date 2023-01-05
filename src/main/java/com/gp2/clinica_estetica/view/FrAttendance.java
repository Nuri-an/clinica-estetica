/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.view;

import com.gp2.clinica_estetica.controller.AppointmentController;
import com.gp2.clinica_estetica.controller.AttendanceController;
import com.gp2.clinica_estetica.controller.PeopleController;
import com.gp2.clinica_estetica.controller.ProcedureController;
import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.MedicalProcedure;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import com.gp2.clinica_estetica.model.exceptions.ProcedureException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author darloonlino
 */
public class FrAttendance extends javax.swing.JFrame {

    private boolean isNewProcedure;
    private List<MedicalProcedure> procedures;
    private Attendance attendance;
    private AttendanceController attendanceCon;
    private String mode; // create | edit
    private String type; // Avaliacao | Consulta
    private User user;
    private JFrame previusScreen;

    /**
     * Creates new form FrAttendance
     */
    public FrAttendance() {
        initialize();
    }

    /**
     * Creates new form FrAttendance
     *
     * @param previusScreen
     * @param mode
     * @param user
     */
    public FrAttendance(JFrame previusScreen, String mode, User user) {
        initialize();
        
        this.user = user;
        this.previusScreen = previusScreen;

        if (mode.equals("edit")) {
            this.setFieldsEnabled(false);
            fieldStartSection.setEnabled(true);
            fieldEndSection.setEnabled(true);
        }

        this.mode = mode;
    }

    /**
     * Creates new form FrAttendance
     * Initialize to edit attendance
     *
     * @param mode
     * @param type
     * @param id
     * @param user
     */
    public FrAttendance(String mode, String type, Integer id, User user) {
        initialize();
        
        this.user = user;

        if (mode.equals("edit")) {
            Attendance attendanceEdit = this.attendanceCon.onFind(id);
            if (attendanceEdit != null) {
                this.attendance = attendanceEdit;
                fieldCpfPatient.setText(attendanceEdit.getPatient().getPeople().getCPF());
                fieldCpfDoctor.setText(attendanceEdit.getDoctor().getPeople().getCPF());
                jComboProcedure.setSelectedItem(attendanceEdit.getProcedure().getName());
                fieldProcedurePrice.setText(attendanceEdit.getProcedure().getPrice().toString().replaceAll("\\.", ","));

                this.setInitialDates(attendanceEdit.getStartDateTime(), attendanceEdit.getEndDateTime());
                fieldFinally.setText(attendanceEdit.getFinality());

                this.setFieldsEnabled(false);
                fieldStartSection.setEnabled(true);
                fieldEndSection.setEnabled(true);

                if (type.equals("Consulta")) {
                    fieldNumSections.setText(attendanceEdit.getAppointment().getNumberOfSessions().toString());
                }
            }
        }

        if (type.equals("Avaliacao")) {
            labelNumSections.setVisible(false);
            fieldNumSections.setVisible(false);

            groupAttendanceType.setSelected(optAttendanceTypeA.getModel(), true);
        } else {
            groupAttendanceType.setSelected(optAttendanceTypeB.getModel(), true);
        }

        this.mode = mode;
        this.type = type;
    }

    /**
     * Creates new form FrAttendance
     * Initialize to initial dates defined
     *
     * @param mode
     * @param type
     * @param startDate
     * @param endDate
     * @param user
     */
    public FrAttendance(String mode, String type, Calendar startDate, Calendar endDate, User user) {
        initialize();

        this.user = user;
        
        if (mode.equals("create")) {
            this.setInitialDates(startDate, endDate);
        }

        if (type.equals("Avaliacao")) {
            labelNumSections.setVisible(false);
            fieldNumSections.setVisible(false);

            groupAttendanceType.setSelected(optAttendanceTypeA.getModel(), true);
        } else {
            groupAttendanceType.setSelected(optAttendanceTypeB.getModel(), true);
        }

        this.mode = mode;
        this.type = type;
    }

    public void initialize() {
        initComponents();

        this.attendanceCon = new AttendanceController();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        btnAddPatient.setOpaque(false);
        btnAddPatient.setContentAreaFilled(false);
        btnAddPatient.setBorderPainted(false);

        setMasks();

        fieldProcedurePrice.setEnabled(false);

        groupAttendanceType.setSelected(optAttendanceTypeA.getModel(), true);

        jComboProcedure.enableInputMethods(true);
        labelNewProcedureFeedback.setVisible(false);
        jComboProcedure.getEditor().getEditorComponent().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jComboProcedure.showPopup();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //To Do Focus Lost
            }
        });

        ProcedureController procedureCon = new ProcedureController();
        Vector procedures = new Vector();
        this.procedures = procedureCon.onFindAll();
        procedures.addAll(this.procedures);
        jComboProcedure.setModel(new DefaultComboBoxModel(procedures));

        if (this.procedures.size() > 0) {
            fieldProcedurePrice.setText("R$ " + this.procedures.get(0).getPrice());
        }
    }

    public void setInitialDates(Calendar startDate, Calendar endDate) {
        String dt = "";
        int day = startDate.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            dt = dt + "0" + day;
        } else {
            dt = dt + day;
        }
        int month = startDate.get(Calendar.MONTH) + 1;
        if (month < 10) {
            dt = dt + "0" + month;
        } else {
            dt = dt + month;
        }
        dt = dt + startDate.get(Calendar.YEAR);
        int hour = startDate.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            dt = dt + "0" + hour;
        } else {
            dt = dt + hour;
        }
        int minute = startDate.get(Calendar.MINUTE);
        if (minute < 10) {
            dt = dt + "0" + minute;
        } else {
            dt = dt + minute;
        }

        String dt2 = "";
        day = endDate.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            dt2 = dt2 + "0" + day;
        } else {
            dt2 = dt2 + day;
        }
        month = endDate.get(Calendar.MONTH) + 1;
        if (month < 10) {
            dt2 = dt2 + "0" + month;
        } else {
            dt2 = dt2 + month;
        }
        dt2 = dt2 + endDate.get(Calendar.YEAR);
        hour = endDate.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            dt2 = dt2 + "0" + hour;
        } else {
            dt2 = dt2 + hour;
        }
        minute = endDate.get(Calendar.MINUTE);
        if (minute < 10) {
            dt2 = dt2 + "0" + minute;
        } else {
            dt2 = dt2 + minute;
        }

        fieldStartSection.setText(dt);
        fieldEndSection.setText(dt2);
    }

    public void setFieldsEnabled(Boolean flag) {
        optAttendanceTypeA.setEnabled(flag);
        optAttendanceTypeB.setEnabled(flag);
        fieldCpfPatient.setEnabled(flag);
        fieldCpfDoctor.setEnabled(flag);
        jComboProcedure.setEnabled(flag);
        fieldProcedurePrice.setEnabled(flag);
        fieldStartSection.setEnabled(flag);
        fieldEndSection.setEnabled(flag);
        fieldFinally.setEnabled(flag);
        btnAddPatient.setEnabled(flag);
        fieldNumSections.setEnabled(flag);
    }

    public void setMasks() {
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(fieldCpfPatient);
            MaskFormatter maskCpf1 = new MaskFormatter("###.###.###-##");
            maskCpf1.install(fieldCpfDoctor);

            fieldProcedurePrice.setText("R$ ");
            /**
             * MaskFormatter maskPrice = new MaskFormatter();
             * maskPrice.setValidCharacters("123456789.,");
             * maskPrice.install(fieldProcedurePrice); NumberFormat format =
             * NumberFormat.getCurrencyInstance(); NumberFormatter nff = new
             * NumberFormatter(format); DefaultFormatterFactory factory = new
             * DefaultFormatterFactory(nff);
             * fieldProcedurePrice.setFormatterFactory(factory);
             */
            MaskFormatter maskDateTime = new MaskFormatter("##/##/#### - ##:##");
            maskDateTime.install(fieldStartSection);

            MaskFormatter maskDateTime1 = new MaskFormatter("##/##/#### - ##:##");
            maskDateTime1.install(fieldEndSection);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar máscara nos campos");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupAttendanceType = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAddPatient = new javax.swing.JButton();
        fieldCpfPatient = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fieldCpfDoctor = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldStartSection = new javax.swing.JFormattedTextField();
        fieldEndSection = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboProcedure = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fieldFinally = new javax.swing.JTextArea();
        fieldProcedurePrice = new javax.swing.JFormattedTextField();
        labelNewProcedureFeedback = new javax.swing.JLabel();
        labelNumSections = new javax.swing.JLabel();
        fieldNumSections = new javax.swing.JFormattedTextField();
        optAttendanceTypeA = new javax.swing.JRadioButton();
        optAttendanceTypeB = new javax.swing.JRadioButton();
        btnBack = new javax.swing.JButton();
        btnSchedule = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Novo agendamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Paciente"));

        jLabel2.setText("CPF Paciente");

        btnAddPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/plus.png"))); // NOI18N
        btnAddPatient.setText("Cadastrar Paciente");
        btnAddPatient.setBorder(null);
        btnAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatientActionPerformed(evt);
            }
        });

        fieldCpfPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCpfPatientActionPerformed(evt);
            }
        });
        fieldCpfPatient.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fieldCpfPatientPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldCpfPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddPatient))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldCpfPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddPatient)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Doutor"));

        jLabel3.setText("CPF Doutor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCpfDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldCpfDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Procedimento"));

        jLabel4.setText("Preço do procedimento:");

        jLabel5.setText("Inicio da sessão:");

        fieldStartSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldStartSectionActionPerformed(evt);
            }
        });

        fieldEndSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEndSectionActionPerformed(evt);
            }
        });

        jLabel6.setText("Fim da sessão:");

        jComboProcedure.setEditable(true);
        jComboProcedure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboProcedure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboProcedureMousePressed(evt);
            }
        });
        jComboProcedure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboProcedureActionPerformed(evt);
            }
        });

        jLabel8.setText("Finalidade / Objetivo do tratamento");

        fieldFinally.setColumns(20);
        fieldFinally.setRows(5);
        jScrollPane1.setViewportView(fieldFinally);

        fieldProcedurePrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));

        labelNewProcedureFeedback.setBackground(new java.awt.Color(51, 51, 51));
        labelNewProcedureFeedback.setFont(new java.awt.Font("Dialog", 2, 9)); // NOI18N
        labelNewProcedureFeedback.setText("Um novo procedimento será criado");

        labelNumSections.setText("N° de sessões");

        fieldNumSections.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldStartSection, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldEndSection, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelNewProcedureFeedback)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboProcedure, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldProcedurePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNumSections)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldNumSections, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboProcedure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldNumSections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNumSections))
                    .addComponent(jLabel4)
                    .addComponent(fieldProcedurePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNewProcedureFeedback)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldStartSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(fieldEndSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        groupAttendanceType.add(optAttendanceTypeA);
        optAttendanceTypeA.setText("Avaliação");
        optAttendanceTypeA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAttendanceTypeAActionPerformed(evt);
            }
        });

        groupAttendanceType.add(optAttendanceTypeB);
        optAttendanceTypeB.setText("Consultas");
        optAttendanceTypeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAttendanceTypeBActionPerformed(evt);
            }
        });

        btnBack.setText("Voltar");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSchedule.setText("Agendar");
        btnSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(optAttendanceTypeA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optAttendanceTypeB))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnBack)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSchedule))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optAttendanceTypeA)
                    .addComponent(optAttendanceTypeB))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnSchedule))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddPatientActionPerformed

    private void optAttendanceTypeAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAttendanceTypeAActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_optAttendanceTypeAActionPerformed

    private void optAttendanceTypeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAttendanceTypeBActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_optAttendanceTypeBActionPerformed

    private void fieldCpfPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCpfPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCpfPatientActionPerformed

    private void fieldCpfPatientPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fieldCpfPatientPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCpfPatientPropertyChange

    private void fieldStartSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldStartSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldStartSectionActionPerformed

    private void fieldEndSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEndSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldEndSectionActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.previusScreen.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleActionPerformed
        // TODO add your handling code here:
        PeopleController peopleCon = new PeopleController();
        ProcedureController procedureCon = new ProcedureController();
        AttendanceController attendanceCon = new AttendanceController();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm+s:SSSS");

        String doctorCpf = fieldCpfDoctor.getText().replaceAll(" ", "").replaceAll("\\.", "").replaceAll("\\-", "");
        String patientCpf = fieldCpfPatient.getText().replaceAll(" ", "").replaceAll("\\.", "").replaceAll("\\-", "");
        String startSection = fieldStartSection.getText().substring(6, 10)
                + "-"
                + fieldStartSection.getText().substring(3, 5)
                + "-"
                + fieldStartSection.getText().substring(0, 2)
                + "T"
                + fieldStartSection.getText().substring(13, 15)
                + ":"
                + fieldStartSection.getText().substring(16, 18)
                + "+0:0000";

        String endSection = fieldEndSection.getText().substring(6, 10)
                + "-"
                + fieldEndSection.getText().substring(3, 5)
                + "-"
                + fieldEndSection.getText().substring(0, 2)
                + "T"
                + fieldEndSection.getText().substring(13, 15)
                + ":"
                + fieldEndSection.getText().substring(16, 18)
                + "+0:0000";

        if (this.mode.equals("edit")) {
            try {
                if (fieldStartSection.getText() == null
                        || fieldStartSection.getText()
                                .replaceAll(" ", "")
                                .replaceAll("\\/", "")
                                .replaceAll("\\-", "")
                                .replaceAll("\\:", "").length() < 12) {
                    throw new AttendanceException("Error - Informe o inicio da sessão.");
                }
                if (fieldEndSection.getText() == null
                        || fieldEndSection.getText()
                                .replaceAll(" ", "")
                                .replaceAll("\\/", "")
                                .replaceAll("\\-", "")
                                .replaceAll("\\:", "").length() < 12) {
                    throw new AttendanceException("Error - Informe o fim da sessão.");
                }

                Calendar startSectionCalend = Calendar.getInstance(TimeZone.getTimeZone("GMT-00:00"));
                Calendar endSectionCalend = Calendar.getInstance(TimeZone.getTimeZone("GMT-00:00"));
                startSectionCalend.setTime(formatDate.parse(startSection));
                endSectionCalend.setTime(formatDate.parse(endSection));

                attendanceCon.onEditSchedule(attendance.getId(), startSectionCalend, endSectionCalend);

                this.setFieldsEnabled(false);
                int response = JOptionPane.showConfirmDialog(null,
                        "Voltar para a Home?",
                        "Agendamento editado com sucesso!",
                        JOptionPane.OK_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.OK_OPTION) {
                    FrAttendantHome attendantScreen = new FrAttendantHome();
                    this.setVisible(false);
                    attendantScreen.setVisible(true);
                }

            } catch (AttendanceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(FrAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                People findPeople = peopleCon.onFetchPeople(patientCpf);
                if (findPeople == null || findPeople.getPatient() == null) {
                    throw new AttendanceException("Error - Não foi encontrado um paciente com esse CPF.");
                }
                Patient patient = findPeople.getPatient();

                findPeople = peopleCon.onFetchPeople(doctorCpf);
                if (findPeople == null || findPeople.getDoctor() == null) {
                    throw new AttendanceException("Error - Não foi encontrado um doutor com esse CPF.");
                }
                Doctor doctor = findPeople.getDoctor();

                if (fieldStartSection.getText() == null
                        || fieldStartSection.getText()
                                .replaceAll(" ", "")
                                .replaceAll("\\/", "")
                                .replaceAll("\\-", "")
                                .replaceAll("\\:", "").length() < 12) {
                    throw new AttendanceException("Error - Informe o inicio da sessão.");
                }
                if (fieldEndSection.getText() == null
                        || fieldEndSection.getText()
                                .replaceAll(" ", "")
                                .replaceAll("\\/", "")
                                .replaceAll("\\-", "")
                                .replaceAll("\\:", "").length() < 12) {
                    throw new AttendanceException("Error - Informe o fim da sessão.");
                }

                Calendar startSectionCalend = Calendar.getInstance(TimeZone.getTimeZone("GMT-00:00"));
                Calendar endSectionCalend = Calendar.getInstance(TimeZone.getTimeZone("GMT-00:00"));
                startSectionCalend.setTime(formatDate.parse(startSection));
                endSectionCalend.setTime(formatDate.parse(endSection));

                MedicalProcedure procedure;
                try {
                    if (this.isNewProcedure) {
                        String price = fieldProcedurePrice.getText().replaceAll(" ", "").replaceAll("R", "").replaceAll("\\$", "").replaceAll("\\.", "").replaceAll(",", "\\.");
                        procedure = procedureCon.onSave(jComboProcedure.getSelectedItem(), price);
                    } else {
                        procedure = procedureCon.onFetchProcedure(procedures.get(jComboProcedure.getSelectedIndex()).getId());
                    }
                } catch (ProcedureException e) {
                    throw new AttendanceException(e.getMessage());
                }

                if (this.type.equals("Consulta")) {
                    Attendance newAttendance = new Attendance("Consulta", startSectionCalend, endSectionCalend, fieldFinally.getText());
                    newAttendance.setPatient(patient);
                    newAttendance.setDoctor(doctor);
                    newAttendance.setProcedure(procedure);
                    AppointmentController appointmentCon = new AppointmentController();
                    appointmentCon.onSave(newAttendance, Integer.parseInt(fieldNumSections.getText()));
                } else {
                    attendanceCon.onSave(patient, doctor, procedure, "Avaliacao", startSectionCalend, endSectionCalend, fieldFinally.getText());
                }

                this.setFieldsEnabled(false);
                int response = JOptionPane.showConfirmDialog(null,
                        "Voltar para a Home?",
                        "Cadastro de agendamento realizado com sucesso!",
                        JOptionPane.OK_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.OK_OPTION) {
                    FrAttendantHome attendantScreen = new FrAttendantHome();
                    this.setVisible(false);
                    attendantScreen.setVisible(true);
                }
            } catch (AttendanceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(FrAttendance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnScheduleActionPerformed

    private void jComboProcedureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboProcedureActionPerformed
        // TODO add your handling code here:
        System.out.println("action" + evt.getActionCommand() + "item " + jComboProcedure.getEditor().getItem().toString());
        System.out.println("selected " + jComboProcedure.getSelectedItem() + ", index: " + jComboProcedure.getSelectedIndex());

        if (!jComboProcedure.getSelectedItem().equals("") && jComboProcedure.getSelectedIndex() == -1) {
            this.isNewProcedure = true;
            labelNewProcedureFeedback.setVisible(true);
            fieldProcedurePrice.setEnabled(true);
            fieldProcedurePrice.setText("R$ ");
        } else {
            this.isNewProcedure = false;
            labelNewProcedureFeedback.setVisible(false);
            fieldProcedurePrice.setEnabled(false);
            fieldProcedurePrice.setText("R$ " + this.procedures.get(jComboProcedure.getSelectedIndex()).getPrice());
        }
    }//GEN-LAST:event_jComboProcedureActionPerformed

    private void jComboProcedureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboProcedureMousePressed
        // TODO add your handling code here:
        System.out.println("input text");
        jComboProcedure.showPopup();
    }//GEN-LAST:event_jComboProcedureMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPatient;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSchedule;
    private javax.swing.JFormattedTextField fieldCpfDoctor;
    private javax.swing.JFormattedTextField fieldCpfPatient;
    private javax.swing.JFormattedTextField fieldEndSection;
    private javax.swing.JTextArea fieldFinally;
    private javax.swing.JFormattedTextField fieldNumSections;
    private javax.swing.JFormattedTextField fieldProcedurePrice;
    private javax.swing.JFormattedTextField fieldStartSection;
    private javax.swing.ButtonGroup groupAttendanceType;
    private javax.swing.JComboBox<String> jComboProcedure;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNewProcedureFeedback;
    private javax.swing.JLabel labelNumSections;
    private javax.swing.JRadioButton optAttendanceTypeA;
    private javax.swing.JRadioButton optAttendanceTypeB;
    // End of variables declaration//GEN-END:variables
}
