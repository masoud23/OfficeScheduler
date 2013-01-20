/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedschema;

import java.util.Date;

/**
 *
 * @author Mike
 */
public class Appointment {
    
    private int id;                 
    private int slotId;
    private int patientExamId;
    private Date createdDate;


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {   
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getPatientExamId() {
        return patientExamId;
    }

    public void setPatientExamId(int patientExamId) {
        this.patientExamId = patientExamId;
    }
    
}
