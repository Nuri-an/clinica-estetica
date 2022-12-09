/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidatePatient {
    ValidateUtil validateUtil = new ValidateUtil();

    public Patient completeRegisterValidate(String securityQuestion, String securityAnswer) {
        Patient patient = new Patient();
        if (securityQuestion.isEmpty())
            throw new UserException("Error - Campo vazio: 'pergunta de segurança'.");
        patient.setSecurityQuestion(securityQuestion);

        if (securityAnswer.isEmpty())
            throw new UserException("Error - Campo vazio: 'resposta de segurança'.");
        patient.setSecurityAnswer(securityAnswer);

        return patient;
    }

    public Patient basicRegisterValidate(String name, String CPF, String birthDate) {
        Patient patient = new Patient();
        if (name.isEmpty())
            throw new UserException("Error - Campo vazio: 'nome'.");
        if (name.length() < 3)
            throw new UserException("Error - Nome muito curto.");
        patient.setName(name);

        if (CPF.isEmpty())
            throw new UserException("Error - Campo vazio: 'CPF'.");
        if (!validateUtil.isCPF(CPF))
            throw new UserException("Error - CPF inválido.");
        patient.setCPF(CPF);

        if (birthDate.isEmpty())
            throw new UserException("Error - Campo vazio: 'data de nascimneto'.");
        if (!validateUtil.isDate(birthDate))
            throw new UserException("Error - Data de nascimento inválida.");
        patient.setBirthDate(birthDate);

        return patient;
    }

}
