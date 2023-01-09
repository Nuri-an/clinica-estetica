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
    
    public void sendDocValidate(Integer id, String recipte){       
        if (id.toString().isEmpty()) 
            throw new AppointmentException("Error - Atendimento sem referência.");  
        
        if (recipte.isEmpty()) 
            throw new AppointmentException("Error - Selecione um arquivo."); 
        
    }
    
    public void saveValidate(Integer numberOfSessions, Double budget){       
        if (numberOfSessions.toString().isEmpty()) 
            throw new AppointmentException("Error - Informe o número de sessões.");  
        
        if (budget.toString().isEmpty()) 
            throw new AppointmentException("Error - Informe o valor total da consulta."); 
        
    }
}
