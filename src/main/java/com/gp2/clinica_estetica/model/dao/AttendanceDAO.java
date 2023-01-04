/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.MedicalProcedure;
import com.gp2.clinica_estetica.model.Patient;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author darloonlino
 */
public class AttendanceDAO implements IDao {

    EntityManager entityManager;

    Query qry;
    String sql;

    public AttendanceDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public void save(Patient patient, Doctor doctor, MedicalProcedure procedure, String type, Calendar startSection, Calendar endSection, String finality) {
        Attendance attendance = new Attendance(type, startSection, endSection, finality);
        Patient persistePatient = this.entityManager.find(Patient.class, patient.getId());
        Doctor persisteDoctor = this.entityManager.find(Doctor.class, doctor.getId());
        MedicalProcedure persisteProcedure = this.entityManager.find(MedicalProcedure.class, procedure.getId());
        attendance.setPatient(persistePatient);
        attendance.setDoctor(persisteDoctor);
        attendance.setProcedure(persisteProcedure);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(attendance);
        this.entityManager.getTransaction().commit();
    }

    public void editSchedule(int id, Calendar startSection, Calendar endSection) {
        Attendance attendance = this.find(id);
        attendance.setStartDateTime(startSection);
        attendance.setEndDateTime(endSection);

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(attendance);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void save(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        try {
            Attendance attendance = this.find(id);
            this.entityManager.getTransaction().begin();
            sql = " DELETE FROM Attendance "
                    + " WHERE id=:id";
            qry = this.entityManager.createQuery(sql);
            qry.setParameter("id", attendance.getId());
            qry.executeUpdate();
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Attendance find(int id) {
        Attendance attendance = this.entityManager.find(Attendance.class, id);

        return attendance;
    }

    public List<Attendance> findAll() {
        sql = " SELECT a "
                + " FROM Attendance a ";

        qry = this.entityManager.createQuery(sql, Attendance.class);

        List<Attendance> lst = qry.getResultList();
        return lst;
    }

    public List<Attendance> findAllAttendances() {
        sql = " SELECT a "
                + " FROM Attendance a "
                + " WHERE a.type LIKE :type";

        qry = this.entityManager.createQuery(sql);
        qry.setParameter("type", "Avaliacao");

        List lst = qry.getResultList();
        return lst;
    }

}
