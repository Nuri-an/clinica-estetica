/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.dao.AttendanceDAO;
import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import com.gp2.clinica_estetica.model.valid.ValidateAttendance;
import com.gp2.clinica_estetica.model.valid.ValidateIDao;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author darloonlino
 */
public class AttendanceController {

    private AttendanceDAO repositorio;

    public AttendanceController() {
        repositorio = new AttendanceDAO();
    }

    public void onSave(Object obj) {
        Attendance att = (Attendance) obj;
        ValidateAttendance validAttendance = new ValidateAttendance();
        validAttendance.scheduleValidate(att.getStartDateTime(), att.getEndDateTime(), null);
        validAttendance.saveValidate(att.getStartDateTime(), att.getEndDateTime(), att.getFinality());

        try {
            repositorio.save(obj);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - salvar atendimento.");
        }
    }

    public void onEditSchedule(int id, Calendar startSection, Calendar endSection) {
        ValidateIDao validDao = new ValidateIDao();
        validDao.find(id);
        ValidateAttendance validAttendance = new ValidateAttendance();
        validAttendance.scheduleValidate(startSection, endSection, id);

        try {
            repositorio.editSchedule(id, startSection, endSection);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - editar atendimento.");
        }
    }

    public List<Attendance> onFindAllAttendances() {
        try {
            return repositorio.findAllAttendances();
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro buscar atendimento.");
        }
    }

    public void onFindAllByPatient(JTable grd, String type, String pacientName) {
        ValidateAttendance validAttendance = new ValidateAttendance();
        validAttendance.findTypeValidate(type);
        try {
            List<Attendance> listAll = repositorio.findAllByPatient(type, pacientName);
            TMListAttendance TMList = new TMListAttendance(listAll);
            TMList.putColumns(0, 1, 2, -100);
            Util.jTableShow(grd, TMList, null);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro buscar atendimentos.");
        }
    }

    public void onFindAllFilter(JTable grd, String cpf, String type, List<String> date, String procedureName, double price) {
        try {
            List<Attendance> listAll;
            if(type.equals("Avaliacao"))
                listAll = repositorio.findAllAttendancesFilter(cpf, date, procedureName, price);
            else listAll = repositorio.findAllAppointmentsFilter(cpf, date, procedureName, price);
            TMListAttendance TMList = new TMListAttendance(listAll);
            TMList.putColumns(0, -100, 1, 2);
            Util.jTableShow(grd, TMList, null);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro buscar atendimentos.");
        }
    }

    public List<Attendance> onFindAll() {
        try {
            return repositorio.findAll();
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro buscar atendimento.");
        }
    }

    public Attendance onFind(Integer id) {
        ValidateIDao validDao = new ValidateIDao();
        validDao.find(id);
        try {
            return repositorio.find(id);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro buscar atendimento.");
        }
    }

    public boolean onDelete(Integer id) {
        ValidateIDao validDao = new ValidateIDao();
        validDao.find(id);
        try {
            return repositorio.delete(id);
        } catch (AttendanceException e) {
            throw new AttendanceException("Error - erro deletar atendimento.");
        }
    }

}
