/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Address;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.PhoneNumber;
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
                    + " FROM Patient p "
                    + " WHERE CPF LIKE :login ";

            qry = this.entityManager.createQuery(sql);
            qry.setParameter("login", login);

            List<Integer> lst = qry.getResultList();

            if (!lst.isEmpty()) {
                User user = new User(login, password);
                Patient patient = this.entityManager.find(Patient.class, lst.get(0));
                patient.setUser(user);
                patient.setSecurityQuestion(securityQuestion);
                patient.setSecurityAnswer(securityAnswer);
                user.setPatient(patient);

                this.entityManager.getTransaction().begin();
                this.entityManager.merge(patient);
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

    public void basicRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood) {
        PhoneNumber phoneNumber = new PhoneNumber(number, isWhatsapp);
        Address address = new Address(zipCode, street, neighborhood);
        Patient patient = new Patient(name, CPF, birthDate, phoneNumber, address);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(phoneNumber);
        this.entityManager.persist(address);
        this.entityManager.persist(patient);
        this.entityManager.getTransaction().commit();
    }
}
