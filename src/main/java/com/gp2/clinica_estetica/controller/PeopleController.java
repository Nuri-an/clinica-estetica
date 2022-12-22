/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.dao.PeopleDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateAddress;
import com.gp2.clinica_estetica.model.valid.ValidatePF;
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

    public People onFetchPeople(String CPF) {
        ValidatePF validPF = new ValidatePF();
        boolean valid = validPF.verifyCPF(CPF);

        if (valid) {
            try {
                return repositorio.fetchPeople(CPF);
            } catch (UserException e) {
                throw new UserException("Error - Falha ao buscar pessoa.");
            }
        } else {
            return null;
        }
    }

    public void onBasicRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, Integer houseNumber) {
        ValidatePeople validPeople = new ValidatePeople();
        validPeople.basicRegisterValidate(name, CPF, birthDate);

        ValidatePhoneNumber validPhone = new ValidatePhoneNumber();
        validPhone.phoneNumberValidate(number, isWhatsapp);

        ValidateAddress validAddress = new ValidateAddress();
        validAddress.addressValidate(zipCode, street, neighborhood, houseNumber);
        
        try {
            repositorio.basicRegister(name, CPF, birthDate, number, isWhatsapp, zipCode, street, neighborhood, houseNumber);
        } catch (UserException e) {
            throw new UserException("Error - Falha ao realizar cadastro de paciente.");
        }
    }

}
