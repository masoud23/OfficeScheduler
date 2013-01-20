/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.schedwebservice.Controller;

import edu.weber.finalproject.schedmanager.AppointmentManager;
import edu.weber.finalproject.schedmanager.CalendarManager;
import edu.weber.finalproject.schedmanager.PatientManager;
import edu.weber.finalproject.schedschema.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Mike
 */
@Controller
@RequestMapping(value = "/cal")
public class JsonFeedController {


    
    @Autowired
    private CalendarManager calMan;
    @Autowired 
    PatientManager patientManager;
    @Autowired
    private AppointmentManager appointmentManager;
    
//    public void setCalMan(CalendarManager calMan) {
//        this.calMan = calMan;
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Calendar getCalendar(@PathVariable("id")int id) {
        return calMan.getCalendar(id);
    }
    
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getPatientEvents() {
        List<Event> events = new ArrayList<Event>();
        for(TimeSlot slot : calMan.getPatientCalendarSlots()) {
            Event event = new Event();
            event.setId(slot.getId());
            event.setTitle("Open Appointment");
            event.setStartDate(slot.getDateTime());
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTimeInMillis(slot.getDateTime().getTime());
            cal.add(java.util.Calendar.MINUTE, 30);
            event.setEndDate(new Date(cal.getTimeInMillis()));   //need a way to not hard-code the minutes
            event.setUrl("confirmAppointment.html?id=" + slot.getId());
            events.add(event); 
        }
        EventList ev = new EventList();
        ev.setEvents(events);
        return events;
    }
    
    @RequestMapping(value ="/patients/${id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getPatientAppointments(@PathVariable("id")int id) {
        List<Event> events = new ArrayList<Event>();
        for(Appointment app : appointmentManager.getPatientAppointments(id)) {
            Event event = new Event();
            event.setId(app.getId());
  //          Patient pat = patientManager.getPatient(app.getPatientId());
  //          event.setTitle(pat.getLast() + ", " + pat.getFirst() + " " + pat.getMiddle());
            TimeSlot slot = calMan.getSlot(id);                 //slotId = appId
            event.setStartDate(slot.getDateTime());
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTimeInMillis(slot.getDateTime().getTime());
            cal.add(java.util.Calendar.MINUTE, 30);
            event.setEndDate(new Date(cal.getTimeInMillis()));   //need a way to not hard-code the minutes
            event.setUrl("index.html");
            events.add(event);
        }
        return events;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getAdminEvents() {
        List<Event> events = new ArrayList<Event>();
        for(TimeSlot slot : calMan.getAdminCalendarSlots()) {
            Event event = new Event();
            event.setId(slot.getId());
            if(!slot.isAvailable()) {
  //              Appointment app = calMan.getAppointment(slot.getAppointmentId());
  //              Patient pat = patientManager.getPatient(app.getPatientId());
  //              event.setTitle(pat.getLast() + ", " + pat.getFirst() + " " + pat.getMiddle());
            }
            else {
                event.setTitle("Open Appointment");
            }
            event.setStartDate(slot.getDateTime());
            event.setEndDate(new Date(slot.getDateTime().getTime()));   //need a way to not hard-code the minutes
            event.setUrl("confirmApp.html?id=" + slot.getId());
            event.setAllDay(false);
            events.add(event);
            
        }
        return events;
    }
}
