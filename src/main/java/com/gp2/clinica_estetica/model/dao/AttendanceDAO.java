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

    @Override
    public void save(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Attendance> findAllAttendances() {
        sql = " SELECT a "
                + " FROM Attendance a ";

        qry = this.entityManager.createQuery(sql);

        List lst = qry.getResultList();
        return lst;
    }
    
}
