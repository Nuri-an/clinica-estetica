/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.PhoneNumber;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidatePhoneNumber {
    
    public PhoneNumber phoneNumberValidate(String number, boolean isWhatsapp){
        PhoneNumber phoneNumber = new PhoneNumber();     
        
        if (number.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'número de telefone'.");                
        phoneNumber.setNumber(number); 
        phoneNumber.setIsWhatsapp(isWhatsapp);

        return phoneNumber;
    }
}
