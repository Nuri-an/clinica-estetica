/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author nuria
 */
@Entity
public class PhoneNumber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private boolean isWhatsapp;
    
    public PhoneNumber() {
        this.id = -1;
        this.number = "";
        this.isWhatsapp = false;
    }
    
    public PhoneNumber(String number, boolean isWhatsapp) {
        this.number = number;
        this.isWhatsapp = isWhatsapp;
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
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the isWhatsapp
     */
    public boolean isIsWhatsapp() {
        return isWhatsapp;
    }

    /**
     * @param isWhatsapp the isWhatsapp to set
     */
    public void setIsWhatsapp(boolean isWhatsapp) {
        this.isWhatsapp = isWhatsapp;
    }
    
}
