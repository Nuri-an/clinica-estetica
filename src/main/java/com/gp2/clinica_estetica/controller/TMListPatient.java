/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.People;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author darloonlino
 */
public class TMListPatient extends AbstractTableModel {

    private List<People> lstPatient;

    private final int COL_CPF = 0;
    private final int COL_NAME = 1;

    public TMListPatient(List<People> lstPatient) {
        this.lstPatient = lstPatient;
    }

    @Override
    public int getRowCount() {
        return lstPatient.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        People aux = new People();
        if (lstPatient.isEmpty()) {
            return aux;
        } else {
            aux = lstPatient.get(rowIndex);

            if (columnIndex == -1) {
                return aux;
            } else {

                switch (columnIndex) {
                    case -1:
                        return aux;
                    case COL_CPF:
                        return aux.getCPF();
                    case COL_NAME:
                        return aux.getName();

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
            case COL_CPF:
                return "CPF";
            case COL_NAME:
                return "Nome";

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
