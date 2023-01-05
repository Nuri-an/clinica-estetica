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
public class ValidateContract {  
    public void searchValidate(String search){         
        if (search == null) 
            throw new UserException("Error - Campo de pesquisa nulo.");  
    }
}
