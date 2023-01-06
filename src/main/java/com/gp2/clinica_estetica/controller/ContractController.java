/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Contract;
import com.gp2.clinica_estetica.model.dao.ContractDAO;
import com.gp2.clinica_estetica.model.exceptions.ContractException;
import com.gp2.clinica_estetica.model.valid.ValidateContract;
import com.gp2.clinica_estetica.model.valid.ValidateIDao;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author nuria
 */
public class ContractController {

    private ContractDAO repositorio;

    public ContractController() {
        repositorio = new ContractDAO();
    }

    public void updateTable(JTable grd, String procedureName) {
        ValidateContract validContract = new ValidateContract();
        validContract.searchValidate(procedureName);

        try {
            List<Contract> listAll = repositorio.findAll(procedureName);
            Util.jTableShow(grd, new TMListContracts(listAll), null);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }

    public void updateTableWithSigned(JTable grd, String procedureName) {
        ValidateContract validContract = new ValidateContract();
        validContract.searchValidate(procedureName);

        try {
            List<Contract> listAll = repositorio.findAllBySigned(procedureName);
            Util.jTableShow(grd, new TMListContracts(listAll), null);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }

    public void updateTableWithSigned(JTable grd, String cpf, String procedureName) {
        ValidateContract validContract = new ValidateContract();
        validContract.searchPerPetientValidate(cpf, procedureName);

        try {
            List<Contract> listAll = repositorio.findAllBySigned(cpf, procedureName);
            Util.jTableShow(grd, new TMListContracts(listAll), null);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }

    public void updateTableWithUnsigned(JTable grd, String procedureName) {
        ValidateContract validContract = new ValidateContract();
        validContract.searchValidate(procedureName);

        try {
            List<Contract> listAll = repositorio.findAllByUnsigned(procedureName);
            Util.jTableShow(grd, new TMListContracts(listAll), null);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }

    public void updateTableWithUnsigned(JTable grd, String cpf, String procedureName) {
        ValidateContract validContract = new ValidateContract();
        validContract.searchPerPetientValidate(cpf, procedureName);

        try {
            List<Contract> listAll = repositorio.findAllByUnsigned(cpf, procedureName);
            Util.jTableShow(grd, new TMListContracts(listAll), null);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }

    public void updateTableWithExpired(JTable grd, String cpf, String procedureName) {
        ValidateContract validContract = new ValidateContract();
        validContract.searchPerPetientValidate(cpf, procedureName);

        try {
            List<Contract> listAll = repositorio.findAllByExpired(cpf, procedureName);
            Util.jTableShow(grd, new TMListContracts(listAll), null);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }

    public void onSetAsSigned(int id) {
        ValidateIDao validDao = new ValidateIDao();
        validDao.find(id);

        try {
            repositorio.setAsSigned(id);
        } catch (ContractException e) {
            throw new ContractException(e.getMessage());
        }
    }
}
