/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.exceptions.AppointmentException;

/**
 *
 * @author nuria
 */
public class ValidateAppointment {
    
    public void sendRecipteValidate(Integer id, String recipte){       
        if (id.toString().isEmpty()) 
            throw new AppointmentException("Error - Atendimento sem referência.");  
        
        if (recipte.isEmpty()) 
            throw new AppointmentException("Error - Selecione uma receita."); 
        
    }
}
