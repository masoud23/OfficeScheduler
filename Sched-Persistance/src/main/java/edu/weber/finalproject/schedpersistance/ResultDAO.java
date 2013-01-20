/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Result;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface ResultDAO {
    
    Result getResultById(int id);
    
    List<Result> getResultsByPatientExam(int patientExamId);  //Is this one to one or many to one? It is one to one
    
    /**
     * Not sure this is necessary.  Deprecated so I don't forget to review managers first.
     * @deprecated
     */
    List<Result> getAllResults();
    
    int updateResult(Result result);
    
    int addResult(Result result);
    
    int deleteResult(int id);
}
