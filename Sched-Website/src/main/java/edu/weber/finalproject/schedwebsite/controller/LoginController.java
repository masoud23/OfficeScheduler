/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.AdminManager;
import edu.weber.finalproject.schedmanager.PatientManager;
import edu.weber.finalproject.schedschema.Admin;
import edu.weber.finalproject.schedschema.Patient;
import edu.weber.finalproject.schedschema.User;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import edu.weber.finalproject.schedmanager.SessionManager;
import edu.weber.finalproject.schedwebsite.validator.LoginValidator;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;

/**
 *
 * @author Mike
 */
public class LoginController extends SimpleFormController{
    
    private PatientManager patientMan;
    private AdminManager adminMan;
   // private HttpSession session;

    public void setAdminMan(AdminManager adminMan) {
        this.adminMan = adminMan;
    }

    public void setPatientMan(PatientManager patientMan) {
        this.patientMan = patientMan;
    }
    
    public LoginController(){
        setCommandClass(User.class);
        setCommandName("user");
    }
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        ModelMap model = new ModelMap();
        User user = (User)command;
        HttpSession session = request.getSession(true);
        Validator userValidator = new LoginValidator();
        userValidator.validate(user, errors.getBindingResult());
        if(errors.getBindingResult().hasErrors())
        {
            return new ModelAndView("index", "command", user);
        }
        int userId = patientMan.authenticate(user.getUsername(), user.getPassword());
        if(userId == 0){
        userId = adminMan.authenticate(user.getUsername(), user.getPassword());
            if(userId > 0)
            {
                user = adminMan.getAdmin(userId);
            }
            else{
                ModelAndView mav = new ModelAndView("index");
                mav.addObject("message", "Username and Password not found!  Try again.");
                return mav;
            }
        }
        else
        {
            user = patientMan.getPatient(userId);
        }

        session.setAttribute(SessionManager.SessionKeys.currentUser, user);
        if(user.getClass().getName().equalsIgnoreCase("Patient"))
        {
            model.addAttribute("user", user);
            return new ModelAndView("patientDashboard");
        }
        model.addAttribute("user", user);
        return new ModelAndView("redirect:/adminDashboard.html");
    }
    
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.setRequiredFields("username", "password");
        super.initBinder(request, binder);
        
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        User returnMe = new User();
        returnMe.setId(0);
        return returnMe;
    }
}
