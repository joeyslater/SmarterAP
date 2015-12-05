package edu.gatech.edutech.smarterap.controllers.auth;

import static edu.gatech.edutech.smarterap.utils.UserBuilderUtil.build;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.StormpathService;
import edu.gatech.edutech.smarterap.services.UserService;
import edu.gatech.edutech.smarterap.validators.LoginValidator;

/**
 * @author Elder Crisostomo
 * @author Scott R. Leitstein - Updated for SmarterAp
 * @author Joey Slater - Updated for SmarterAP
 * @since 0.1
 */
@Controller
@RequestMapping("/authenticate")
public class LoginController
{
	@Autowired
	private LoginValidator		loginValidator;

	@Autowired
	private StormpathService	stormpathService;

	@Autowired
	private CrudService			crudService;

	@Autowired
	private UserService			userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<String> authenticate(final @RequestBody User user, final BindingResult result, final SessionStatus status, final HttpSession session,
	        final HttpServletResponse response)
	{
		loginValidator.validate(user, result);
		if (!result.hasErrors())
		{
			try
			{
				final AuthenticationRequest request = UsernamePasswordRequest.builder().setUsernameOrEmail(user.getUsername()).setPassword(user.getPassword()).build();
				final AuthenticationResult authResult = stormpathService.authenticate(request);
				session.setAttribute("sessionUser", authResult.getAccount());
				status.setComplete();

				final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
				SecurityContextHolder.getContext().setAuthentication(authRequest);

				final User userFromService = userService.getUserFromEmail(authResult.getAccount().getEmail());
				if (userFromService == null || userFromService.getUid() == -1)
				{
					crudService.create(build(authResult.getAccount()));
				}
				return new JsonResponse<String>(true, "Successfully authenticated", "/dashboard");
			}
			catch (final Exception e)
			{
				e.printStackTrace();
				session.invalidate();
				status.setComplete();
				response.setStatus(409);
				return new JsonResponse<String>(false, "The email and password you entered don't match.", "/login");
			}
		}
		status.setComplete();
		response.setStatus(409);
		return new JsonResponse<String>(false, result.getAllErrors().get(0).getDefaultMessage().toString(), "/login");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/emailVerificationTokens")
	@ResponseBody
	public JsonResponse<String> verifyAccount(@RequestParam("sptoken") final String token, final User user, final BindingResult result)
	{
		try
		{
			stormpathService.verifyAccountEmail(token);
			return new JsonResponse<String>(true, "Successfully authenticated", "/dashboard");
		}
		catch (final RuntimeException re)
		{
			result.addError(new ObjectError("userName", re.getMessage()));
			return new JsonResponse<String>(true, "Issue verifying account. Try again.", "/login");
		}
	}
}
