/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidateDoctor {
    
    public Doctor registerValidate(String securityQuestion, String securityAnswer){
        Doctor doctor = new Doctor();        
        if (securityQuestion.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'pergunta de segurança'.");                
        doctor.setSecurityQuestion(securityQuestion);
        
        if (securityAnswer.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'resposta de segurança'.");                
        doctor.setSecurityAnswer(securityAnswer);

        return doctor;
    }
}
