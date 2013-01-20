/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.controller;

import edu.weber.finalproject.schedmanager.AppointmentManager;
import edu.weber.finalproject.schedmanager.CalendarManager;
import edu.weber.finalproject.schedschema.Appointment;
import edu.weber.finalproject.schedschema.TimeSlot;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;



/**
 *
 * @author Mike
 */
public class ConfirmAppointmentController extends AbstractController{
    
    private AppointmentManager appointmentMan;
    private CalendarManager calendarMan;
    
    
//    public ConfirmAppointmentController(AppointmentManager appMan, CalendarManager calMan) { 
//        this.appointmentMan = appMan;
//        this.calendarMan = calMan;
//    }

    public void setAppointmentMan(AppointmentManager appMan) {
        this.appointmentMan = appMan;
    }

    public void setCalendarMan(CalendarManager calMan) {
        this.calendarMan = calMan;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        int id = Integer.parseInt(hsr.getParameter("id"));
        ModelAndView mav = null;
        //display confirmAppointment page        
        if(hsr.getMethod().equalsIgnoreCase("GET")) {
            TimeSlot slot = calendarMan.getSlot(id);          
            mav = new ModelAndView("confirmAppointment");
            mav.addObject("slot", slot);
        }
        else {
            //save appointment
            if(hsr.getParameter("confirm").equalsIgnoreCase("yes")) {
                HttpSession session = hsr.getSession(true);
                Appointment toSave = new Appointment();
                toSave.setId(id);
                int patientId = (Integer) session.getAttribute("patientId");
          //      toSave.setPatientId(patientId);
                session.setAttribute("url", "/Sched-WebService/cal/patients/" + patientId);
 //               toSave.setExamId((Integer) session.getAttribute("examId"));
                appointmentMan.saveAppointment(toSave);
                mav = new ModelAndView("calendar.html");    //don't know name yet or a "show appointments" page
            } 
            // no confirm? -- send back to calendar
            else {
                mav = new ModelAndView(new RedirectView("calendar.html"), null);
            }
        }
        return mav;
    }
}
