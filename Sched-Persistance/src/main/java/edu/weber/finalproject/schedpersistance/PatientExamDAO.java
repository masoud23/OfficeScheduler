/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.PatientExam;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface PatientExamDAO {
    
    PatientExam getPatientExamById(int id);
    
    List<PatientExam> getAllPatientExams();
    
    /**
     * For billing and history purposes
     * @param patientId
     * @return 
     */
    List<PatientExam> getPatientsPastExams(int patientId);
    
    List<PatientExam> getPatientsFutureExams(int patientId);
    
    int addPatientExam(PatientExam exam);
    
    int updatePatientExam(PatientExam exam);
    
    int deletePatientExam(int deleteId);
}
