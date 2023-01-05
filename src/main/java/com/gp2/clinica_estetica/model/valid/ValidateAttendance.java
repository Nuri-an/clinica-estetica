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

    public void scheduleValidate(Calendar startSection, Calendar endSection, Integer id) {
        AttendanceDAO attendanceDao = new AttendanceDAO();
        List<Attendance> attendances = attendanceDao.findAll();

        for (int i = 0; i < attendances.size(); i++) {
            if (attendances.get(i).getStartDateTime().get(Calendar.DAY_OF_MONTH) == startSection.get(Calendar.DAY_OF_MONTH)) {
                System.out.println(startSection.get(Calendar.DAY_OF_MONTH));
                DateTime dt_start = new DateTime(
                        attendances.get(i).getStartDateTime().get(Calendar.YEAR),
                        attendances.get(i).getStartDateTime().get(Calendar.MONTH) + 1,
                        attendances.get(i).getStartDateTime().get(Calendar.DAY_OF_MONTH),
                        attendances.get(i).getStartDateTime().get(Calendar.HOUR_OF_DAY),
                        attendances.get(i).getStartDateTime().get(Calendar.MINUTE),
                        00);
                DateTime dt2_start = new DateTime(startSection.getTime());

                System.out.println("dt_start: " + dt_start);
                System.out.println("dt2_start: " + dt2_start);

                DateTime dt_end = new DateTime(attendances.get(i).getEndDateTime().getTime());
                DateTime dt2_end = new DateTime(endSection.getTime());

                System.out.println("dt_end: " + dt_end);
                System.out.println("dt2_end: " + dt2_end);

                boolean sameEvent = DateTime.op_Equality(dt_start, dt2_start)
                        && DateTime.op_Equality(dt_end, dt2_end);

                boolean starOfEvent = DateTime.op_GreaterThan(dt_end, dt2_start)
                        && DateTime.op_LessThan(dt_end, dt2_end);

                boolean endOfEvent = DateTime.op_GreaterThan(dt_start, dt2_start)
                        && DateTime.op_LessThan(dt_start, dt2_end);

                boolean middleOfEvent = DateTime.op_GreaterThan(dt_start, dt2_start)
                        && DateTime.op_LessThan(dt_start, dt2_end)
                        && DateTime.op_GreaterThan(dt_end, dt2_start)
                        && DateTime.op_LessThan(dt_end, dt2_end);

                boolean outOfEvent = DateTime.op_LessThan(dt_start, dt2_start)
                        && DateTime.op_GreaterThan(dt_end, dt2_end);

                System.out.println(sameEvent + ", " + starOfEvent + ", " + endOfEvent + ", " + middleOfEvent + ", " + outOfEvent);
                if (sameEvent || starOfEvent || endOfEvent || middleOfEvent || outOfEvent) {                    
                    if (attendances.get(i).getId() == id) {
                        return;
                    }
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
    

    public void findTypeValidate(String type) {
        if (type.isEmpty()) {
            throw new AttendanceException("Error - Informe o tipo do agendamento.");
        }
        
    }
}
