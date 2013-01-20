/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.CalendarManager;
import edu.weber.finalproject.schedschema.Calendar;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

/**
 *
 * @author Masoud
 */
public class AdminController extends CancellableFormController{
   
    private CalendarManager calMan;
    private String viewName;
   
    public void setCalendarManager(CalendarManager calMan){
        this.calMan = calMan;
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
         
        //Need a select list with the calndar dates so the id is selected for the next method 
        //You have to call calMan.createCalendars(int numDays) first.
       
        Calendar cal = (Calendar) command;
   //     calMan.createCalendars(30, cal.getStartHour(), cal.getStratMin(), cal.getEndHour(), cal.getEndMin(), cal.getAppLength());

        mav.addObject(cal);
        return mav;
    }
        @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

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
}
