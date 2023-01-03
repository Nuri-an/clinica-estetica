/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.Recipte;
import com.gp2.clinica_estetica.model.exceptions.AppointmentException;
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

            Appointment appointment = this.entityManager.find(Appointment.class, id);

            if (appointment != null) {
                Recipte recipteObj = new Recipte(recipte);
                appointment.addRecipte(recipteObj);

                this.entityManager.getTransaction().begin();
                this.entityManager.merge(appointment);
                this.entityManager.getTransaction().commit();
            } else {
                throw new AppointmentException("Não foi posspivel encontrar esse atendimento!");
            }
        } catch (AppointmentException e) {
            System.err.println("atendimento " + e.getMessage());
        }

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
    public Appointment find(int id) {
        sql = " SELECT a "
                + " FROM Appointment a "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql, Appointment.class);
        qry.setParameter("id", id);
        
        List<Appointment> lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
}
