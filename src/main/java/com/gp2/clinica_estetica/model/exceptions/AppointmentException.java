/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.exceptions;

/**
 *
 * @author nuria
 */
public class AppointmentException extends RuntimeException {

    public AppointmentException(String msg) {
        super(msg);
    }
}
