/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.MedicalProcedure;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.dao.AttendanceDAO;
import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import com.gp2.clinica_estetica.model.valid.ValidateAttendance;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author darloonlino
 */
public class AttendanceController {

    private AttendanceDAO repositorio;

    public AttendanceController() {
        repositorio = new AttendanceDAO();
    }

    public void onSave(Patient patient, Doctor doctor, MedicalProcedure procedure, String type, Calendar startSection, Calendar endSection, String finality) {
        ValidateAttendance validAttendance = new ValidateAttendance();
        validAttendance.saveValidate(startSection, endSection, finality);
          
        try {
            repositorio.save(patient, doctor, procedure, type, startSection, endSection, finality);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - salvar atendimento.");
        }
    }

    public List<Attendance> onFindAll() {        
        try {
            return repositorio.findAllAttendances();
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro buscar atendimento.");
        }
    }
    
}