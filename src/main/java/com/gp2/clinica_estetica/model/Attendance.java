/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 *
 * @author nuria
 */

@Entity
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type; // "Avaliacao" | "Consulta"
    private Calendar startDateTime;
    private Calendar endDateTime;
    
    @Lob //for longs texts
    private String finality;

    @OneToOne
    @JoinColumn(name = "patient_id") // nome para coluna foreign key no banco
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id") // nome para coluna foreign key no banco
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "procedure_id") // nome para coluna foreign key no banco
    private MedicalProcedure procedure;

    @OneToOne
    @JoinColumn(name = "appointment_id") // nome para coluna foreign key no banco
    private Appointment appointment;

    public Attendance() {
        this.id = -1;
        this.type = "Avaliacao";
        this.startDateTime = Calendar.getInstance();
        this.endDateTime = Calendar.getInstance();
        this.finality = "";
    }

    public Attendance(String type, Calendar startDateTime, Calendar endDateTime, String finality) {
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.finality = finality;
    }
    
    @Override
    public String toString() {  
        return "" 
                + this.getPatient().getPeople().getName()
                + " - "
                + this.getStartDateTime().DAY_OF_MONTH
                + "/" + this.getStartDateTime().MONTH
                + "/" + this.getStartDateTime().YEAR
                + " " + this.getStartDateTime().HOUR_OF_DAY
                + ":" + this.getStartDateTime().MINUTE;  
    } 

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the startDateTime
     */
    public Calendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime the startDateTime to set
     */
    public void setStartDateTime(Calendar startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the endDateTime
     */
    public Calendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime the endDateTime to set
     */
    public void setEndDateTime(Calendar endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @return the finality
     */
    public String getFinality() {
        return finality;
    }

    /**
     * @param finality the finality to set
     */
    public void setFinality(String finality) {
        this.finality = finality;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the procedure
     */
    public MedicalProcedure getProcedure() {
        return procedure;
    }

    /**
     * @param procedure the procedure to set
     */
    public void setProcedure(MedicalProcedure procedure) {
        this.procedure = procedure;
    }

    /**
     * @return the appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * @param appointment the appointment to set
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

}
