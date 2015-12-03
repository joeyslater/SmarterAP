package edu.gatech.edutech.smarterap.controllers.auth;

import com.stormpath.sdk.account.VerificationEmailRequest;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.resource.ResourceException;
import edu.gatech.edutech.smarterap.daos.UserDao;
import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.IStormpathService;
import edu.gatech.edutech.smarterap.validators.ChangePasswordValidator;
import edu.gatech.edutech.smarterap.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.gatech.edutech.smarterap.validators.ResetPasswordValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - Updated for SmarterAP
 * @author Scott Leitstein - Updated for SmarterAP
 */
@Controller
@RequestMapping("/password")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PasswordController {
	ChangePasswordValidator changePasswordValidator;
	EmailValidator emailValidator;

	@Autowired
	UserDao userDao;

	@Autowired
	IStormpathService stormpath;

	public PasswordController() {

	}

	@Autowired
	public PasswordController(ChangePasswordValidator changePasswordValidator, EmailValidator emailValidator) {
		this.changePasswordValidator = changePasswordValidator;
		this.emailValidator = emailValidator;
	}


	@RequestMapping(value = "/reset", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<String> processResetPassword(final @RequestBody String email, final BindingResult result, final SessionStatus status, final HttpSession session,
													 final HttpServletResponse response) {

		emailValidator.validate(email, result);

		if (result.hasErrors()) {
			return new JsonResponse<>(false, "ERROR: Password Reset, Email Validation Error.", "/reset-password");
		} else {
			try {
				stormpath.getApplication().sendPasswordResetEmail(email);
				status.setComplete();
			} catch (ResourceException re) {
				response.setStatus(re.getStatus());
				result.addError(new ObjectError("email", re.getCode() == 404 ? "The provided email for password reset does not exist." : re.getMessage()));
				re.printStackTrace();
				return new JsonResponse<>(false, result.getAllErrors().toString(), "/reset-password");
			}
		}

		// Success
		return new JsonResponse<>(true, "Sent Password Reset Email.", "/login");
	}

	@RequestMapping(value = "/resendVerification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<String> processResendVerification(final @RequestBody String email, final BindingResult result, final SessionStatus status, final HttpSession session,
													 final HttpServletResponse response) {

		emailValidator.validate(email, result);

		if (result.hasErrors()) {
			return new JsonResponse<>(false, "ERROR: Resend Email Verification, Email Validation Error.", "/resend-verification");
		} else {
			try {
				VerificationEmailRequest verificationEmailRequest = Applications.verificationEmailBuilder()
						.setLogin(email)
						.setAccountStore(stormpath.getDirectory())
						.build();
				stormpath.getApplication().sendVerificationEmail(verificationEmailRequest);
				status.setComplete();
			} catch (ResourceException re) {
				response.setStatus(re.getStatus());
				result.addError(new ObjectError("email", re.getCode() == 404 ? "The provided email for the email verification does not exist." : re.getMessage()));
				re.printStackTrace();
				return new JsonResponse<>(false, result.getAllErrors().toString(), "/resend-verification");
			}
		}

		// Success
		return new JsonResponse<>(true, "Resent Email Verification email.", "/login");
	}
}
