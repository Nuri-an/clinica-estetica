/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.view;

import com.gp2.clinica_estetica.controller.AppointmentController;
import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.exceptions.AppointmentException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
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
    SimpleDateFormat dt;
    private JFrame previusScreen;

    public FrAppointment() {
        initizalize();
    }

    /**
     * Creates new form FrAtendimento
     *
     * @param previusScreen
     * @param appointment
     * @param people
     */
    public FrAppointment(JFrame previusScreen, Appointment appointment, People people) {
        initizalize();

        this.previusScreen = previusScreen;
        this.appointment = appointment;
        this.currentPeople = people;
        this.appointmentCon = new AppointmentController();
        this.dt = new SimpleDateFormat("dd/MM/yyyy");
        btnPutSection.setVisible(false);

        if (people.getDoctor() != null) {
            this.typeUser = "Doctor";

        } else if (people.getPatient() != null) {
            typeUser = "Patient";
        }

        this.reloadComponents();

        String name = "";
        if (this.typeUser.equals("Doctor")) {
            this.btnRecipte.setText("Adicionar Receita");
            name = appointment.getAttendance().getDoctor().getPeople().getName();
        } else if (this.typeUser.equals("Patient")) {
            this.btnRecipte.setText("Baixar Receitas");

            if (appointment.getRecipte().size() < 1) {
                name = appointment.getAttendance().getPatient().getPeople().getName();
                btnRecipte.setEnabled(false);
                btnRecipte.setText("Nenhuma receita");
            }
        }
        textSubtitle.setText(appointment.getAttendance().getType() + " - " + name);
    }

    public void initizalize() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        fileSelector.setVisible(false);
        fileSelector.setFileFilter(filter);
    }

    public void reloadComponents() {
        String date = dt.format(appointment.getAttendance().getStartDateTime().getTime());
        textTitle.setText(appointment.getAttendance().getProcedure().getName());
        textDate.setText(date);
        textQtnSessions.setText(appointment.getCurrentSession() + " de " + appointment.getNumberOfSessions() + " sessões");
        textQtnReciptes.setText(appointment.getRecipte().size() + " reaceitas geradas");
        textPrice.setText("R$ " + ((Double) (Math.round(appointment.getBudget() * 100.0) / 100.0)).toString().replaceAll("\\.", ","));

        if (this.typeUser.equals("Doctor")) {
            btnPutSection.setVisible(true);
            if (appointment.getCurrentSession() < appointment.getNumberOfSessions()) {
                btnPutSection.setText("Marcar sessão " + (appointment.getCurrentSession() + 1) + " como concluída");
            } else {
                btnPutSection.setText("Todas as sessões concluídas");
                btnPutSection.setEnabled(false);
            }
        }

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
        btnBack = new javax.swing.JButton();
        btnPutSection = new javax.swing.JButton();

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

        fileSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSelectorActionPerformed(evt);
            }
        });

        textDate.setText("01/01/2000");

        textQtnSessions.setText("0 sessões");

        textQtnReciptes.setText("0 receitas geradas");

        textPrice.setText("R$ 0,00");

        btnBack.setText("Voltar");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnPutSection.setText("Marcar sessão x como concluída");
        btnPutSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPutSectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textSubtitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRecipte))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textDate)
                            .addComponent(textPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textQtnReciptes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textQtnSessions)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(btnPutSection))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTitle)
                            .addComponent(btnBack))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDate)
                    .addComponent(textQtnSessions)
                    .addComponent(btnPutSection))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPrice)
                    .addComponent(textQtnReciptes))
                .addGap(36, 36, 36)
                .addComponent(fileSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(39, Short.MAX_VALUE))
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

        if (this.typeUser.equals("Doctor")) {
            fileSelector.setVisible(true);
        } else if (this.typeUser.equals("Patient")) {
            try {
                for (int i = 0; i < this.appointment.getRecipte().size(); i++) {
                    String nameFile = this.appointment.getRecipte().get(i).getSrc();
                    File src = new File("src/main/resources/receitas/" + nameFile);
                    File dest = new File(nameFile);
                    this.appointment.saveFile(src, dest);

                }
                JOptionPane.showMessageDialog(this, "(" + this.appointment.getRecipte().size() + ") receitas salvas!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar receita ");
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

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.previusScreen.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnPutSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPutSectionActionPerformed
        // TODO add your handling code here:
        try {
            appointmentCon.onPutSection(appointment.getId(), appointment.getCurrentSession() + 1);
            this.reloadComponents();
        } catch (AppointmentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnPutSectionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPutSection;
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
