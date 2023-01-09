/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.dao.AppointmentDAO;
import com.gp2.clinica_estetica.model.exceptions.AppointmentException;
import com.gp2.clinica_estetica.model.valid.ValidateAppointment;
import com.gp2.clinica_estetica.model.valid.ValidateAttendance;
import com.gp2.clinica_estetica.model.valid.ValidateIDao;
import com.gp2.clinica_estetica.model.valid.ValidatePF;
import java.util.List;
import javax.swing.JTable;

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
            validAppointment.sendDocValidate(id, recipte);

            repositorio.sendRecipte(id, recipte);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - Falha ao realizar upload de receita.");
        }
    }

    public void onSendContract(Integer id, String contract) {
            ValidateAppointment validAppointment = new ValidateAppointment();
            validAppointment.sendDocValidate(id, contract);

        try {
            repositorio.sendContract(id, contract);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - Falha ao realizar upload de contrato.");
        }
    }

    public void onPutSection(Integer id, int numSection) {
        try {
            repositorio.putSection(id, numSection);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - Falha ao atualizar número da sessão.");
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

    public Appointment onSave(Attendance attendance, int numberOfSessions) {
        Double budget = attendance.getProcedure().getPrice() * numberOfSessions;
        Appointment appointment = new Appointment(attendance, numberOfSessions, 1, budget);
        attendance.setAppointment(appointment);

        ValidateAttendance validAttendance = new ValidateAttendance();
        validAttendance.scheduleValidate(attendance.getStartDateTime(), attendance.getEndDateTime(), null);

        ValidateAppointment validAppointment = new ValidateAppointment();
        validAppointment.saveValidate(numberOfSessions, budget);

        try {
            return repositorio.saveAppointement(appointment);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - Falha ao buscar atendimento.");
        }

    }

    public void onFindAllByPatient(JTable grd, String cpf, String procedureName) {
        ValidatePF validPf = new ValidatePF();
        validPf.verifyCPF(cpf);
        try {
            List<Attendance> listAll = repositorio.findAllByPatient(cpf, procedureName);
            TMListAttendance TMList = new TMListAttendance(listAll);
            TMList.putColumns(0, -100, 1, 2);
            Util.jTableShow(grd, TMList, null);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - erro buscar atendimentos.");
        }
    }

    public void onFindAllByDoctor(JTable grd, String cpf, String procedureName) {
        ValidatePF validPf = new ValidatePF();
        validPf.verifyCPF(cpf);
        try {
            List<Attendance> listAll = repositorio.findAllByDoctor(cpf, procedureName);
            TMListAttendance TMList = new TMListAttendance(listAll);
            TMList.putColumns(0, -100, 1, 2);
            Util.jTableShow(grd, TMList, null);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - erro buscar atendimentos.");
        }
    }

    public void onFindAllWithoutContract(JTable grd, String cpf, String procedureName) {
        ValidatePF validPf = new ValidatePF();
        validPf.verifyCPF(cpf);

        try {
            List<Attendance> listAll = repositorio.findAllWithoutContract(cpf, procedureName);
            TMListAttendance TMList = new TMListAttendance(listAll);
            TMList.putColumns(0, 1, 2, 3);
            Util.jTableShow(grd, TMList, null);
        } catch (AppointmentException e) {
            throw new AppointmentException("Error - erro buscar atendimentos.");
        }
    }

}
