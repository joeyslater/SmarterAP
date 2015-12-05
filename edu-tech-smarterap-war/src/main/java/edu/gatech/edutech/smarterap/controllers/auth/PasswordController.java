package edu.gatech.edutech.smarterap.controllers.auth;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.stormpath.sdk.resource.ResourceException;

import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.StormpathService;
import edu.gatech.edutech.smarterap.validators.EmailValidator;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - Updated for SmarterAP
 * @author Scott Leitstein - Updated for SmarterAP
 */
@RestController
@RequestMapping("/password")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PasswordController
{
	@Autowired
	private EmailValidator		emailValidator;

	@Autowired
	private StormpathService	stormpathService;

	@RequestMapping(value = "/reset", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<String> processResetPassword(final @RequestBody String email, final BindingResult result, final SessionStatus status, final HttpSession session,
	        final HttpServletResponse response)
	{
		emailValidator.validate(email, result);

		if (result.hasErrors())
		{
			return new JsonResponse<>(false, "ERROR: Password Reset, Email Validation Error.", "/reset-password");
		}
		else
		{
			try
			{
				stormpathService.sendPasswordResetEmail(email);
				status.setComplete();
			}
			catch (final ResourceException e)
			{
				response.setStatus(e.getStatus());
				result.addError(new ObjectError("email", e.getCode() == 404 ? "The provided email for password reset does not exist." : e.getMessage()));
				e.printStackTrace();
				return new JsonResponse<>(false, result.getAllErrors().toString(), "/reset-password");
			}
		}

		// Success
		return new JsonResponse<>(true, "Sent Password Reset Email.", "/login");
	}

	@RequestMapping(value = "/resendVerification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<String> processResendVerification(final @RequestBody String email, final BindingResult result, final SessionStatus status, final HttpSession session,
	        final HttpServletResponse response)
	{
		emailValidator.validate(email, result);

		if (result.hasErrors())
		{
			return new JsonResponse<>(false, "ERROR: Resend Email Verification, Email Validation Error.", "/resend-verification");
		}
		else
		{
			try
			{
				stormpathService.sendVerificationEmail(email);
				status.setComplete();
			}
			catch (final ResourceException e)
			{
				response.setStatus(e.getStatus());
				result.addError(new ObjectError("email", e.getCode() == 404 ? "The provided email for the email verification does not exist." : e.getMessage()));
				e.printStackTrace();
				return new JsonResponse<>(false, result.getAllErrors().toString(), "/resend-verification");
			}
		}

		// Success
		return new JsonResponse<>(true, "Resent Email Verification email.", "/login");
	}
}
