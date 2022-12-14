/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model;

import com.gp2.clinica_estetica.model.dao.reports.DaoReports;
import java.io.File;
import java.io.IOException;
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
public class Contract extends DaoReports implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private boolean signed;
    private String file;

    @JoinColumn(name = "procedure_id") // nome para coluna foreign key no banco
    @OneToOne
    private MedicalProcedure procedure;
    
    @JoinColumn(name = "patient_id") // nome para coluna foreign key no banco
    @OneToOne
    private Patient patient;
    
    @JoinColumn(name = "doctor_id") // nome para coluna foreign key no banco
    @OneToOne
    private Doctor doctor;
    
    
    public Contract() {
        this.id = -1;
        this.date =  new Date();
        this.signed = false;
        this.file = "";
    }
    
    public Contract(Date date,  boolean signed, String file) {
        this.date = date;
        this.signed = signed;
        this.file = file;
    }
    
    @Override
    public void saveFile(File source, File dest) throws IOException {
        super.saveFile(source, dest);
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the signed
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * @param signed the signed to set
     */
    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
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

}
