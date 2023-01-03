/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica.model.valid;

import com.gp2.clinica_estetica.model.Attendance;
import com.gp2.clinica_estetica.model.dao.AttendanceDAO;
import com.gp2.clinica_estetica.model.exceptions.AttendanceException;
import com.mindfusion.common.DateTime;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author darloonlino
 */
public class ValidateAttendance {

    public void scheduleValidate(Calendar startSection, Calendar endSection) {
        AttendanceDAO attendanceDao = new AttendanceDAO();

        List<Attendance> attendances = attendanceDao.findAllAttendances();

        for (int i = 0; i < attendances.size(); i++) {
            if (attendances.get(i).getStartDateTime().get(Calendar.DAY_OF_MONTH) == startSection.get(Calendar.DAY_OF_MONTH)) {
                DateTime dt_start = new DateTime(attendances.get(i).getStartDateTime().getTime());
                DateTime dt2_start = new DateTime(startSection.getTime());

                DateTime dt_end = new DateTime(attendances.get(i).getEndDateTime().getTime());
                DateTime dt2_end = new DateTime(endSection.getTime());

                boolean starOfEvent = DateTime.op_LessThanOrEqual(dt_start, dt2_start)
                        && DateTime.op_GreaterThanOrEqual(dt_start, dt2_start);

                boolean endOfEvent = DateTime.op_GreaterThanOrEqual(dt_end, dt2_start)
                        && DateTime.op_LessThanOrEqual(dt_end, dt2_end);

                boolean middleOfEvent = DateTime.op_GreaterThanOrEqual(dt_start, dt2_start)
                        && DateTime.op_GreaterThanOrEqual(dt_start, dt2_end);

                boolean outOfEvent = DateTime.op_LessThanOrEqual(dt_start, dt2_start)
                        && DateTime.op_GreaterThanOrEqual(dt_end, dt2_end);

                System.out.println(starOfEvent + ", " + endOfEvent + ", " + middleOfEvent);
                if (starOfEvent || endOfEvent || middleOfEvent || outOfEvent) {
                    throw new AttendanceException("Erro - Já existe um agendamento neste horario");
                }
            }
        }
    }
    

    public void saveValidate(Calendar startSection, Calendar endSection, String finality) {
        if (startSection.getCalendarType() == null) {
            throw new AttendanceException("Error - Forneça o inicio da sessão.");
        }
        if (endSection.getCalendarType() == null) {
            throw new AttendanceException("Error - Forneça o fim da sessão.");
        }
        if (finality.equals("")) {
            throw new AttendanceException("Error - Informe a finalidade do procedimento.");
        }
    }
}
