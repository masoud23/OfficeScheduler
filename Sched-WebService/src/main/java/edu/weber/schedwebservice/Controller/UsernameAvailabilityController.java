package edu.weber.schedwebservice.Controller;

import edu.weber.finalproject.schedmanager.PatientManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Checks if a username is available for use.
 * @author ryan
 */
@Controller
@RequestMapping("/reg/username/available")
public class UsernameAvailabilityController {
    private PatientManager patMan;
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public boolean Check(@RequestParam("un") String username) {
        //return patMan.checkExistingUserNames(username);
        return true;
    }
}
