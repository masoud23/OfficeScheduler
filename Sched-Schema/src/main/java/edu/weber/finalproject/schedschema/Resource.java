/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedschema;

/**
 *
 * @author Mike
 */
public class Resource {
    
    private int id;
    private int officeId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of officeId
     *
     * @return the value of officeId
     */
    public int getOfficeId() {
        return officeId;
    }

    /**
     * Set the value of officeId
     *
     * @param officeId new value of officeId
     */
    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    
}
