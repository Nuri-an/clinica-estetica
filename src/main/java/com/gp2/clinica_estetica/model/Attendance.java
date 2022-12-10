/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private Date startDateTime;
    private Date endDateTime;
    private String finality;
    
    @OneToOne
    @JoinColumn(name = "patient_id") // nome para coluna foreign key no banco
    private Patient patient;
    
    @OneToOne
    @JoinColumn(name = "doctor_id") // nome para coluna foreign key no banco
    private Doctor doctor;
    
    @OneToOne
    @JoinColumn(name = "procedure_id") // nome para coluna foreign key no banco
    private Procedure procedure;
    
    
    public Attendance() {
        this.id = -1;
        this.type = "Avaliacao";
        this.startDateTime =  new Date();
        this.endDateTime = new Date();
        this.finality = "";
    }
    
    public Attendance(String type, Date startDateTime, Date endDateTime, String finality) {
        this.type = type;
        this.startDateTime =  startDateTime;
        this.endDateTime = endDateTime;
        this.finality = finality;
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
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime the startDateTime to set
     */
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the endDateTime
     */
    public Date getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime the endDateTime to set
     */
    public void setEndDateTime(Date endDateTime) {
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
    public Procedure getProcedure() {
        return procedure;
    }

    /**
     * @param procedure the procedure to set
     */
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
    
}
