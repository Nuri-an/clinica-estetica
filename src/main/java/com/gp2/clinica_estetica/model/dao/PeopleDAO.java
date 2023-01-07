/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Address;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.model.People;
import com.gp2.clinica_estetica.model.PhoneNumber;
import com.gp2.clinica_estetica.model.exceptions.UserException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author nuria
 */
public class PeopleDAO {

    EntityManager entityManager;

    Query qry;
    String sql;

    public PeopleDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public People fetchPeople(String CPF) {
        try {
            sql = " SELECT "
                    + " p "
                    + " FROM People p "
                    + " WHERE CPF LIKE :login ";

            qry = this.entityManager.createQuery(sql, People.class);
            qry.setParameter("login", CPF);

            List<People> lst = qry.getResultList();

            if (!lst.isEmpty()) {
                People people = lst.get(0);
                return people;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UserException("Ocorreu um erro, tente novamente.");
        }
    }

    public void basicRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, Integer houseNumber) {
        PhoneNumber phoneNumber = new PhoneNumber(number, isWhatsapp);
        Address address = new Address(zipCode, street, neighborhood, houseNumber);
        People people = new People(name, CPF, birthDate, phoneNumber, address);
        Patient patient = new Patient(people);
        people.setPatient(patient);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(phoneNumber);
        this.entityManager.persist(address);
        this.entityManager.persist(people);
        this.entityManager.persist(patient);
        this.entityManager.getTransaction().commit();
    }

    public void basicEdit(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, Integer houseNumber) {
        People currentPeople = this.fetchPeople(CPF);
        currentPeople.setName(name);
        currentPeople.setBirthDate(birthDate);
        
        PhoneNumber phoneNumber = currentPeople.getPhoneNumber();
        phoneNumber.setNumber(number);
        phoneNumber.setIsWhatsapp(isWhatsapp);
        
        Address address = currentPeople.getAddress();
        address.setZipCode(zipCode);
        address.setStreet(street);
        address.setNeighborhood(neighborhood);
        address.setHouseNumber(houseNumber);

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(phoneNumber);
        this.entityManager.merge(address);
        this.entityManager.persist(currentPeople);
        this.entityManager.getTransaction().commit();
    }
    
    public boolean hasPeopleWithCpf (String CPF) {
        try {
            People people = this.fetchPeople(CPF);

            if (people == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new UserException("Houve um erro inesperado! \n" + e.getMessage());
        }
    }
    
}
