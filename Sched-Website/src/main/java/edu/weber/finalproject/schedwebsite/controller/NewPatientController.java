/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.EmailManager;
import edu.weber.finalproject.schedmanager.PatientManager;
import edu.weber.finalproject.schedschema.Patient;
import edu.weber.finalproject.schedwebsite.validator.PatientValidator;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

/**
 * Displays the patient data form
 * @author Mike
 */
public class NewPatientController extends CancellableFormController {

    private PatientManager manager;
    private EmailManager eman;
    
    public void seteManager(EmailManager eManager) {
        this.eman = eManager;
    }

    public void setManager(PatientManager manager) {
        this.manager = manager;
    }

    public PatientManager getManager() {
        return manager;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        ModelAndView mav = new ModelAndView(getSuccessView(), errors.getModel());
        Patient patient = (Patient) command;
        boolean isDuplicate = manager.checkExistingUserNames(patient.getUsername());
        if(isDuplicate) {
                errors.rejectValue("username", "already in use", "User name is already in use!");
                showForm(request, errors, getFormView());
        }        
        Validator patientValidator = new PatientValidator();
        patientValidator.validate(patient, errors.getBindingResult());
        
            
        if(errors.getBindingResult().hasErrors()){
            showForm(request, errors, getFormView());
        }
        
        if(!isDuplicate) {
            int success = manager.save(patient);
            if(success > 0) {
                mav.addObject("message", "Your profile was successfully saved");
                Map emailModel = new HashMap();
                emailModel.put("account", (Patient) command);
                eman.sendEmail(emailModel, "properties/newAccountEmail.vm", patient.getEmail(), "team@sched.com", "Welcome to our app.", true);
            }
            else {
                mav.addObject("message", "There was a problem saving your profile.  Please try again.");
            }
        }
        return mav;
        
       
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, "birthDate", new PropertyEditorSupport() {

            @Override
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("dd/MM/yyyy").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                if(null == getValue()){
                    return "dd/mm/yyyy";
                }else{
                    return new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
                }
            }
        });

    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            return manager.getPatient(id);
        } else {
            Patient returnMe = new Patient();
            returnMe.setId(0);
            return returnMe;
        }
    }
}
