package edu.gatech.edutech.smarterap.validators;

import edu.gatech.edutech.smarterap.dtos.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Scott R. Leitstein on 11/30/15.
 */
@Component
public class ChangePasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "change.password.required.password", "Field password is required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "change.password.required.confirm.password", "Field confirm password is required");

        User customer = (User) o;

        if (!customer.getPassword().equals(customer.getConfirmedPassword())) {
            errors.rejectValue("password", "password.not.match");
        }
    }
}
