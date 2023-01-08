/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Contract;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author darloonlino
 */
public class TMListContracts extends AbstractTableModel {

    private List<Contract> lstContracts;

    private int COL_DATE = 0;
    private int COL_PROCEDURE = 1;
    private int COL_PATIENT = 2;
    private int COL_PRICE = 3;

    public TMListContracts(List<Contract> lstContracts) {
        this.lstContracts = lstContracts;
    }

    public void putColumns(int col_data, int col_procedure, int col_patient, int col_price) {
        this.COL_DATE = col_data;
        this.COL_PROCEDURE = col_procedure;
        this.COL_PATIENT = col_patient;
        this.COL_PRICE = col_price;
    }

    @Override
    public int getRowCount() {
        return lstContracts.size();
    }

    @Override
    public int getColumnCount() {
        int qtn = 0;
        if (this.COL_DATE >= 0) {
            qtn++;
        }
        if (this.COL_PROCEDURE >= 0) {
            qtn++;
        }
        if (this.COL_PATIENT >= 0) {
            qtn++;
        }
        if (this.COL_PRICE >= 0) {
            qtn++;
        }

        return qtn;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contract aux = new Contract();
        if (lstContracts.isEmpty()) {
            return aux;
        } else {
            aux = lstContracts.get(rowIndex);

            if (columnIndex == -1) {
                return aux;
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                if (columnIndex == -1) {
                    return aux;
                }
                if (columnIndex == COL_DATE) {
                    return simpleDateFormat.format(aux.getDate());
                }
                if (columnIndex == COL_PROCEDURE) {
                    return aux.getProcedure().getName();
                }
                if (columnIndex == COL_PATIENT) {
                    return aux.getPatient().getPeople().getName();
                }
                if (columnIndex == COL_PRICE) {
                    return "R$ " + ((Double) (Math.round(aux.getAppointment().getBudget() * 100.0) / 100.0)).toString().replaceAll("\\.", ",");
                }
            }
            return aux;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex
    ) {
        return false;
    }

    @Override
    public String getColumnName(int column
    ) {

        if (column == COL_DATE) {
            return "Data";
        }
        if (column == COL_PROCEDURE) {
            return "Procedimento";
        }
        if (column == COL_PATIENT) {
            return "Paciente";
        }
        if (column == COL_PRICE) {
            return "Valor";
        }
        
        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex
    ) {

        return String.class;
    }
}
