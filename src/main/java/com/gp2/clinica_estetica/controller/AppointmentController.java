/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.dao.AppointmentDAO;
import com.gp2.clinica_estetica.model.exceptions.AppointmentException;
import com.gp2.clinica_estetica.model.valid.ValidateAppointment;
import com.gp2.clinica_estetica.model.valid.ValidateIDao;

/**
 *
 * @author nuria
 */
public class AppointmentController {

    private AppointmentDAO repositorio;

    public AppointmentController() {
        repositorio = new AppointmentDAO();
    }

    public void onSendRecipte(Integer id, String recipte) {
        try {
            ValidateAppointment validAppointment = new ValidateAppointment();
            validAppointment.sendRecipteValidate(id, recipte);

            repositorio.sendRecipte(id, recipte);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - Falha ao realizar upload de receita.");
        }
    }

    public Appointment onFetchAppointment(Integer id) {
        try {
            ValidateIDao validIDao = new ValidateIDao();
            validIDao.find(id);

            return repositorio.find(id);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - Falha ao buscar atendimento.");
        }

    }

}