/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Doctor;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface DoctorDAO {
    
    Doctor getDoctorById(int id);
    
    Doctor getDoctorByNpi(long npi);
    
    List<Doctor> getDoctors();
    
    int addDoctor(Doctor doctor);
    
    int updateDoctor(Doctor doctor);
    
    int deleteDoctor(int id);
    
}
