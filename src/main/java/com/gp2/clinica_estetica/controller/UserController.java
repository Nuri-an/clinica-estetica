/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.dao.UserDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateAddress;
import com.gp2.clinica_estetica.model.valid.ValidatePeople;
import com.gp2.clinica_estetica.model.valid.ValidatePhoneNumber;
import com.gp2.clinica_estetica.model.valid.ValidateUser;

/**
 *
 * @author nuria
 */
public class UserController {

    private UserDAO repositorio;

    public UserController() {
        repositorio = new UserDAO();
    }

    public User onLogin(String login, String password) {
        ValidateUser valid = new ValidateUser();
        valid.loginValidate(login, password);

        User fecthUser = repositorio.login(login, password);
        if (fecthUser == null) {
            throw new UserException("Error - Nenhum usuário com este 'login'.");
        }

        return fecthUser;
    }

    public void onRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, Integer houseNumber, String password, String securityQuestion, String securityAnswer, String type) {
        try {
            ValidatePeople validPeople = new ValidatePeople();
            validPeople.basicRegisterValidate(name, CPF, birthDate);

            ValidatePhoneNumber validPhone = new ValidatePhoneNumber();
            validPhone.phoneNumberValidate(number, isWhatsapp);

            ValidateAddress validAddress = new ValidateAddress();
            validAddress.addressValidate(zipCode, street, neighborhood, houseNumber);

            ValidateUser valid = new ValidateUser();
            valid.registerValidate(CPF, password, securityQuestion, securityAnswer);

            repositorio.register(name, CPF, birthDate, number, isWhatsapp, zipCode, street, neighborhood, houseNumber, password, securityQuestion, securityAnswer, type);
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }

    public void onCreateSeeds() {
        repositorio.createSeeds();
    }
}
