/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Address;
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

    public void basicRegister(String name, String CPF, String birthDate, String number, boolean isWhatsapp, String zipCode, String street, String neighborhood, Integer houseNumber) {
        PhoneNumber phoneNumber = new PhoneNumber(number, isWhatsapp);
        Address address = new Address(zipCode, street, neighborhood, houseNumber);
        People people = new People(name, CPF, birthDate, phoneNumber, address);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(phoneNumber);
        this.entityManager.persist(address);
        this.entityManager.persist(people);
        this.entityManager.getTransaction().commit();
    }
    
    public boolean hasPeopleWithCpf (String CPF) {
        try {
            sql = " SELECT "
                    + " p.CPF "
                    + " FROM People p "
                    + " WHERE CPF LIKE :cpf ";

            qry = this.entityManager.createQuery(sql);
            qry.setParameter("cpf", CPF);

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
    
}
