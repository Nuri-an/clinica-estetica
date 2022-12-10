/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author nuria
 */
@Entity
public class People implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String CPF;
    private String birthDate;

    @JoinColumn(name = "address_id") // nome para coluna foreign key no banco
    @ManyToOne
    private Address address;

    @JoinColumn(name = "phone_id") // nome para coluna foreign key no banco
    @OneToOne
    private PhoneNumber phoneNumber;

    @OneToOne(mappedBy = "people", cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // nome para coluna foreign key no banco
    private User user;

    @OneToOne(mappedBy = "people", cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id") // nome para coluna foreign key no banco
    private Doctor doctor;

    @OneToOne(mappedBy = "people", cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id") // nome para coluna foreign key no banco
    private Patient patient;

    @OneToOne(mappedBy = "people", cascade = CascadeType.ALL)
    @JoinColumn(name = "attendant_id") // nome para coluna foreign key no banco
    private Attendant attendant;
    
    // initialize
    public People() {
        this.name = "";
        this.CPF = "000.000.000-00";
        this.birthDate = "00/00/0000";
        this.address = new Address();
        this.phoneNumber = new PhoneNumber();
        this.user = new User();
    }
    
    // pattern constructor without user
    public People(String name, String CPF, String birthDate, PhoneNumber phoneNumber, Address address) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    // constructor for register
    public People(String name, String CPF, String birthDate, PhoneNumber phoneNumber, Address address, User user) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
    }
    
    // constructor for new patient doctor
    public People(String name, String CPF, String birthDate, PhoneNumber phoneNumber, Address address, User user, Doctor doctor) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
        this.doctor = doctor;
    }
    
    // constructor for new patient people
    public People(String name, String CPF, String birthDate, PhoneNumber phoneNumber, Address address, User user, Patient patient) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.user = user;
        this.patient = patient;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the CPF
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * @param CPF the CPF to set
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @return the attendant
     */
    public Attendant getAttendant() {
        return attendant;
    }

    /**
     * @param attendant the attendant to set
     */
    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }
}
