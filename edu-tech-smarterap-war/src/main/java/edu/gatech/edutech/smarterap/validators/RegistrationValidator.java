package edu.gatech.edutech.smarterap.validators;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.gatech.edutech.smarterap.dtos.User;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - SmarterAP
 */
@Component
public class RegistrationValidator implements Validator
{
	//TODO Refactor error codes / fields / messages
	@Override
	public boolean supports(final Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object o, final Errors errors)
	{
		rejectIfEmptyOrWhitespace(errors, "emailAddress", "registration.required.emailAddress", "Email address is required");
		rejectIfEmptyOrWhitespace(errors, "givenName", "registration.required.givenName", "First name is required");
		rejectIfEmptyOrWhitespace(errors, "surname", "registration.required.surname", "Last name is required");
		rejectIfEmptyOrWhitespace(errors, "password", "registration.required.password", "Password is required");
		rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "registration.required.confirmedPassword", "Confirmed password is required");

		final User user = (User) o;
		if (isBlank(user.getPassword()) || isBlank(user.getConfirmedPassword()) || !user.getPassword().equals(user.getConfirmedPassword()))
		{
			errors.rejectValue("password", "password.not.match", "Passwords do not match.");
		}
	}
}
