/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedschema;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is an aggregate object to ease data mapping into JSon for JQuery calendar population.
 * 
 * @author Mike
 */
@XmlRootElement (name = "event")
public class Event {
    
    private int id;             //appouintmentId
    private String title;       //patient name
    private Date start;         //appointment startDate
    private Date end;           // calculated from start and appointment length 
    private String url;
    private boolean allDay;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public String getEnd() {
        return sdf.format(end);
    }

    public void setEndDate(Date end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return sdf.format(start);
    }

    public void setStartDate(Date start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
    
}
