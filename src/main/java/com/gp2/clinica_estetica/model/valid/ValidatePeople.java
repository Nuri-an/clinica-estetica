/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidatePeople {
    
    public void basicRegisterValidate(String name, String CPF, String birthDate){       
        if (name.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'nome'.");  
        
        if (CPF.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'CPF'."); 
        
        if (birthDate.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'data de nascimneto'.");      
    }
}
