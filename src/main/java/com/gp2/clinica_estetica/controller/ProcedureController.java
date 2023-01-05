/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.MedicalProcedure;
import com.gp2.clinica_estetica.model.dao.ProcedureDAO;
import com.gp2.clinica_estetica.model.exceptions.ProcedureException;
import com.gp2.clinica_estetica.model.valid.ValidateIDao;
import com.gp2.clinica_estetica.model.valid.ValidateProcedure;
import java.util.List;

/**
 *
 * @author nuria
 */
public class ProcedureController {

    private ProcedureDAO repositorio;

    public ProcedureController() {
        repositorio = new ProcedureDAO();
    }

    public MedicalProcedure onSave(Object name, String price) {
        ValidateProcedure validProcedure = new ValidateProcedure();
        validProcedure.saveValidate(name, price);
        
        try {
            return repositorio.save(name.toString(), Double.parseDouble(price));
        } catch (ProcedureException e) {
            throw new ProcedureException("Error - erro salvar procedimento.");
        }
    }

    public List<MedicalProcedure> onFindAll() {        
        try {
            return repositorio.findAllProcedurres();
        } catch (ProcedureException e) {
            throw new ProcedureException("Error - erro buscar procedimentos.");
        }
    }

    public MedicalProcedure onFetchProcedure(Integer id) {
            ValidateIDao validIDao = new ValidateIDao();
            validIDao.find(id);

        try {
            return repositorio.find(id);
        } catch (ProcedureException e) {
            throw new ProcedureException("Error - Falha ao buscar atendimento.");
        }

    }

}
