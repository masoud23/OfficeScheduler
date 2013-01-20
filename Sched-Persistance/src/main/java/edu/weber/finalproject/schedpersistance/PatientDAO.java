/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Patient;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface PatientDAO {
    
    int authenticatePatient(String username, String pass);
    
    boolean checkExistingUserNames(String userName);
    
    Patient getPatientById(int id);
    
    List<Patient> getAllPatients();
    
    int addPatient(Patient patient); //I had to change the type to int
    
    int updatePatient(Patient patient); //I had to change the type to int
    
    int deletePatient(int id); //I had to change the type to int
}
