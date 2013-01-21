/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.AdminManager;
import edu.weber.finalproject.schedmanager.PatientManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Masoud
 */
public class DashboardController extends SimpleFormController{
    private PatientManager patientMan;
    private AdminManager adminMan;
    private String viewName;
    
    public void setAdminMan(AdminManager adminMan) {
        this.adminMan = adminMan;
    }

    public void setPatientMan(PatientManager patientMan) {
        this.patientMan = patientMan;
    }
        public void setViewName(String viewName){
        this.viewName = viewName;
    }
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
        ModelAndView mav = new ModelAndView(viewName);
        if (errors.hasErrors()){
            showForm(request, errors, getFormView());
        }
         
        //mav.addObject(cal);
        return mav;
    }
}
