/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedmanager;

import edu.weber.finalproject.schedpersistance.AppointmentDAO;
import edu.weber.finalproject.schedpersistance.DoctorDAO;
import edu.weber.finalproject.schedpersistance.ExamDAO;
import edu.weber.finalproject.schedpersistance.ResultDAO;
import edu.weber.finalproject.schedschema.Appointment;
import edu.weber.finalproject.schedschema.Exam;
import edu.weber.finalproject.schedschema.Result;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mike
 */
public class AppointmentManager {
    
    private AppointmentDAO dao;
    private DoctorDAO docDao;
    private ExamDAO examDao;
    private ResultDAO resultDao;
    
    public AppointmentManager(AppointmentDAO dao, DoctorDAO docDao, ExamDAO examDao, ResultDAO resDao) {
        this.dao = dao;
        this.docDao = docDao;
        this.examDao = examDao;
        this.resultDao = resDao;
    }
    
    public Appointment getAppointment(int id) {
        return dao.getAppointmentById(id);
    }
    
    public List<Appointment> getPatientAppointments(int patientId) {
        return dao.getAppointmentsByPatientId(patientId);
    }
    
    public List<Appointment> getAllAppointments() {
        return dao.getAllAppointments();
    }

    public boolean saveAppointment(Appointment appointment) {
        if(appointment.getId() < 1){
            return (dao.addAppointment(appointment) == 1) ? true : false;
        }
        else {
            return (dao.updateAppointment(appointment) == 1) ? true : false;
        }
    }
    
    public boolean deleteAppointment(int appId) {
        return (dao.deleteAppointment(appId) == 1) ? true : false;
    }
    
//    public Exam getExamByAppointment(int appointmentId) {
//        int examId = dao.getAppointmentById(appointmentId).getExamId();
//        return examDao.getExamById(examId);
//    }
//    
//    public Result getAppointmentResult(int appointmentId) {
//        int resultId = dao.getAppointmentById(appointmentId).getResultId();
//        return resultDao.getResultById(resultId);
//    }
    
    public boolean saveExam(Exam exam) {
        return (examDao.addExam(exam) == 1) ? true : false;
    }
    
    public boolean editExam(Exam exam) {
        return (examDao.updateExam(exam) == 1) ? true : false;
    }
    
    public boolean deleteExam(int examId) {
        return (examDao.deleteExam(examId) == 1) ? true : false;
    }
    
    public boolean saveResult(Result result) {
        return (resultDao.addResult(result) == 1) ? true : false;
    }
    
    public boolean editResult(Result result) {
        return (resultDao.updateResult(result) == 1) ? true : false;
    }
    
    public boolean deleteResult(int resultId) {
        return (resultDao.deleteResult(resultId) == 1) ? true : false;
    }
//    public Doctor getAppointmentDoc(int appointmentId) {
//        int docId = dao.getAppointmentById(appointmentId).getDoctorId();
//          return docDao.getDoctorById(docId);
//    }
}
