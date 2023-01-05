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
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author nuria
 */
@Entity
public class Appointment extends DaoReports implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numberOfSessions;
    private int currentSession;
    private Double budget;

    @OneToMany
    @JoinColumn(name = "reciptes_id")
    private List<Recipte> reciptes;
    
    @OneToOne
    @JoinColumn(name = "attendance_id") // nome para coluna foreign key no banco
    private Attendance attendance;

    @OneToOne
    @JoinColumn(name = "contract_id") // nome para coluna foreign key no banco
    private Contract contract;

    public Appointment() {
        this.id = -1;
        this.numberOfSessions = 0;
        this.currentSession = 0;
        this.budget = 0.0;
    }

    public Appointment(List<Recipte> recipte, int numberOfSessions, int currentSession, Double budget) {
        this.reciptes = recipte;
        this.numberOfSessions = numberOfSessions;
        this.currentSession = currentSession;
        this.budget = budget;
    }

    public Appointment(Attendance attendance, int numberOfSessions, int currentSession, Double budget) {
        this.attendance = attendance;
        this.numberOfSessions = numberOfSessions;
        this.currentSession = currentSession;
        this.budget = budget;
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
     * @return the recipte
     */
    public List<Recipte> getRecipte() {
        return reciptes;
    }

    /**
     * @param reciptes
     */
    public void setRecipte(List<Recipte> reciptes) {
        this.reciptes = reciptes;
    }

    /**
     * @param recipte the recipte to set
     */
    public void addRecipte(Recipte recipte) {
        this.reciptes.add(recipte);
    }

    /**
     * @return the numberOfSessions
     */
    public Integer getNumberOfSessions() {
        return numberOfSessions;
    }

    /**
     * @param numberOfSessions the numberOfSessions to set
     */
    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    /**
     * @return the currentSession
     */
    public int getCurrentSession() {
        return currentSession;
    }

    /**
     * @param currentSession the currentSession to set
     */
    public void setCurrentSession(int currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * @return the budget
     */
    public Double getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    /**
     * @return the attendance
     */
    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    /**
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }

}
