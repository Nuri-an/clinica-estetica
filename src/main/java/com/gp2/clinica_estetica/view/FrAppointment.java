/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.view;

import com.gp2.clinica_estetica.controller.AppointmentController;
import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.People;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author nuria
 */
public class FrAppointment extends javax.swing.JFrame {

    File file;
    Appointment appointment;
    People currentPeople;
    String typeUser; // "Doctor" | "Patient"
    AppointmentController appointmentCon; 

    public FrAppointment() {
        initComponents();
        fileSelector.setVisible(false);        
    }
    
    /**
     * Creates new form FrAtendimento
     *
     * @param appointment
     * @param people
     */
    public FrAppointment(Appointment appointment, People people) {
        initComponents();
        fileSelector.setVisible(false);
        fileSelector.setFileFilter(filter);
        this.appointment = appointment;
        this.currentPeople = people;
        this.appointmentCon = new AppointmentController();

        if (people.getDoctor() != null) {
            this.typeUser = "Doctor";
                
        } else if (people.getPatient() != null) {
            typeUser = "Patient";        
        }
        
        this.reloadComponents();
        
        if(this.typeUser.equals("Doctor")) {
            this.btnRecipte.setText("Adicionar Receita");
        } else if (this.typeUser.equals("Patient")) {
            this.btnRecipte.setText("Baixar Receitas");       
        }
    }
    
    public void reloadComponents () {
        textTitle.setText(appointment.getAttendance().getProcedure().getName());
        textSubtitle.setText(appointment.getAttendance().getType() + " - " + appointment.getAttendance().getPatient().getPeople().getName());
        textQtnSessions.setText("20/04/2022");
        textQtnSessions.setText(appointment.getCurrentSession() + " de " + appointment.getNumberOfSessions() + " sessões");
        textQtnReciptes.setText(appointment.getRecipte().size() + " reaceitas geradas");
        textPrice.setText("R$ " + appointment.getBudget().toString().replace(".", ","));
    }

    FileFilter filter = new FileFilter() {
        @Override
        public String getDescription() {
            return "PDF Documents (*.pdf)";
        }

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                String filename = f.getName().toLowerCase();
                return filename.endsWith(".pdf");
            }
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textTitle = new javax.swing.JLabel();
        textSubtitle = new javax.swing.JLabel();
        btnRecipte = new javax.swing.JButton();
        fileSelector = new javax.swing.JFileChooser();
        textDate = new javax.swing.JLabel();
        textQtnSessions = new javax.swing.JLabel();
        textQtnReciptes = new javax.swing.JLabel();
        textPrice = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        textTitle.setText("Consulta");

        textSubtitle.setText("Nome do Paciente - Tipo de atendimento");

        btnRecipte.setText("Adicionar Receita");
        btnRecipte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecipteActionPerformed(evt);
            }
        });

        textDate.setText("01/01/2000");

        textQtnSessions.setText("0 sessões");

        textQtnReciptes.setText("0 receitas geradas");

        textPrice.setText("R$ 0,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textTitle)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textSubtitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                        .addComponent(btnRecipte)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textDate)
                            .addComponent(textPrice))
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textQtnReciptes)
                            .addComponent(textQtnSessions))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(fileSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(textTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSubtitle)
                    .addComponent(btnRecipte))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDate)
                    .addComponent(textQtnSessions))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPrice)
                    .addComponent(textQtnReciptes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(fileSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        //FrDoctor telaMedico = new FrDoctor();
        //telaMedico.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRecipteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecipteActionPerformed
        // TODO add your handling code here:

        if(this.typeUser.equals("Doctor")) {
            fileSelector.setVisible(true);
        } else if(this.typeUser.equals("Patient")) {
            for(int i = 0; i < this.appointment.getRecipte().size(); i++) {
                String nameFile = this.appointment.getRecipte().get(i) + "_(" + i + ")";
                File src = new File("src/main/resources/receitas/" + nameFile);
                File dest = new File(nameFile);
                try {
                    this.appointment.saveFile(src, dest);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar receita " + nameFile);
                }
            }
            
        }
        

        // se paciete - baixar receitas
    }//GEN-LAST:event_btnRecipteActionPerformed

    private void fileSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSelectorActionPerformed
        // TODO add your handling code here:

        file = fileSelector.getSelectedFile();
        System.err.println(evt.getActionCommand());

        if (evt.getActionCommand().equals("CancelSelection")) {
            fileSelector.setVisible(false);
            return;
        }

        try {
            String nameFile = file.getName();

            int response = JOptionPane.showConfirmDialog(null,
                    "Deseja adicionar o arquivo "
                    + nameFile + " ?",
                    "Confirmar o upload",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.OK_OPTION) {
                File destino = new File("src/main/resources/receitas/" + nameFile);
                this.appointment.saveFile(file, destino);
                
                // adicionar nome do arquivo no banco e recarregar view p/ atualizar numero de receitas
                appointmentCon.onSendRecipte(this.appointment.getId(), nameFile);
                this.appointment = appointmentCon.onFetchAppointment(this.appointment.getId());
                this.reloadComponents();
                fileSelector.setVisible(false);
                JOptionPane.showMessageDialog(this, "Receita adicionada com sucesso!");
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }//GEN-LAST:event_fileSelectorActionPerformed

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
            java.util.logging.Logger.getLogger(FrAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrAppointment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecipte;
    private javax.swing.JFileChooser fileSelector;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel textDate;
    private javax.swing.JLabel textPrice;
    private javax.swing.JLabel textQtnReciptes;
    private javax.swing.JLabel textQtnSessions;
    private javax.swing.JLabel textSubtitle;
    private javax.swing.JLabel textTitle;
    // End of variables declaration//GEN-END:variables
}
