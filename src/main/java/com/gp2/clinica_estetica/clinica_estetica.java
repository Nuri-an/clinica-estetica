/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp2.clinica_estetica;

import com.gp2.clinica_estetica.controller.AppointmentController;
import com.gp2.clinica_estetica.controller.PeopleController;
import com.gp2.clinica_estetica.controller.ProcedureController;
import com.gp2.clinica_estetica.controller.UserController;
import com.gp2.clinica_estetica.factory.Database;
import com.gp2.clinica_estetica.model.Appointment;
import com.gp2.clinica_estetica.model.Contract;
import com.gp2.clinica_estetica.model.Doctor;
import com.gp2.clinica_estetica.model.MedicalProcedure;
import com.gp2.clinica_estetica.model.Patient;
import com.gp2.clinica_estetica.view.FrLogin;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author nuria
 */
public class clinica_estetica {

    public static void main(String[] args) {
        UserController userCon = new UserController();
        userCon.onCreateSeeds();
        
        FrLogin frLogin = new FrLogin();
        frLogin.setVisible(true);
        
        /*PeopleController peopleCon = new PeopleController();
        Doctor doc = peopleCon.onFetchPeople("85543241615").getDoctor();
        Patient pat = peopleCon.onFetchPeople("15171295670").getPatient();
        
        ProcedureController procedureCon = new ProcedureController();
        MedicalProcedure proc = procedureCon.onFetchProcedure(1);
        
        AppointmentController appointmentCon = new AppointmentController();
        Appointment app = appointmentCon.onFetchAppointment(3);
        
        Contract contr = new Contract(new Date(), false, "contracts/1.pdf");
        contr.setDoctor(doc);
        contr.setPatient(pat);
        contr.setProcedure(proc);
        contr.setAppointment(app);
        
        
        EntityManager entityManager = Database.getInstance().getEntityManager();
        
                entityManager.getTransaction().begin();
                entityManager.persist(contr);
                entityManager.getTransaction().commit(); */
    }
}
