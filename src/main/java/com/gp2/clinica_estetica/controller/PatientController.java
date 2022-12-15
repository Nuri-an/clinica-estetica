/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.dao.PatientDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateUser;

/**
 *
 * @author nuria
 */
public class PatientController {

    private PatientDAO repositorio;

    public PatientController() {
        repositorio = new PatientDAO();
    }

    public void onCompleteRegister(String login, String password, String securityQuestion, String securityAnswer) {
        ValidateUser validUser = new ValidateUser();
        validUser.registerValidate(login, password, securityQuestion, securityAnswer);
        
        try {
            repositorio.completeRegister(login, password, securityQuestion, securityAnswer);
        } catch (UserException e) {
            throw new UserException("Error - Falha ao realizar cadastro.");
        }
    }
}
