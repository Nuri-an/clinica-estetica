/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.controller;

import com.gp2.clinica_estetica.model.Attendance;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author darloonlino
 */
public class TMListAttendance extends AbstractTableModel {

    private List<Attendance> lstAttendance;

    private final int COL_DATA = 0;
    private final int COL_PATIENT = 1;
    private final int COL_PROCEDURE = 2;

    public TMListAttendance(List<Attendance> lstPatient) {
        this.lstAttendance = lstPatient;
    }

    @Override
    public int getRowCount() {
        return lstAttendance.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Attendance aux = new Attendance();
        if (lstAttendance.isEmpty()) {
            return aux;
        } else {
            aux = lstAttendance.get(rowIndex);
            String date = aux.getStartDateTime().get(Calendar.DAY_OF_MONTH)
                    + "/"
                    + (aux.getStartDateTime().get(Calendar.MONTH) + 1)
                    + "/"
                    + aux.getStartDateTime().get(Calendar.YEAR)
                    + " - "
                    + aux.getStartDateTime().get(Calendar.HOUR_OF_DAY)
                    + ":"
                    + aux.getStartDateTime().get(Calendar.MINUTE);

            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_DATA:
                    return date;
                case COL_PATIENT:
                    return aux.getPatient().getPeople().getName();
                case COL_PROCEDURE:
                    return aux.getProcedure().getName();

                default:
                    break;
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
            case COL_DATA:
                return "Data";
            case COL_PATIENT:
                return "Paciente";
            case COL_PROCEDURE:
                return "Procedimento";

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
