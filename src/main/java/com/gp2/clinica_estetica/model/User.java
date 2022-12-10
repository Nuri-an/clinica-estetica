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
import javax.persistence.OneToOne;

/**
 *
 * @author nuria
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name="people_id") // nome para coluna foreign key no banco
    private People people;


    public User() {
        this.id = -1;
        this.login = "";
        this.password = "";
        this.securityQuestion = "";
        this.securityAnswer = "";
    }

    public User(String login, String password, String securityQuestion, String securityAnswer) {
        this.login = login;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
    
    public void onLogin(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the doctor
     */
    public People getPeople() {
        return people;
    }

    /**
     * @param people
     */
    public void setPeople(People people) {
        this.people = people;
    }

    /**
     * @return the securityQuestion
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * @param securityQuestion the securityQuestion to set
     */
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    /**
     * @return the securityAnswer
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * @param securityAnswer the securityAnswer to set
     */
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    
}
