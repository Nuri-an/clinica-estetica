/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.dao.UserDAO;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import com.gp2.clinica_estetica.model.valid.ValidateUser;

/**
 *
 * @author nuria
 */
public class UserController {

    private UserDAO repositorio;

    public UserController() {
        repositorio = new UserDAO();
    }

    public User onLogin(String login, String password) {
        ValidateUser valid = new ValidateUser();
        valid.authValidate(login, password);

        User fecthUser = repositorio.login(login, password);
        if (fecthUser == null) {
            throw new UserException("Error - Nenhum usuário com este 'login'.");
        }

        return fecthUser;
    }

    public void onCreateSeeds() {
        repositorio.createSeeds();
    }

}
