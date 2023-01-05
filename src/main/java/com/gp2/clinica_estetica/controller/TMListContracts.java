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

    private final int COL_DATE = 0;
    private final int COL_PROCEDURE = 1;
    private final int COL_PATIENT = 2;
    private final int COL_PRICE = 3;

    public TMListContracts(List<Contract> lstContracts) {
        this.lstContracts = lstContracts;
    }

    @Override
    public int getRowCount() {
        return lstContracts.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
                switch (columnIndex) {
                    case -1:
                        return aux;
                    case COL_DATE:
                        return simpleDateFormat.format(aux.getDate());
                    case COL_PROCEDURE:
                        return aux.getProcedure().getName();
                    case COL_PATIENT:
                        return aux.getPatient().getPeople().getName();
                    case COL_PRICE:
                        return "R$ " + ((Double) (Math.round(aux.getAppointment().getBudget() * 100.0) / 100.0)).toString().replaceAll("\\.", ",");

                    default:
                        break;
                }
            }
        }
        return aux;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case COL_DATE:
                return "Data";
            case COL_PROCEDURE:
                return "Procedimento";
            case COL_PATIENT:
                return "Paciente";
            case COL_PRICE:
                return "Valor";

            default:
                break;
        }

        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {

        return String.class;
    }
}
