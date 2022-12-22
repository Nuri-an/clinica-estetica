/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.Address;
import com.gp2.clinica_estetica.model.exceptions.UserException;

/**
 *
 * @author nuria
 */
public class ValidateAddress {  
    public Address addressValidate(String zipCode, String street, String neighborhood, Integer houseNumber){
        Address address = new Address();     
        
        if (zipCode.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'CEP'.");                
        address.setZipCode(zipCode);  
        
        if (street.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'rua'.");                
        address.setStreet(street);  
        
        if (neighborhood.isEmpty()) 
            throw new UserException("Error - Campo vazio: 'bairro'.");                
        address.setNeighborhood(neighborhood);
        
        if (houseNumber == null) 
            throw new UserException("Error - Campo vazio: 'número da casa'.");                
        address.setHouseNumber(houseNumber);

        return address;
    }
}
