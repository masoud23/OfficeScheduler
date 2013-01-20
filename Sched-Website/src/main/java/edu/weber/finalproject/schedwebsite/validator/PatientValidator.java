/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedwebsite.validator;

import edu.weber.finalproject.schedschema.Patient;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mike
 */
public class PatientValidator implements Validator{

    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.field", "Username cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.field", "Password cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.field", "Email address is required to register online");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first", "required.field", "First name cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last", "required.field", "Last name cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required.field", "Address cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "required.field", "City cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "required.field", "State cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "required.field", "Zipcode cannot be blank");
        if(!errors.hasErrors()) {
            Patient temp = (Patient)o;
            String password = temp.getPassword();
            //lets make sure the password is long enough for our policy.
            if (password != null && password.trim().length() < MINIMUM_PASSWORD_LENGTH) {
                errors.rejectValue("password", "field.min.length",
                        new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
                        "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
            }
        }
        
    }
    
}
