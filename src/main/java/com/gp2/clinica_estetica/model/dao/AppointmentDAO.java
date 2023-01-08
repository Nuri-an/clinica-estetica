/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.Recipte;
import com.gp2.clinica_estetica.model.exceptions.AppointmentException;
import java.util.Calendar;
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
                appointment.addRecipte(recipteObj);

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
