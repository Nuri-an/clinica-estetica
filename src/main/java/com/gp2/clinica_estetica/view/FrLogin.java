/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gp2.clinica_estetica.view;

import com.gp2.clinica_estetica.controller.PeopleController;
import com.gp2.clinica_estetica.controller.UserController;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author gabri
 */
public class FrLogin extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public FrLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMasks();
    }

    public void clearFields() {
        this.jTextLogin.setText("");
        this.jPassword.setText("");
    }

    public void setMasks() {
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(jTextLogin);

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

        buttonGroupUserType = new javax.swing.ButtonGroup();
        jOptionPane1 = new javax.swing.JOptionPane();
        jPasswordField1 = new javax.swing.JPasswordField();
        windowsTitle = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnForgotPassword = new javax.swing.JButton();
        jPassword = new javax.swing.JPasswordField();
        windowDecription = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextLogin = new javax.swing.JFormattedTextField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        windowsTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        windowsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        windowsTitle.setText("Clínica de estética");

        passwordLabel.setText("Senha");

        loginLabel.setText("Login");

        btnLogin.setText("Entrar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnForgotPassword.setText("Criar conta");
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });

        windowDecription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        windowDecription.setText("Formulário de login");

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel1.setText("Esquici a senha");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jTextLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(windowDecription, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(windowsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel)
                            .addComponent(loginLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(windowsTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowDecription)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(79, 79, 79)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnForgotPassword)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            UserController userController = new UserController();
            String login = jTextLogin.getText().replaceAll("\\.", "").replaceAll("\\-", "");
            String password = jPassword.getText();
            System.out.println("login: " + login);
            this.clearFields();
            try {
                User userLogged = userController.onLogin(login, password);
                if (userLogged.getPeople().getDoctor() != null) {
                    System.out.println("doctor logged");
                    FrDoctor doctorScreen = new FrDoctor(userLogged);
                    doctorScreen.setVisible(true);
                    this.setVisible(false);
                } else if (userLogged.getPeople().getPatient() != null) {
                    System.out.println("patient logged");
                    FrPatient patientScreen = new FrPatient(userLogged);
                    patientScreen.setVisible(true);
                    this.setVisible(false);
                } else {
                    System.out.println("attendant logged");
                    FrAttendantHome attendantScreen = new FrAttendantHome(userLogged);
                    attendantScreen.setVisible(true);
                    this.setVisible(false);
                }
            } catch (UserException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed
        // TODO add your handling code here:

        FrUserType frMain = new FrUserType();
        this.setVisible(false);
        frMain.setLocationRelativeTo(null);
        frMain.setVisible(true);
    }//GEN-LAST:event_btnForgotPasswordActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jTextLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextLoginActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        String login = jTextLogin.getText().replaceAll(" ", "").replaceAll("\\.", "").replaceAll("\\-", "");
        if (login.length() == 11) {
            PeopleController peopleCon = new PeopleController();
            People currentUser = peopleCon.onFetchPeople(login);

            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado! Verifique o login para recuperar a senha");
                return;
            }

            FrResetPassword resetPasswordScreen = new FrResetPassword(currentUser);
            this.setVisible(false);
            resetPasswordScreen.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Atenção - Informe o login para recuperar a senha.");
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.ButtonGroup buttonGroupUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JFormattedTextField jTextLogin;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel windowDecription;
    private javax.swing.JLabel windowsTitle;
    // End of variables declaration//GEN-END:variables
}
