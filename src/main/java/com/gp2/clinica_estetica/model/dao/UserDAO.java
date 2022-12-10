/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Address;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.PhoneNumber;
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

    public void register(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, String login, String password, String securityQuestion, String securityAnswer, String type) {
        PhoneNumber phoneNumber = new PhoneNumber(number, isWhatsapp);
        Address address = new Address(zipCode, street, neighborhood);
        User user = new User(login, password, securityQuestion, securityAnswer);
        People people = new People(name, CPF, birthDate, phoneNumber, address, user);

        if (type.equals("Doctor")) {
            Doctor doctor = new Doctor();
            people.setDoctor(doctor);
        } else if (type.equals("Patient")) {
            Patient patient = new Patient();
            people.setPatient(patient);
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(phoneNumber);
        this.entityManager.persist(address);
        this.entityManager.persist(people);
        this.entityManager.getTransaction().commit();
    }
    

    public void createSeeds() {
        
    }
}
