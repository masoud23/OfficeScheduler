/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedschema;

/**
 *
 * @author Mike
 */
public class Result {
    
    private int id;
    private int patientExamId;
    private String result;          //maybe another object with more detail??

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientExamId() {
        return patientExamId;
    }

    public void setPatientExamId(int patientExamId) {
        this.patientExamId = patientExamId;
    }
 
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
