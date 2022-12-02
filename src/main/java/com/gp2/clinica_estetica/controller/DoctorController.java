/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.dao.DoctorDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateDoctor;
import com.gp2.clinica_estetica.model.valid.ValidateUser;

/**
 *
 * @author nuria
 */
public class DoctorController {

    private DoctorDAO repositorio;

    public DoctorController() {
        repositorio = new DoctorDAO();
    }

    public void onRegister(String login, String password, String securityQuestion, String securityAnswer) {
        ValidateUser validUser = new ValidateUser();
        validUser.authValidate(login, password);

        ValidateDoctor validDoctor = new ValidateDoctor();
        validDoctor.registerValidate(securityQuestion, securityAnswer);

        try {
            repositorio.register(login, password, securityQuestion, securityAnswer);
        } catch (UserException e) {
            throw new UserException("Error - Falha ao realizar cadastro.");
        }
    }
}
