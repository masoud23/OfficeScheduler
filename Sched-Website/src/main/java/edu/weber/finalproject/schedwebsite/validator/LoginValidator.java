
package edu.weber.finalproject.schedwebsite.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import edu.weber.finalproject.schedschema.User;
import org.springframework.validation.ValidationUtils;

/**
 *
 * @author ryan
 */
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.field", "Username cannot be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.field", "Password cannot be blank");
    }

}
