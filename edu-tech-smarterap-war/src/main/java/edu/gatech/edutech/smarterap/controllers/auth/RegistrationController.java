package edu.gatech.edutech.smarterap.controllers.auth;

import static edu.gatech.edutech.smarterap.utils.UserBuilderUtil.build;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.resource.ResourceException;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.enums.SecurityRole;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.StormpathService;
import edu.gatech.edutech.smarterap.validators.RegistrationValidator;

/**
 * @author Elder Crisostomo  - Template author
 * @author Scott R. Leitstein - Customized for SmarterAP
 * @author  Joey Slater - Customized for SmarterAP
 */
@Controller
@RequestMapping("/register")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegistrationController
{
	@Autowired
	private RegistrationValidator	registrationValidator;

	@Autowired
	private StormpathService		stormpathService;

	@Autowired
	private CrudService				crudService;

	public RegistrationController()
	{
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<String> register(final @RequestBody User user, final BindingResult result, final SessionStatus status, final HttpSession session,
	        final HttpServletResponse response)
	{
		registrationValidator.validate(user, result);

		try
		{
			if (!result.hasErrors())
			{
				final Account account = stormpathService.getDataStore().instantiate(Account.class);
				account.setEmail(user.getUsername());
				account.setGivenName(user.getGivenName());
				account.setSurname(user.getSurname());
				account.setPassword(user.getPassword());
				stormpathService.getApplication().createAccount(account);

				final GroupList groups = stormpathService.getApplication().getGroups();
				for (final Group group : groups)
				{
					if (SecurityRole.STUDENT.toString().equals(group.getHref()))
					{
						account.addGroup(group);
						break;
					}
				}

				crudService.create(build(account));
				status.setComplete();
				return new JsonResponse<String>(true, "Successfully registered.", "/dashboard");
			}
		}
		catch (final ResourceException e)
		{
			response.setStatus(e.getStatus());
			session.invalidate();
			status.setComplete();
			return new JsonResponse<String>(false, e.getDeveloperMessage(), "/register");
		}
		return new JsonResponse<String>(false, result.getAllErrors().get(0).getDefaultMessage().toString(), "/register");
	}

}
