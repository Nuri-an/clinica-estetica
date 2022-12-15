/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.dao.UserDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidateUser {
    
    public static boolean isValidPassword(String password) {
        String regexExp = "^(?=.*[0-9])"
                       + ".{8,}$";
  
        return password.matches(regexExp);
    }

    
    public User loginValidate(String login, String password){
        User user = new User();

        if (login.isEmpty())
            throw new UserException("Error - Campo vazio: 'login'.");
        
        ValidatePF validCPF = new ValidatePF();
        if(!validCPF.verifyCPF(login))
          throw new UserException("Error - CPF para login inválido!");
        user.setLogin(login);
        
        if (password.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'senha'.");  
        if (!isValidPassword(password)) 
            throw new UserException("Error - Senha inválida.");  
            
        user.setPassword(password);

        return user;
    }
    
    public void registerValidate(String login, String password, String securityQuestion, String securityAnswer){
        UserDAO userDao = new UserDAO();
        
        if (login.isEmpty())
            throw new UserException("Error - Campo vazio: 'login'.");
        
        ValidatePF validCPF = new ValidatePF();
        if(!validCPF.verifyCPF(login))
          throw new UserException("Error - CPF para login inválido!");
        
        if(userDao.hasUserWithCpf(login))
            throw new UserException("Error - Este CPF já está sendo utilizado por outro usuário."); 
        
        if (securityQuestion.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'pergunta de segurança'.");   
        
        if (securityAnswer.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'resposta de segurança'.");  
        
        if (password.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'senha'.");  
        
        if (!isValidPassword(password)) 
            throw new UserException("Error - Informe ao menos 1 número para a senha e o menos 8 caracteres.");  
        
    }
    
}
