/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author nuria
 */
public class UserDAO {

    EntityManager entityManager;

    Query qry;
    String sql;

    public UserDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public Object login(String login, String password) {
        try {
            sql = " SELECT "
                    + " u.login, "
                    + " u.password "
                    + " FROM User u "
                    + " WHERE login LIKE :login "
                    + " AND password LIKE :password ";

            qry = this.entityManager.createQuery(sql);
            qry.setParameter("login", login);
            qry.setParameter("password", password);

            List lst = qry.getResultList();

            if (lst.isEmpty()) {
                return null;
            } else {
                return lst.get(0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public void createAttendantSeed() {
        try {
            String attendantEmail = "atendente@clinica.com";
            String attendantPassword = "Clinica@2022";

            sql = " SELECT "
                    + " u.login, "
                    + " u.password "
                    + " FROM User u "
                    + " WHERE login LIKE :login "
                    + " AND password LIKE :password ";

            qry = this.entityManager.createQuery(sql);
            qry.setParameter("login", attendantEmail);
            qry.setParameter("password", attendantPassword);

            List lst = qry.getResultList();

            if (lst.isEmpty()) {
                User attendant = new User(attendantEmail, attendantPassword);

                this.entityManager.getTransaction().begin();
                this.entityManager.persist(attendant);
                this.entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.err.println("Seed Atendente: " + e.getMessage());
        }
    }

    public void createSeeds() {
        this.createAttendantSeed();
    }
}
