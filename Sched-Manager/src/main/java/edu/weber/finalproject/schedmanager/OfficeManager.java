/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedmanager;

import edu.weber.finalproject.schedpersistance.ExamDAO;
import edu.weber.finalproject.schedpersistance.OfficeDAO;
import edu.weber.finalproject.schedpersistance.ResourceDAO;
import edu.weber.finalproject.schedschema.Exam;
import edu.weber.finalproject.schedschema.Office;
import edu.weber.finalproject.schedschema.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Combines data from Exams, Resources and Offices
 * @author Mike
 */
public class OfficeManager {
    
    private ExamDAO examMan;
    private OfficeDAO officeMan;
    private ResourceDAO resourceMan;
    
    public OfficeManager(OfficeDAO office, ResourceDAO resource, ExamDAO exam) {
        
        this.officeMan = office;
        this.resourceMan = resource;
        this.examMan = exam;
    }
    
    public Office getOffice(int id) {
        return officeMan.getOfficeById(id);
    }
    
    public List<Office> getAllOffices() {
        return officeMan.getAllOffices();
    }
    
    public List<Resource> getOfficeResources(int officeId) {
        return resourceMan.getResourcesByOffice(officeId);
    }
    
    public List<Exam> getResourceExamsByActive(boolean active, int resourceId) {
        return examMan.getActiveExamsByResource(active, resourceId);
    }
    
    public List<Resource> getAllResource() {
        return resourceMan.getResources();
    }
    /**
     * If we actually need this it would be better to write a new query rather than use two
     * @param resourceId
     * @return 
     */
    public List<Exam> getAllResourceExams(int resourceId) {
        List<Exam> allExams = new ArrayList<Exam>();
        allExams.addAll(examMan.getActiveExamsByResource(true, resourceId));
        allExams.addAll(examMan.getActiveExamsByResource(false, resourceId));
        return allExams;
    }
    
    public List<Exam> getAllExams() {
        return examMan.getAllExams();
    }
    
    public Resource getResource(int resourceId) {
        return resourceMan.getResourceById(resourceId);
    }
    
    public Exam getExam(int examId) {
        return examMan.getExamById(examId);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Add, Update, Delete Methods">
    public int addOffice(Office orifice) {
        return officeMan.addOffice(orifice);
    }
    
    public int addResource(Resource resource) {
        return resourceMan.addResource(resource);
    }
    
    public int addExam(Exam exam) {
        return examMan.addExam(exam);
    }
    
    public int updateOffice(Office off) {
        return officeMan.updateOffice(off);
    }
    
    public int updateResource(Resource res) {
        return resourceMan.updateResource(res);
    }
    
    public int updateExam(Exam exam) {
        return examMan.updateExam(exam);
    }
    
    public int deleteOffice(int officeId) {
        return officeMan.deleteOffice(officeId);
    }
    
    public int deleteResource(int resourceId) {
        return resourceMan.deleteResource(resourceId);
    }
    
    public int deleteExam(int exam) {
        return examMan.deleteExam(exam);
    }
    
    // </editor-fold>
}
