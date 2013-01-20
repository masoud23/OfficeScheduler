/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Office;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface OfficeDAO {
    
    Office getOfficeById(int officeId);
    
    List<Office> getAllOffices();
    
    int addOffice(Office office);
    
    int updateOffice(Office office);
    
    int deleteOffice(int deleteId);
}
