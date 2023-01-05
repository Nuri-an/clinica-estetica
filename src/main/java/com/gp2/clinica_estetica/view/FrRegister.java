/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gp2.clinica_estetica.view;

import com.gp2.clinica_estetica.controller.PatientController;
import com.gp2.clinica_estetica.controller.PeopleController;
import com.gp2.clinica_estetica.controller.UserController;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author gabri
 */
public class FrRegister extends javax.swing.JFrame {

    private Boolean fieldsEnabled;
    private String userType; // "Patient" | "Doctor" | "Attendant" | "PreRegister"
    private User currentUser;
    private String mode; // "create | "edit
    private JFrame previusScreen;

    /**
     * Creates new form FrPatientManagement
     */
    public FrRegister() {
        initComponents();
    }

    /**
     * Creates new form FrPatientManagement as user register
     *
     * @param previusScreen
     * @param userType
     */
    public FrRegister(JFrame previusScreen, String userType) {
        initComponents();
        this.previusScreen = previusScreen;
        this.fieldsEnabled = true;
        this.userType = userType;
        this.setFieldsEnabled(this.fieldsEnabled);
        this.clearFields();
        this.setMasks();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Creates new form FrPatientManagement as attendant registering/editing
     *
     * @param previusScreen
     * @param user
     * @param mode
     */
    public FrRegister(JFrame previusScreen, User user, String mode) {
        initComponents();
        this.previusScreen = previusScreen;
        this.mode = mode;
        boxAccessData.setVisible(false);
        this.fieldsEnabled = true;
        this.currentUser = user;
        this.userType = "PreRegister";
        this.setFieldsEnabled(this.fieldsEnabled);
        this.clearFields();
        this.setMasks();

        People currentPeople = user.getPeople();

        if (mode.equals("create")) {
            textTitle.setText("Preencha os campos abaixo com os dados do paciente para cadastrá-lo");
        } else {
            textFieldName.setText(currentPeople.getName());
            textFieldCpf.setText(currentPeople.getCPF());
            textFieldBirthDate.setText(currentPeople.getBirthDate());
            textFieldPhoneNumber.setText(currentPeople.getPhoneNumber().getNumber());
            jCheckBoxIsWhatsapp.setSelected(currentPeople.getPhoneNumber().isIsWhatsapp());
            textFieldZipCode.setText(currentPeople.getAddress().getZipCode());
            textFieldStreet.setText(currentPeople.getAddress().getStreet());
            textFieldNeighborhood.setText(currentPeople.getAddress().getNeighborhood());
            textFieldNumber.setText(currentPeople.getAddress().getHouseNumber() + "");

            textFieldCpf.setEnabled(false);
        }
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void setFieldsEnabled(Boolean flag) {
        textFieldCpf.setEnabled(flag);
        textFieldBirthDate.setEnabled(flag);
        textFieldZipCode.setEnabled(flag);
        textFieldNeighborhood.setEnabled(flag);
        textFieldNumber.setEnabled(flag);
        textFieldPhoneNumber.setEnabled(flag);
        textFieldStreet.setEnabled(flag);
        textFieldName.setEnabled(flag);
        jCheckBoxIsWhatsapp.setEnabled(flag);
        textFieldSecQuestion.setEnabled(flag);
        textFieldSecAnswer.setEnabled(flag);
        jPasswordField.setEnabled(flag);
        jPasswordFieldConfirmation.setEnabled(flag);
        btnRegister.setEnabled(flag);
        disableFieldsFeedback.setVisible(false);

        if (this.userType.equals("Patient")) {
            textFieldBirthDate.setEnabled(false);
            textFieldZipCode.setEnabled(false);
            textFieldNeighborhood.setEnabled(false);
            textFieldNumber.setEnabled(false);
            textFieldPhoneNumber.setEnabled(false);
            textFieldStreet.setEnabled(false);
            textFieldName.setEnabled(false);
            jCheckBoxIsWhatsapp.setEnabled(false);
            disableFieldsFeedback.setVisible(true);
        }
    }

    public void clearFields() {
        textFieldBirthDate.setText("");
        textFieldZipCode.setText("");
        textFieldCpf.setText("");
        textFieldNeighborhood.setText("");
        textFieldNumber.setText("");
        textFieldPhoneNumber.setText("");
        textFieldStreet.setText("");
        textFieldName.setText("");
        jCheckBoxIsWhatsapp.setSelected(false);
        textFieldSecQuestion.setText("");
        textFieldSecAnswer.setText("");
        jPasswordField.setText("");
        jPasswordFieldConfirmation.setText("");
    }

    public void setMasks() {
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(textFieldCpf);

            MaskFormatter maskBirthDate = new MaskFormatter("##/##/####");
            maskBirthDate.install(textFieldBirthDate);

            MaskFormatter maskPhoneNumber = new MaskFormatter("(##) #####-####");
            maskPhoneNumber.install(textFieldPhoneNumber);

            MaskFormatter maskZipCode = new MaskFormatter("#####-###");
            maskZipCode.install(textFieldZipCode);

            MaskFormatter maskNumber = new MaskFormatter("#####");
            maskNumber.install(textFieldNumber);

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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxPersonalData = new javax.swing.JPanel();
        textFieldName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxIsWhatsapp = new javax.swing.JCheckBox();
        textFieldBirthDate = new javax.swing.JFormattedTextField();
        textFieldCpf = new javax.swing.JFormattedTextField();
        textFieldPhoneNumber = new javax.swing.JFormattedTextField();
        boxAddress = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textFieldStreet = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textFieldNeighborhood = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textFieldZipCode = new javax.swing.JFormattedTextField();
        textFieldNumber = new javax.swing.JFormattedTextField();
        btnBack = new javax.swing.JButton();
        boxAccessData = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textFieldSecQuestion = new javax.swing.JFormattedTextField();
        textFieldSecAnswer = new javax.swing.JFormattedTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jPasswordFieldConfirmation = new javax.swing.JPasswordField();
        textTitle = new javax.swing.JLabel();
        disableFieldsFeedback = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        boxPersonalData.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados pessoais"));
        boxPersonalData.setToolTipText("");

        jLabel1.setText("Nome:");

        jLabel2.setText("Data de nascimento:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Telefone:");

        jCheckBoxIsWhatsapp.setText("É WhatsApp");

        textFieldCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCpfActionPerformed(evt);
            }
        });
        textFieldCpf.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                textFieldCpfPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout boxPersonalDataLayout = new javax.swing.GroupLayout(boxPersonalData);
        boxPersonalData.setLayout(boxPersonalDataLayout);
        boxPersonalDataLayout.setHorizontalGroup(
            boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxPersonalDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGroup(boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(boxPersonalDataLayout.createSequentialGroup()
                        .addGroup(boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(boxPersonalDataLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(textFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(boxPersonalDataLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(boxPersonalDataLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldBirthDate))
                            .addGroup(boxPersonalDataLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldPhoneNumber))))
                    .addGroup(boxPersonalDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxIsWhatsapp)))
                .addContainerGap())
        );
        boxPersonalDataLayout.setVerticalGroup(
            boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxPersonalDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(boxPersonalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(textFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxIsWhatsapp))
        );

        boxAddress.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));
        boxAddress.setToolTipText("");

        jLabel5.setText("CEP:");

        jLabel6.setText("Rua:");

        jLabel7.setText("Bairro:");

        jLabel8.setText("Número");

        javax.swing.GroupLayout boxAddressLayout = new javax.swing.GroupLayout(boxAddress);
        boxAddress.setLayout(boxAddressLayout);
        boxAddressLayout.setHorizontalGroup(
            boxAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boxAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(boxAddressLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldStreet))
                    .addGroup(boxAddressLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldNeighborhood, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldNumber)))
                .addContainerGap())
        );
        boxAddressLayout.setVerticalGroup(
            boxAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boxAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(textFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(boxAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textFieldNeighborhood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(textFieldNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnBack.setText("Voltar");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        boxAccessData.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados de acesso"));
        boxAccessData.setToolTipText("");

        jLabel9.setText("Pergunta de Segurança:");

        jLabel10.setText("Confirmação da senha:");

        jLabel11.setText("Senha:");

        jLabel12.setText("Resposta da pergunta de segurança:");

        javax.swing.GroupLayout boxAccessDataLayout = new javax.swing.GroupLayout(boxAccessData);
        boxAccessData.setLayout(boxAccessDataLayout);
        boxAccessDataLayout.setHorizontalGroup(
            boxAccessDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxAccessDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boxAccessDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(boxAccessDataLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldSecQuestion))
                    .addGroup(boxAccessDataLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldSecAnswer))
                    .addGroup(boxAccessDataLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordFieldConfirmation, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );
        boxAccessDataLayout.setVerticalGroup(
            boxAccessDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxAccessDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boxAccessDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textFieldSecQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(boxAccessDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(textFieldSecAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(boxAccessDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        textTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textTitle.setText("Preencha os campos abaixo para realizar o cadastro");

        disableFieldsFeedback.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        disableFieldsFeedback.setText("** Informe seu CPF para preencher os campos desabilitados");

        btnRegister.setText("Cadastrar");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxPersonalData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegister))
                    .addComponent(boxAccessData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boxAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTitle)
                            .addComponent(disableFieldsFeedback))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(textTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disableFieldsFeedback)
                .addGap(23, 23, 23)
                .addComponent(boxPersonalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxAccessData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnRegister))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        String cpf = textFieldCpf.getText().replaceAll("\\.", "").replaceAll("\\-", "");
        if (jPasswordField.getText().length() > 7 && !jPasswordFieldConfirmation.getText().equals(jPasswordField.getText())) {
            JOptionPane.showMessageDialog(this, "Erro - Os campos 'senha' e 'confirmar senha' não conferem!");
            return;
        }

        try {
            if (this.userType.equals("Patient")) {
                PatientController patienteCon = new PatientController();
                patienteCon.onCompleteRegister(cpf, jPasswordField.getText(), textFieldSecQuestion.getText(), textFieldSecAnswer.getText());
            } else if (this.userType.equals("PreRegister")) {
                PeopleController peopleCon = new PeopleController();
                Integer houseNumber = null;

                if (!textFieldNumber.getText().replaceAll(" ", "").equals("")) {
                    houseNumber = Integer.parseInt(textFieldNumber.getText().replaceAll(" ", ""));
                }

                if (this.mode.equals("create")) {
                    peopleCon.onBasicRegister(
                            textFieldName.getText(),
                            cpf,
                            textFieldBirthDate.getText().replaceAll(" ", "").replaceAll("\\/", ""),
                            textFieldPhoneNumber.getText().replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", ""),
                            jCheckBoxIsWhatsapp.isSelected(),
                            textFieldZipCode.getText().replaceAll(" ", "").replaceAll("-", ""),
                            textFieldStreet.getText(),
                            textFieldNeighborhood.getText(),
                            houseNumber
                    );
                } else {
                    peopleCon.onBasicEdit(
                            textFieldName.getText(),
                            cpf,
                            textFieldBirthDate.getText().replaceAll(" ", "").replaceAll("\\/", ""),
                            textFieldPhoneNumber.getText().replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", ""),
                            jCheckBoxIsWhatsapp.isSelected(),
                            textFieldZipCode.getText().replaceAll(" ", "").replaceAll("-", ""),
                            textFieldStreet.getText(),
                            textFieldNeighborhood.getText(),
                            houseNumber
                    );
                }

            } else {
                UserController userCon = new UserController();
                Integer houseNumber = null;

                if (!textFieldNumber.getText().replaceAll(" ", "").equals("")) {
                    houseNumber = Integer.parseInt(textFieldNumber.getText().replaceAll(" ", ""));
                }

                userCon.onRegister(
                        textFieldName.getText(),
                        cpf,
                        textFieldBirthDate.getText().replaceAll(" ", "").replaceAll("\\/", ""),
                        textFieldPhoneNumber.getText().replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", ""),
                        jCheckBoxIsWhatsapp.isSelected(),
                        textFieldZipCode.getText().replaceAll(" ", "").replaceAll("-", ""),
                        textFieldStreet.getText(),
                        textFieldNeighborhood.getText(),
                        houseNumber,
                        jPasswordField.getText(),
                        textFieldSecQuestion.getText(),
                        textFieldSecAnswer.getText().toLowerCase(),
                        userType);
            }

            if (this.userType.equals("PreRegister")) {
                this.setFieldsEnabled(false);

                if (this.mode.equals("create")) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Voltar para a Home?",
                            "Cadastro de paciente realizado com sucesso!",
                            JOptionPane.OK_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.OK_OPTION) {
                        this.clearFields();
                        FrAttendantHome attendantScreen = new FrAttendantHome();
                        this.setVisible(false);
                        attendantScreen.setVisible(true);
                    }
                } else {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Voltar para a listagem?",
                            "Paciente editado com sucesso!",
                            JOptionPane.OK_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.OK_OPTION) {
                        this.clearFields();
                        FrPatientsList patientsScreen = new FrPatientsList(this.currentUser);
                        this.setVisible(false);
                        patientsScreen.setVisible(true);
                    }
                }
            } else {
                this.setFieldsEnabled(false);
                int response = JOptionPane.showConfirmDialog(null,
                        "Ir para a tela de login?",
                        "Cadastro realizado com sucesso!",
                        JOptionPane.OK_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.OK_OPTION) {
                    this.clearFields();
                    FrLogin loginScreen = new FrLogin();
                    this.setVisible(false);
                    loginScreen.setVisible(true);
                }
            }
        } catch (UserException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void textFieldCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCpfActionPerformed

    private void textFieldCpfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_textFieldCpfPropertyChange
        // TODO add your handling code here:
        if (textFieldCpf.getText().equals("")) {
            return;
        }
        String cpf = textFieldCpf.getText().replaceAll(" ", "");

        if (cpf.length() == 14 && this.userType.equals("Patient")) {
            try {
                cpf = textFieldCpf.getText().replaceAll("\\.", "").replaceAll("\\-", "");
                PeopleController peopleCon = new PeopleController();
                People curretUser = peopleCon.onFetchPeople(cpf);

                if (curretUser == null) {
                    JOptionPane.showMessageDialog(this, "É preciso ser cliente para ter acesso ao sistema. "
                            + "Entre em contato e agende uma avaliação! "
                            + "Telefone: 3451-8620");
                    return;
                }

                if (curretUser.getBirthDate() != null) {
                    textFieldBirthDate.setText(curretUser.getBirthDate());
                }
                if (curretUser.getAddress().getZipCode() != null) {
                    textFieldZipCode.setText(curretUser.getAddress().getZipCode());
                }
                if (curretUser.getAddress().getNeighborhood() != null) {
                    textFieldNeighborhood.setText(curretUser.getAddress().getNeighborhood());
                }
                if (curretUser.getAddress().getStreet() != null) {
                    textFieldStreet.setText(curretUser.getAddress().getStreet());
                }
                if (curretUser.getPhoneNumber().getNumber() != null) {
                    textFieldPhoneNumber.setText(curretUser.getPhoneNumber().getNumber());
                }
                if (curretUser.getName() != null) {
                    textFieldName.setText(curretUser.getName());
                }
                jCheckBoxIsWhatsapp.setSelected(curretUser.getPhoneNumber().isIsWhatsapp());
                if (curretUser.getAddress().getHouseNumber() != null) {
                    textFieldNumber.setText(curretUser.getAddress().getHouseNumber().toString());
                }
            } catch (UserException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }//GEN-LAST:event_textFieldCpfPropertyChange

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.previusScreen.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrRegister.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrRegister.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrRegister.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrRegister.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxAccessData;
    private javax.swing.JPanel boxAddress;
    private javax.swing.JPanel boxPersonalData;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel disableFieldsFeedback;
    private javax.swing.JCheckBox jCheckBoxIsWhatsapp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPasswordField jPasswordFieldConfirmation;
    private javax.swing.JFormattedTextField textFieldBirthDate;
    private javax.swing.JFormattedTextField textFieldCpf;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldNeighborhood;
    private javax.swing.JFormattedTextField textFieldNumber;
    private javax.swing.JFormattedTextField textFieldPhoneNumber;
    private javax.swing.JFormattedTextField textFieldSecAnswer;
    private javax.swing.JFormattedTextField textFieldSecQuestion;
    private javax.swing.JTextField textFieldStreet;
    private javax.swing.JFormattedTextField textFieldZipCode;
    private javax.swing.JLabel textTitle;
    // End of variables declaration//GEN-END:variables
}
