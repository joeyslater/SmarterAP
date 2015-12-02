package edu.gatech.edutech.smarterap.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.gatech.edutech.smarterap.dtos.User;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - SmarterAP
 * @author Scott Leitstein - SmarterAP
 */
@Component
public class ResetPasswordValidator implements Validator
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
		String email = (String)o;

		if (isBlank(email))
		{
			errors.rejectValue("email", "resetPassword.invalid.email", "Invalid email address.");
		}
	}
}
