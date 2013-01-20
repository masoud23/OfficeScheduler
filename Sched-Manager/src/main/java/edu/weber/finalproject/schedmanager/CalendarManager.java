/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedmanager;

import edu.weber.finalproject.schedpersistance.AppointmentDAO;
import edu.weber.finalproject.schedpersistance.CalendarDAO;
import edu.weber.finalproject.schedpersistance.SlotDAO;
import edu.weber.finalproject.schedschema.Appointment;
import edu.weber.finalproject.schedschema.Calendar;
import edu.weber.finalproject.schedschema.TimeSlot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Mike
 */
//@Service
public class CalendarManager {
    
//    @Autowired
    private AppointmentDAO appDao;
//    @Autowired
    private SlotDAO slotDao;
//    @Autowired
    private CalendarDAO calDao;
    
    public CalendarManager() {}
    public CalendarManager(SlotDAO slotDao, CalendarDAO calDao, AppointmentDAO appDao) {
        
        this.appDao = appDao;
        this.slotDao = slotDao;
        this.calDao = calDao;
    }

//    public void setAppDao(AppointmentDAO appDao) {
//        this.appDao = appDao;
//    }
//
//    public void setCalDao(CalendarDAO calDao) {
//        this.calDao = calDao;
//    }
//
//    public void setSlotDao(SlotDAO slotDao) {
//        this.slotDao = slotDao;
//    }
    
    public List<TimeSlot> getSlotsForDay(int calendarId) {
        return slotDao.getSlotsByCalendarId(calendarId);
    }
    
    public TimeSlot getSlot(int id) {
        return slotDao.getSlotById(id);
    }
    
    public Calendar getCalendar(int id) {
        Calendar day = calDao.getCalendarById(id);
  //      day.setSlots(getSlotsForDay(id));
        return day;
    }
     
    public List<Calendar> getCalendars() {      //need method to retrieve calendars by week or month
        List<Calendar> days = calDao.getAllCalendars();
        for(Calendar day : days) {
 //           day.setSlots(getSlotsForDay(day.getId()));
        }
        return days;
    }
    
    public Appointment getAppointment(int appointmentId) {
        return appDao.getAppointmentById(appointmentId);
    }
    
    public List<Calendar> getAvailableCalendars(int available) {
       List<Calendar> days = calDao.getCalendarsByAvailability(available);
       List<Appointment> appointments = new ArrayList<Appointment>();
       for(Calendar day : days) {
    //       day.setSlots(getSlotsForDay(day.getId()));
//           for(TimeSlot slot : day.getSlots()) {
//               //appointments.add(appDao.getAppointmentById(slot.getAppointmentId()));
//           }
     //      day.setAppointments(appointments);
       }
       return days;
   }
   /**
    * Populates the patient calendar with only the available appointments
    * @return available Timeslots
    */
   public List<TimeSlot>getPatientCalendarSlots() {
       return slotDao.getAllAvailableSlots();
   }
   
   /**
    * Admin can see all of the slots so the calendar is filled with all of them.
    * @return all Timeslots
    */
   public List<TimeSlot>getAdminCalendarSlots() {
       return slotDao.getAllSlots();
   }
   
   public int saveCalendar(Calendar cal) {
       if(cal.getId() < 1) {
           return calDao.addCalendar(cal); 
       }
       else {
           return calDao.addCalendar(cal);
       }
   }
   /**
    * Adds any number of raw calendar days (skipping weekends) to the db for further processing.  Must limit number of
    * NOT NULL fields in db table to allow this and avoid a huge, unfriendly method
    * @param numDays 
    */
//   public void createCalendars(int numDays, int startHr, int startMin, int endHour, int endMin, int appLen) {
//       java.util.Calendar cal = java.util.Calendar.getInstance();
//       int calId;
//       for(int i = 0; i < numDays; i++){
//           Calendar day = new Calendar();
//           int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
//           if(dayOfWeek != java.util.Calendar.SATURDAY || dayOfWeek != java.util.Calendar.SUNDAY) {
//     //           day.setDate(cal.getTime());
//                calId = saveCalendar(day);  // must add raw calendar to get an Id to pass to other methods
//    //            setAvailableSlotsForDay(calId, startHr, startMin, endHour, endMin, appLen);
//    //       } 
//           cal.add(java.util.Calendar.DATE, 1);
// //      }
////   }
   /**
    * For an admin calendar management form.  Use only after a raw calendar is in the db.  Admin chooses calendar by date
    * from dropdown list.  The id is then used in this method. To add slots
    * @
    * @param calendarId
    * @param startHour
    * @param startMin
    * @param endHour
    * @param endMin
    * @param appointLength 
    * @return number of slots added
    */
//   public int setAvailableSlotsForDay(int calendarId, int startHour, int startMin, int endHour, int endMin, int appointLength) {
//       
//        Calendar day = calDao.getCalendarById(calendarId);
//  //      if(day.getSlotId() >= 0) {
//            return 0;
//        }
////        else {
////            Date workDay = null;
////            Date endDay = null;
////            if(day.getDate() != null) {
////                    workDay = day.getDate();
////                    endDay = day.getDate();
////            }
////            else {
////                workDay = new Date();
////                endDay = new Date();
////            }
////            java.util.Calendar calendar = java.util.Calendar.getInstance();
////            day.setDate(workDay);
////            //set the times
////            calendar.setTime(workDay);
////            calendar.set(java.util.Calendar.HOUR, startHour);
////            calendar.set(java.util.Calendar.MINUTE, startMin);
////            calendar.set(java.util.Calendar.SECOND, 0);
////            workDay = calendar.getTime();
////            day.setStartTime(workDay);
////            calendar.setTime(endDay);
////            calendar.set(java.util.Calendar.HOUR, endHour);
////            calendar.set(java.util.Calendar.MINUTE, endMin);
////            calendar.set(java.util.Calendar.SECOND, 0);
////            day.setEndTime(endDay);
////            saveCalendar(day);
////            // Prepare to add slots
////            Date incTime = new Date(workDay.getTime());
////            List<TimeSlot> slots = new ArrayList<TimeSlot>();
////            int rowCount = 0;
////            while(incTime.after(workDay) && incTime.before(endDay)) {
////                TimeSlot slot = new TimeSlot();
////                slot.setAvailable(true);
////                slot.setCalendarId(day.getId());
////                slot.setDateTime(incTime);       
////                if(slotDao.addSlot(slot) == 1)
////                    rowCount++;
////                incTime = new Date(incTime.getTime() + (appointLength*60*1000));
////                slots.add(slot);
////                day.setSlots(slots);
////            } 
//            return rowCount;
//       }
//   }
}
