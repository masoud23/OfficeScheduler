/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedmanager;

import edu.weber.finalproject.schedpersistance.AdminDAO;
import edu.weber.finalproject.schedpersistance.PatientDAO;
import edu.weber.finalproject.schedschema.Admin;
import edu.weber.finalproject.schedschema.Patient;
import java.util.List;

/**
 *
 * @author Mike
 */
public class AdminManager {
    
    private AdminDAO dao;
    private PatientDAO patientDao;
  //  private AppointmentManager appMan;
    
    public AdminManager(AdminDAO dao, PatientDAO patientDao) {
        this.dao = dao;
        this.patientDao = patientDao;
    }
    
    public int authenticate(String user, String pass) {
        return dao.authenticateAdmin(user, pass);
    }
    
    public Admin getAdmin(int id) {
        return dao.getAdminById(id);
    }
    
    public List<Admin> getAllAdmin() {
        return dao.getAllAdmins();
    }
    
    public int saveAdmin(Admin admin) {
        return dao.addAdmin(admin);
    }
    
    public int editAdmin(Admin admin) {
        return dao.updateAdmin(admin);
    }
    
    public int deleteAdmin(int id) {
        return dao.deleteAdmin(id);
    }
    
    public Patient getPatient(int id) {
        return patientDao.getPatientById(id);
    }
    
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }
    
    public int savePatient(Patient patient) {
        return patientDao.addPatient(patient);
    }
    
    public int editPatient(Patient patient) {
        return patientDao.updatePatient(patient);
    }
    
    public int deletePatient(int id) {
        return patientDao.deletePatient(id);
    }
}
