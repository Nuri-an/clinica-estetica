/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
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

}
