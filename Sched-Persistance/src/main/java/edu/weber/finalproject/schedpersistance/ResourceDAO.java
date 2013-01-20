/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance;

import edu.weber.finalproject.schedschema.Resource;
import java.util.List;

/**
 *
 * @author Mike
 */
public interface ResourceDAO {
    
    Resource getResourceById(int id);
    
    List<Resource> getResources();
    
    int addResource(Resource resource);
    
    int updateResource(Resource resource);
    
    int deleteResource(int id);
}
