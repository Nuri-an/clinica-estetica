/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.MedicalProcedure;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author darloonlino
 */
public class ProcedureDAO implements IDao {    

    EntityManager entityManager;

    Query qry;
    String sql;

    public ProcedureDAO() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public MedicalProcedure save(String name, Double price) {
        MedicalProcedure procedure = new MedicalProcedure(name, price);
        
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(procedure);
        this.entityManager.getTransaction().commit();
        
        return procedure;
    }
    
    public Object findByName(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MedicalProcedure find(int id) {
        MedicalProcedure procedure = this.entityManager.find(MedicalProcedure.class, id);
        
        return procedure;
    }

    public List<MedicalProcedure> findAllProcedurres() {
        sql = " SELECT p "
                + " FROM MedicalProcedure p ";

        qry = this.entityManager.createQuery(sql);

        List lst = qry.getResultList();
        return lst;
    }
    
}
