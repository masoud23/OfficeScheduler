/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.AdminManager;
import edu.weber.finalproject.schedmanager.PatientManager;
import edu.weber.finalproject.schedschema.Calendar;
import edu.weber.finalproject.schedschema.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Masoud
 */
@Controller
@RequestMapping(value = "/patientDashboard")
public class PatientDashboardController{
    private PatientManager patientMan;
    private String viewName;
    private String patient;

    public void setPatientMan(PatientManager patientMan) {
        this.patientMan = patientMan;
    }
        public void setViewName(String viewName){
        this.viewName = viewName;
    }
        
    @RequestMapping(method = RequestMethod.GET)    
    public String patientController(@ModelAttribute("user") User user){
        String firstName = user.getFirst();
        
        return firstName;
    }
}
