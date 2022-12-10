/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model;

import com.gp2.clinica_estetica.model.dao.reports.DaoReports;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.swing.JTable;

/**
 *
 * @author nuria
 */
@Entity
public class Doctor extends DaoReports implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    @JoinColumn(name = "people_id") // nome para coluna foreign key no banco
    private People people;

    public Doctor() {
        this.id = -1;
    }

    public Doctor(People people) {
        this.people = people;
    }
    
    public void generateAttendancePDF(JTable table, String type) {
        super.generateTablePDF("Relatorio.pdf", "Relatório de Atendimentos - " + type.toUpperCase(), table);
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
     * @return the people
     */
    public People getPeople() {
        return people;
    }

    /**
     * @param people the people to set
     */
    public void setPeople(People people) {
        this.people = people;
    }

}
