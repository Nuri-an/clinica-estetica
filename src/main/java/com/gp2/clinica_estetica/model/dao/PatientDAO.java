/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
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
            PeopleDAO peopleDao = new PeopleDAO();
            People people = peopleDao.fetchPeople(login);

            if (people != null) {
                User user = new User(login, password, securityQuestion, securityAnswer, people);
                people.setUser(user);

                this.entityManager.getTransaction().begin();
                this.entityManager.persist(user);
                this.entityManager.merge(people);
                this.entityManager.getTransaction().commit();
            } else {
                throw new UserException("É preciso ser cliente para ter acesso às funções. "
                        + "Entre em contato e agende uma avaliação! "
                        + "Telefone: 3451-8620");
            }
        } catch (UserException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<People> findAllByCPF(String cpf) {
        sql = " SELECT p "
                + " FROM People p "
                + " WHERE patient_id is not null "
                + " AND CPF LIKE CONCAT('%',CONCAT(:cpf, '%')) ";

        qry = this.entityManager.createQuery(sql, People.class);
        qry.setParameter("cpf", cpf);

        List<People> lst = qry.getResultList();
        return lst;
    }

    /**
     *
     * @return
     */
    public List<People> findAllPatients() {
        sql = " SELECT p "
                + " FROM People p "
                + " WHERE patient_id is not null ";

        qry = this.entityManager.createQuery(sql, People.class);

        List<People> lst = qry.getResultList();
        return lst;
    }
}
