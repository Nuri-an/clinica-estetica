/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidateUser {
    
    public User authValidate(String login, String password){
        User user = new User();
        if (login.isEmpty())
            throw new UserException("Error - Campo vazio: 'login'.");
        user.setLogin(login);
        
        if (password.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'senha'.");                
        user.setPassword(password);

        return user;
    }
    
}
