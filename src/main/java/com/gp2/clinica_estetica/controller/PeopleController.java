/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.dao.PeopleDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateAddress;
import com.gp2.clinica_estetica.model.valid.ValidatePeople;
import com.gp2.clinica_estetica.model.valid.ValidatePhoneNumber;

/**
 *
 * @author nuria
 */
public class PeopleController {

    private PeopleDAO repositorio;

    public PeopleController() {
        repositorio = new PeopleDAO();
    }

    public void onBasicRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood) {
        try {
            ValidatePhoneNumber validPhone = new ValidatePhoneNumber();
            validPhone.phoneNumberValidate(number, isWhatsapp);

            ValidateAddress validAddress = new ValidateAddress();
            validAddress.addressValidate(zipCode, street, neighborhood);

            ValidatePeople validPeople = new ValidatePeople();
            validPeople.basicRegisterValidate(name, CPF, birthDate);

            repositorio.basicRegister(name, CPF, birthDate, number, isWhatsapp, zipCode, street, neighborhood);
        } catch (UserException e) {
            throw new UserException("Error - Falha ao realizar cadastro de paciente.");
        }
    }

}
