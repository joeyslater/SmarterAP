package edu.gatech.edutech.smarterap.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.gatech.edutech.smarterap.dtos.User;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - SmarterAP
 */
@Component
public class LoginValidator implements Validator
{
	//TODO Refactor error codes / fields / messages
	@Override
	public boolean supports(final Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "login.required.email", "Email address is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.required.password", "Password is required");
	}
}
