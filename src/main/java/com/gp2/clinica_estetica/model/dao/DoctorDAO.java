/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.User;
import javax.persistence.EntityManager;

/**
 *
 * @author nuria
 */
public class DoctorDAO {

    EntityManager entityManager;

    public DoctorDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public void register(String login, String password, String securityQuestion, String securityAnswer) {
        User user = new User(login, password);
        Doctor doctor = new Doctor(user, securityQuestion, securityAnswer);
        user.setDoctor(doctor);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(doctor);
        this.entityManager.getTransaction().commit();
    }

}
