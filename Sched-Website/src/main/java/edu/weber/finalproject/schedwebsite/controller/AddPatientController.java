/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.PatientManager;
import edu.weber.finalproject.schedschema.Patient;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Masoud
 */
public class AddPatientController extends SimpleFormController{
    private PatientManager patMan;
    
    public void setManager(PatientManager patMan) {
        this.patMan = patMan;
    }
    
    public AddPatientController(){
        setCommandClass(Patient.class);
        setCommandName("patient");
    }
    
    
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        Patient patient = (Patient) command;
        patMan.save(patient);
        return new ModelAndView("patientSuccess","patient",patient);
    }
    
}
