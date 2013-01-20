/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedmanager;

import edu.weber.finalproject.schedpersistance.PatientDAO;
import edu.weber.finalproject.schedschema.Patient;

/**
 *
 * @author Mike
 */
public class PatientManager {
    
    private PatientDAO dao;
    
    public PatientManager(PatientDAO dao) {
        this.dao = dao;
    }
    
    public boolean checkExistingUserNames(String userName) {
        return dao.checkExistingUserNames(userName);
    }
    
    public int authenticate(String user, String pass) {
        return dao.authenticatePatient(user, pass);
    }
    
    public Patient getPatient(int id) {
        return dao.getPatientById(id);
    }
    
    public int save(Patient patient) {
        return dao.addPatient(patient);
    }
    
    public int edit(Patient patient) {
        return dao.updatePatient(patient);
    }
    
    public int delete(int id) {
        return dao.deletePatient(id);
    }
}
