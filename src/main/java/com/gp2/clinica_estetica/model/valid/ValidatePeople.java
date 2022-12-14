/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.dao.PeopleDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidatePeople {
    
    public void basicRegisterValidate(String name, String CPF, String birthDate){ 
        PeopleDAO peopleDao = new PeopleDAO();
        
        if (name.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'nome'.");  
        
        if (CPF.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'CPF'.");  
        
        if(peopleDao.hasPeopleWithCpf(CPF))
            throw new UserException("Error - Este CPF já está sendo utilizdo por outra pessoa.");
        
        ValidatePF validCPF = new ValidatePF();
        if(!validCPF.verifyCPF(CPF))
          throw new UserException("Error - CPF inválido!");
        
        if (birthDate.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'data de nascimneto'.");      
    }
}
