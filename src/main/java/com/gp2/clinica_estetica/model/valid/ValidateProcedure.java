/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.exceptions.ProcedureException;

/**
 *
 * @author nuria
 */
public class ValidateProcedure {    
    public void saveValidate(Object name, String price){        
        if (name == null) 
            throw new ProcedureException("Error - Selecione ou crie um procedimento.");  
        if (price == null || price.equals("")) 
            throw new ProcedureException("Error - Informe o preço do procedimento.");  
    }
}
