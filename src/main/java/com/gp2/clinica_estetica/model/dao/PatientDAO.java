/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.User;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author nuria
 */
public class PatientDAO {

    EntityManager entityManager;

    Query qry;
    String sql;

    public PatientDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public void completeRegister(String login, String password, String securityQuestion, String securityAnswer) {
        try {
            sql = " SELECT "
                    + " p.id "
                    + " FROM People p "
                    + " WHERE CPF LIKE :login ";

            qry = this.entityManager.createQuery(sql);
            qry.setParameter("login", login);

            List<Integer> lst = qry.getResultList();

            if (!lst.isEmpty()) {
                User user = new User(login, password, securityQuestion, securityAnswer);
                People people = this.entityManager.find(People.class, lst.get(0));
                people.setUser(user);
                user.setPeople(people);
                Patient patient = new Patient(people);

                this.entityManager.getTransaction().begin();
                this.entityManager.persist(user);
                this.entityManager.persist(patient);
                this.entityManager.merge(people);
                this.entityManager.getTransaction().commit();
            } else {
                throw new UserException("É preciso ser cliente para ter acesso às funções. "
                        + "Entre em contato e agende uma avaliação! "
                        + "Telefone: 3451-8620");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
