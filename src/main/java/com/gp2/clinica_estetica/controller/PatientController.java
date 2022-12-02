/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.dao.PatientDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateAddress;
import com.gp2.clinica_estetica.model.valid.ValidatePatient;
import com.gp2.clinica_estetica.model.valid.ValidatePhoneNumber;
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
        validUser.authValidate(login, password);

        ValidatePatient validDoctor = new ValidatePatient();
        validDoctor.completeRegisterValidate(securityQuestion, securityAnswer);

        try {
            repositorio.completeRegister(login, password, securityQuestion, securityAnswer);
        } catch (UserException e) {
            throw new UserException("Error - Falha ao realizar cadastro.");
        }
    }
    

    public void onBasicRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood) {
        ValidatePhoneNumber validPhone = new ValidatePhoneNumber();
        validPhone.phoneNumberValidate(number, isWhatsapp);
        
        ValidateAddress validAddress = new ValidateAddress();
        validAddress.addressValidate(zipCode, street, neighborhood);

        ValidatePatient validPatient = new ValidatePatient();
        validPatient.basicRegisterValidate(name, CPF, birthDate);

        try {
            repositorio.basicRegister(name, CPF, birthDate, number, isWhatsapp, zipCode, street, neighborhood);
        } catch (UserException e) {
            throw new UserException("Error - Falha ao realizar cadastro de paciente.");
        }
    }
}
