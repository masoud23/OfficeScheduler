/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedschema;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mike
 */
@XmlRootElement (name="calendar")
public class Calendar {
    
    private int id;
    private int resourceId;     
    private int slotSpanMins;        
    private Date workStartTime;
    private Date workEndTime;
    private boolean includeSats;
    private boolean includeSuns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isIncludeSats() {
        return includeSats;
    }

    public void setIncludeSats(boolean includeSats) {
        this.includeSats = includeSats;
    }

    public boolean isIncludeSuns() {
        return includeSuns;
    }

    public void setIncludeSuns(boolean includeSuns) {
        this.includeSuns = includeSuns;
    }

    public int getSlotSpanMins() {
        return slotSpanMins;
    }

    public void setSlotSpanMins(int slotSpanMins) {
        this.slotSpanMins = slotSpanMins;
    }

    public Date getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(Date workEndTime) {
        this.workEndTime = workEndTime;
    }

    public Date getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    } 
}
