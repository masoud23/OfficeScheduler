/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Admin;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface AdminDAO {
    
    int authenticateAdmin(String username, String password);
    
    Admin getAdminById(int id);
    
    int addAdmin(Admin admin); //I had to change the type to int
    
    int updateAdmin(Admin admin); //I had to change the type to int
    
    int deleteAdmin(int id); //I had to change the type to int
    
    //maybe a list of all admins?    
    List<Admin> getAllAdmins(); 
}
