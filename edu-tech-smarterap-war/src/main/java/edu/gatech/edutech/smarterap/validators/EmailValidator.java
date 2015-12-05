package edu.gatech.edutech.smarterap.validators;

import static org.apache.commons.lang3.StringUtils.isBlank;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Scott R. Leitstein on 12/1/15.
 */

import edu.gatech.edutech.smarterap.dtos.User;

@Component
public class EmailValidator implements Validator
{
	//TODO Refactor error codes / fields / messages

	public boolean isValidEmailAddress(final String email)
	{
		// source: http://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
		final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		final java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		final java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public boolean supports(final Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object o, final Errors errors)
	{
		final String email = (String) o;

		if (isBlank(email) || !isValidEmailAddress(email))
		{
			errors.rejectValue("email", "resetPassword.invalid.email", "Invalid email address.");
		}
	}
}