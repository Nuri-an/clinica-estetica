
package com.gp2.clinica_estetica.model.dao;

import java.util.List;

/**
 *
 * @author jose
 */
public interface IDao {
    
    public void save(Object obj);
    
    public boolean delete(int id);
            
    public Object find(int id);
        
    public List<Object> findAll();
}
