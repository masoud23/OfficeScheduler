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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Masoud
 */
@Controller
@RequestMapping(value="/adminDashboard.html")
public class AdminDashboardController{
    private PatientManager patientMan;
    private AdminManager adminMan;
    
    public void setAdminMan(AdminManager adminMan) {
        this.adminMan = adminMan;
    }

    public void setPatientMan(PatientManager patientMan) {
        this.patientMan = patientMan;
    }
    
    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String mess (Model model){
        String message = "Masoudddddd";
        model.addAttribute("msg", message);
        return "adminDashboard";
        
    }
}
