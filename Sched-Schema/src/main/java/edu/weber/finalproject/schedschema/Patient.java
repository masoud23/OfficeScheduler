/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedschema;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mike
 */
public class Patient extends User {
    
    private int addressId;
    private Date birthDate;             //maybe a java.sql.Date here??
    private String address;
    private String city;
    private String state;
    private String zip;
    private List<PatientExam> futureExams;
    private List<PatientExam> examHistory;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }    

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }   
    
}
