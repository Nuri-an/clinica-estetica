/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Address;
import com.gp2.clinica_estetica.model.Attendant;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.People;
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
public class UserDAO {

    EntityManager entityManager;

    Query qry;
    String sql;

    public UserDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public User login(String login, String password) {
        try {
            sql = " SELECT "
                    + " u "
                    + " FROM User u "
                    + " WHERE login LIKE :login "
                    + " AND password LIKE :password ";

            qry = this.entityManager.createQuery(sql, User.class);
            qry.setParameter("login", login);
            qry.setParameter("password", password);

            List<User> lst = qry.getResultList();

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

    public void register(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, String password, String securityQuestion, String securityAnswer, String type) {
        PhoneNumber phoneNumber = new PhoneNumber(number, isWhatsapp);
        Address address = new Address(zipCode, street, neighborhood);
        People people = new People(name, CPF, birthDate, phoneNumber, address);
        User user = new User(CPF, password, securityQuestion, securityAnswer, people);
        people.setUser(user);
        System.out.println(user.getPeople().getName());

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(phoneNumber);
        this.entityManager.persist(address); 
        
        switch (type) {
            case "Doctor":
                Doctor doctor = new Doctor(people);
                this.entityManager.persist(doctor);
                people.setDoctor(doctor);
                break;
            case "Patient":
                Patient patient = new Patient(people);
                this.entityManager.persist(patient);
                people.setPatient(patient);
                break;
            case "Attendant":
                Attendant attendant = new Attendant(people);
                this.entityManager.persist(attendant);
                people.setAttendant(attendant);
                break;
            default:
                break;
        }
               
        this.entityManager.persist(people);
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }
    
    
    
    public boolean hasUserWithCpf (String login) {
        try {
            sql = " SELECT "
                    + " u.login "
                    + " FROM User u "
                    + " WHERE login LIKE :login ";

            qry = this.entityManager.createQuery(sql);
            qry.setParameter("login", login);

            List<String> lst = qry.getResultList();

            if (lst.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new UserException("Houve um erro inesperado! \n" + e.getMessage());
        }
    }
    

    public void createSeeds() {
        
    }
}
