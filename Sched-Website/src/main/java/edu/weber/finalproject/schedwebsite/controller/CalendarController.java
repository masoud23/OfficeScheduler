/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.CalendarManager;
import edu.weber.finalproject.schedschema.Calendar;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Mike
 */
public class CalendarController extends AbstractController{
    private String viewName;
    private CalendarManager calendarMan;
    
    public CalendarController() {
        
    }

    public CalendarController(String view, CalendarManager calMan) {
        this.viewName = view;
        this.calendarMan = calMan;
    }
    
    public void setViewName(String view) {
        this.viewName = view;
    }
    
    public void setCalendarMan(CalendarManager calMan) {
        this.calendarMan = calMan;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session = hsr.getSession(true);
//        calendarMan.createCalendars(30, 8, 0, 17, 0, 30);       //testing
//        session.setAttribute("url", "/Sched-WebService/cal/patients");
//        if(session.getAttribute("patientId") == null) {
//            session.setAttribute("patientId", 1);       //temporary for testing
//        }
        return mav;
    }
}
