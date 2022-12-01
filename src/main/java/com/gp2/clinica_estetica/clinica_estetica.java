/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica;

import com.gp2.clinica_estetica.controller.UserController;

/**
 *
 * @author nuria
 */
public class clinica_estetica {

    public static void main(String[] args) {
        UserController userCon = new UserController();
        userCon.onCreateSeeds();
    }
}
