/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.dao;

import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Contract;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author nuria
 */
public class ContractDAO implements IDao {

    EntityManager entityManager;
    Date currentDate;

    Query qry;
    String sql;

    public ContractDAO() {
        entityManager = Database.getInstance().getEntityManager();

        
        LocalDate date = LocalDate.now().minusDays(7);
        this.currentDate = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
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
    public Contract find(int id) {
        Contract contract = this.entityManager.find(Contract.class, id);

        return contract;
    }

    public List<Contract> findAll(String procedureName) {
        sql = " SELECT c "
                + " FROM Contract c "
                + " INNER JOIN c.procedure mp "
                + " WHERE c.date>=:currentDate  "
                + " AND mp.name LIKE CONCAT('%',CONCAT(:name, '%')) ";

        qry = this.entityManager.createQuery(sql, Contract.class);
        qry.setParameter("currentDate", this.currentDate);
        qry.setParameter("name", procedureName);

        List<Contract> lst = qry.getResultList();
        return lst;
    }

    public List<Contract> findAllBySigned(String procedureName) {
        sql = " SELECT c "
                + " FROM Contract c "
                + " INNER JOIN c.procedure mp "
                + " WHERE c.date>=:currentDate  "
                + " AND c.signed IS TRUE "
                + " AND mp.name LIKE CONCAT('%',CONCAT(:name, '%')) ";

        qry = this.entityManager.createQuery(sql, Contract.class);
        qry.setParameter("currentDate", currentDate);
        qry.setParameter("name", procedureName);

        List<Contract> lst = qry.getResultList();
        return lst;
    }

    public List<Contract> findAllByUnsigned(String procedureName) {
        sql = " SELECT c "
                + " FROM Contract c "
                + " INNER JOIN c.procedure mp "
                + " WHERE c.date>=:currentDate  "
                + " AND c.signed IS FALSE"
                + " AND mp.name LIKE CONCAT('%',CONCAT(:name, '%')) ";

        qry = this.entityManager.createQuery(sql, Contract.class);
        qry.setParameter("currentDate", currentDate);
        qry.setParameter("name", procedureName);

        List<Contract> lst = qry.getResultList();
        return lst;
    }
    
    public void setAsSigned(int id) {
        Contract contract = this.find(id);
        
        contract.setSigned(true);        
        
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(contract);
        this.entityManager.getTransaction().commit();
    }
}
