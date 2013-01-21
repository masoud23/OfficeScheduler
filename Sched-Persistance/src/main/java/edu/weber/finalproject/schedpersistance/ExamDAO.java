/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Exam;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface ExamDAO {
    
    Exam getExamById(int id);
    
    List<Exam>getAllExams();
    
    List<Exam> getExamsByActive(boolean active);
    
    List<Exam> getActiveExamsByResource(boolean active, int resourceId);
    
    int addExam(Exam exam);
    
    int updateExam(Exam exam);
    
    int deleteExam(int id);
}
