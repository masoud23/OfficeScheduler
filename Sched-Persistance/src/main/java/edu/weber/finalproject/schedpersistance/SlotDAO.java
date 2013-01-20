/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.TimeSlot;
import java.util.List;

/**
 *
 * @author Masoud
 */
public interface SlotDAO {
    
    TimeSlot getSlotById(int id);
    
    List<TimeSlot>getSlotsByCalendarId(int calId);
    
    List<TimeSlot> getAllSlots();
    
    List<TimeSlot> getAllAvailableSlots();
    
    int addSlot(TimeSlot slot);
    
    int updateSlot(TimeSlot slot);
    
    int deleteSlot(int id);
}
