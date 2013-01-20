/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Appointment;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface AppointmentDAO {
    
    List<Appointment> getAllAppointments();
    
    /**
     * @deprecated
     * @param patientId
     * @return 
     */
    List<Appointment> getAppointmentsByPatientId(int patientId);
    
    Appointment getAppointmentById(int id);
    
    Appointment getAppointmentBySlotId(int slotId);
    
    int addAppointment(Appointment app);
    
    int updateAppointment(Appointment app);
    
    int deleteAppointment(int id);
}
