/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.view;

import com.gp2.clinica_estetica.controller.AttendanceController;
import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author darloonlino
 */
public class FrAttendanceList extends javax.swing.JFrame {

    private User user;
    AttendanceController attendanceCon;

    /**
     * Creates new form FrPatientsList
     */
    public FrAttendanceList() {
        initComponents();
    }

    /**
     * Creates new form FrPatientsList
     */
    public FrAttendanceList(User user) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.attendanceCon = new AttendanceController();
        this.user = user;

        groupOptType.setSelected(radioOptA.getModel(), true);
        this.attendanceCon.onFindAllAttendancesFilter(grdAttendances, "Avaliacao", "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupOptType = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdAttendances = new javax.swing.JTable();
        btnAddPatient = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        fieldSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        radioOptA = new javax.swing.JRadioButton();
        radioOptB = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Agendamentos");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Todos agendamentos da clínica");

        grdAttendances.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Paciente", "Procedimento"
            }
        ));
        grdAttendances.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdAttendancesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grdAttendances);

        btnAddPatient.setForeground(new java.awt.Color(51, 51, 51));
        btnAddPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/plus.png"))); // NOI18N
        btnAddPatient.setBorder(null);
        btnAddPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatientActionPerformed(evt);
            }
        });

        btnBack.setText("Voltar");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        fieldSearch.setToolTipText("Pesquise por CPF");
        fieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldSearchActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setText("Pesquise pelo nome do paciente:");

        groupOptType.add(radioOptA);
        radioOptA.setText("Avaliações");
        radioOptA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOptAActionPerformed(evt);
            }
        });

        groupOptType.add(radioOptB);
        radioOptB.setText("Consultas");
        radioOptB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOptBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBack)
                                .addComponent(jLabel1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(441, 441, 441)
                                        .addComponent(btnAddPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(radioOptA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioOptB))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnAddPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioOptA)
                        .addComponent(radioOptB)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatientActionPerformed
        // TODO add your handling code here:
        FrAttendance attendanceScreen = new FrAttendance(this, "create", user);
        attendanceScreen.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAddPatientActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        FrAttendantHome attendantScreen = new FrAttendantHome(this.user);
        attendantScreen.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void fieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String type;

        if (radioOptA.isSelected()) {
            type = "Avaliacao";
        } else {
            type = "Consulta";
        }
        this.attendanceCon.onFindAllAttendancesFilter(grdAttendances, type, fieldSearch.getText());
    }//GEN-LAST:event_btnSearchActionPerformed

    private void grdAttendancesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdAttendancesMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() > 1) {
            int row = grdAttendances.getSelectedRow();
            Attendance attendanceRow = (Attendance) grdAttendances.getValueAt(row, -1);
            String type;

            if (radioOptA.isSelected()) {
                type = "Avaliacao";
            } else {
                type = "Consulta";
            }

            String[] options = {"editar", "excluir", "fechar"};
            int response = JOptionPane.showOptionDialog(null, "Deseja editar ou excluir esse atendimento?",
                    "Selecione a ação",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (response == 0) {
                FrAttendance attendanceScreen = new FrAttendance(this, "edit", attendanceRow.getType(), attendanceRow.getId(), this.user);
                attendanceScreen.setVisible(true);
                this.setVisible(false);
            } else if (response == 1) {
                int responseDel = JOptionPane.showConfirmDialog(null,
                        "Tem certeza quedeseja delatar e liberar o horario de "
                        + attendanceRow.getStartDateTime().get(Calendar.DAY_OF_MONTH)
                        + "/"
                        + (attendanceRow.getStartDateTime().get(Calendar.MONTH) + 1)
                        + " - "
                        + attendanceRow.getStartDateTime().get(Calendar.HOUR_OF_DAY)
                        + ":"
                        + attendanceRow.getStartDateTime().get(Calendar.MINUTE)
                        + " à "
                        + attendanceRow.getEndDateTime().get(Calendar.DAY_OF_MONTH)
                        + "/"
                        + (attendanceRow.getEndDateTime().get(Calendar.MONTH) + 1)
                        + " - "
                        + attendanceRow.getEndDateTime().get(Calendar.HOUR_OF_DAY)
                        + ":"
                        + attendanceRow.getEndDateTime().get(Calendar.MINUTE),
                        "Exclusão solicitada!",
                        JOptionPane.YES_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (responseDel == JOptionPane.YES_OPTION) {
                    try {
                        attendanceCon.onDelete(attendanceRow.getId());
                        attendanceCon.onFindAllAttendancesFilter(grdAttendances, type, fieldSearch.getText());
                    } catch (AttendanceException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }

            }
        }
    }//GEN-LAST:event_grdAttendancesMouseClicked

    private void radioOptAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOptAActionPerformed
        // TODO add your handling code here:
        this.attendanceCon.onFindAllAttendancesFilter(grdAttendances, "Avaliacao", fieldSearch.getText());
    }//GEN-LAST:event_radioOptAActionPerformed

    private void radioOptBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOptBActionPerformed
        // TODO add your handling code here:
        this.attendanceCon.onFindAllAttendancesFilter(grdAttendances, "Consulta", fieldSearch.getText());
    }//GEN-LAST:event_radioOptBActionPerformed

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
            java.util.logging.Logger.getLogger(FrAttendanceList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrAttendanceList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrAttendanceList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrAttendanceList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrAttendanceList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPatient;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSearch;
    private javax.swing.JTextField fieldSearch;
    private javax.swing.JTable grdAttendances;
    private javax.swing.ButtonGroup groupOptType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioOptA;
    private javax.swing.JRadioButton radioOptB;
    // End of variables declaration//GEN-END:variables
}
