/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Calendar;
import edu.weber.finalproject.schedschema.TimeSlot;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface CalendarDAO {
    
    Calendar getCalendarById(int id);
    
    List<Calendar> getCalendarsByAvailability(int available);
    
    List<Calendar> getAllCalendars();
    
    int addCalendar (Calendar cal);
    
    int updateCalendar(Calendar cal);
    
    int deleteCalendar(int calendarId);
    
}
