/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import java.util.Calendar;


/**
 *
 * @author darloonlino
 */
public class ValidateAttendance {
    public void saveValidate(Calendar startSection, Calendar endSection, String finality){
        if (startSection.getCalendarType() == null) 
            throw new AttendanceException("Error - Forneça o inicio da sessão.");  
        if (endSection.getCalendarType() == null) 
            throw new AttendanceException("Error - Forneça o fim da sessão.");   
        if (finality.equals("")) 
            throw new AttendanceException("Error - Informe a finalidade do procedimento.");  
    }
}
