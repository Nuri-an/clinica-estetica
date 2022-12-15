/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

/**
 *
 * @author nuria
 */
public class ValidateIDao {
    
    public void find(Integer id){       
        if (id.toString().isEmpty()) 
            throw new Error("Error - Atendimento sem referência.");  
         
        
    }
    
}
