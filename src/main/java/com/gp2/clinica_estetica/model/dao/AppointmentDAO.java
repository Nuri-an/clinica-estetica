/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.Contract;
import com.gp2.clinica_estetica.model.Recipte;
import com.gp2.clinica_estetica.model.exceptions.AppointmentException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author nuria
 */
public class AppointmentDAO implements IDao {    

    EntityManager entityManager;

    Query qry;
    String sql;

    public AppointmentDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public void sendRecipte(Integer id, String recipte) {
        try {

            Appointment appointment = this.find(id);

            if (appointment != null) {
                Recipte recipteObj = new Recipte(recipte);
                recipteObj.setAppointment(appointment);
                appointment.addRecipte(recipteObj);

                this.entityManager.getTransaction().begin();
                this.entityManager.persist(recipteObj);
                this.entityManager.merge(appointment);
                this.entityManager.getTransaction().commit();
            } else {
                throw new AppointmentException("Não foi possível encontrar esse atendimento!");
            }
        } catch (AppointmentException e) {
            System.err.println("atendimento " + e.getMessage());
        }

    }
    public void sendContract(Integer id, String contract) {
        try {

            Appointment appointment = this.find(id);

            if (appointment != null) {
                Contract contractObj = new Contract(new Date(), false, contract);
                contractObj.setAppointment(appointment);
                contractObj.setDoctor(appointment.getAttendance().getDoctor());
                contractObj.setPatient(appointment.getAttendance().getPatient());
                contractObj.setProcedure(appointment.getAttendance().getProcedure());
                appointment.setContract(contractObj);

                this.entityManager.getTransaction().begin();
                this.entityManager.persist(contractObj);
                this.entityManager.merge(appointment);
                this.entityManager.getTransaction().commit();
            } else {
                throw new AppointmentException("Não foi possível encontrar esse atendimento!");
            }
        } catch (AppointmentException e) {
            System.err.println("atendimento " + e.getMessage());
        }

    }
    
    public void putSection(Integer id, int numSction) {
        try {

            Appointment appointment = this.find(id);

            if (appointment != null) {
                appointment.setCurrentSession(numSction);

                this.entityManager.getTransaction().begin();
                this.entityManager.merge(appointment);
                this.entityManager.getTransaction().commit();
            } else {
                throw new AppointmentException("Não foi possível encontrar esse atendimento!");
            }
        } catch (AppointmentException e) {
            System.err.println("atendimento " + e.getMessage());
        }

    }


    public Appointment saveAppointement(Object obj) {
        Appointment app = (Appointment) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(app.getAttendance());
        this.entityManager.persist(app);
        this.entityManager.getTransaction().commit();
        
        return app;
    }

    @Override
    public boolean delete(int id) {
        try {
            Appointment appointment = this.find(id);
            this.entityManager.getTransaction().begin();
            sql = " DELETE FROM Appointment "
                    + " WHERE id=:id";
            qry = this.entityManager.createQuery(sql);
            qry.setParameter("id", appointment.getId());
            qry.executeUpdate();
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    

    public List<Attendance> findAllByPatient(String cpf, String procedureName) {
        sql = " SELECT a "
                + " FROM Attendance a "
                + " INNER JOIN a.patient pat "
                + " INNER JOIN pat.people p "
                + " INNER JOIN a.procedure proc "
                + " INNER JOIN a.appointment app "
                + " WHERE a.type LIKE :type"
                + " AND p.CPF LIKE :cpf"
                + " AND app.contract IS NOT NULL"
                + " AND proc.name LIKE CONCAT('%',CONCAT(:name, '%')) ";

        qry = this.entityManager.createQuery(sql, Attendance.class);
        qry.setParameter("type", "Consulta");
        qry.setParameter("cpf", cpf);
        qry.setParameter("name", procedureName);

        List<Attendance> lst = qry.getResultList();
        return lst;
    }

    public List<Attendance> findAllByDoctor(String cpf, String procedureName) {
        sql = " SELECT a "
                + " FROM Attendance a "
                + " INNER JOIN a.doctor doc "
                + " INNER JOIN doc.people p "
                + " INNER JOIN a.procedure proc "
                + " INNER JOIN a.appointment app "
                + " WHERE a.type LIKE :type"
                + " AND p.CPF LIKE :cpf"
                + " AND app.contract IS NOT NULL"
                + " AND proc.name LIKE CONCAT('%',CONCAT(:name, '%')) ";

        qry = this.entityManager.createQuery(sql, Attendance.class);
        qry.setParameter("type", "Consulta");
        qry.setParameter("cpf", cpf);
        qry.setParameter("name", procedureName);

        List<Attendance> lst = qry.getResultList();
        return lst;
    }

    public List<Attendance> findAllWithoutContract(String cpf, String procedureName) {
        sql = " SELECT a "
                + " FROM Attendance a "
                + " INNER JOIN a.doctor doc "
                + " INNER JOIN doc.people p "
                + " INNER JOIN a.procedure proc "
                + " INNER JOIN a.appointment app "
                + " WHERE a.type LIKE :type"
                + " AND p.CPF LIKE :cpf"
                + " AND app.contract IS NULL"
                + " AND proc.name LIKE CONCAT('%',CONCAT(:name, '%')) ";

        qry = this.entityManager.createQuery(sql, Attendance.class);
        qry.setParameter("type", "Consulta");
        qry.setParameter("cpf", cpf);
        qry.setParameter("name", procedureName);

        List<Attendance> lst = qry.getResultList();
        return lst;
    }


    @Override
    public Appointment find(int id) {
        Appointment appointment = this.entityManager.find(Appointment.class, id);

        return appointment;
    }

    @Override
    public void save(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
