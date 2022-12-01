/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nuria
 */
public class Database {
    
    private EntityManager entityManager;

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    /**
     * Construtor da classe. 
     * - No padrão SINGLETON ele deve ser PRIVADO
     * - Só pode ser chamado pelo método getInstance()
     * - O método getInstance() gerencia a regra SINGLETON, que permite apenas 
     * uma instancia do objeto Database
     */
    private Database() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica-estetica-jpa");
        this.entityManager = factory.createEntityManager();
    }
    
    public EntityManager getEntityManager() {        
        return entityManager;
    }
}
