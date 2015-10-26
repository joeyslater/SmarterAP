package edu.gatech.edutech.smarterap.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.tenant.Tenant;

import edu.gatech.edutech.smarterap.dtos.User;

@Controller
@RequestMapping("/register")
public class RegistrationController
{

	@Autowired
	private Client stormpathClient;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String register()
	{
		final User user = new User();
		final String password = "";
		System.out.println("Test");
		try
		{
			final Account account = stormpathClient.instantiate(Account.class).setUsername(user.getUsername()).setEmail(user.getEmailAddress()).setGivenName(user.getGivenName())
			        .setSurname(user.getSurname()).setPassword(password);

			final Tenant tenant = stormpathClient.getCurrentTenant();
			final ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("gatech-edutech-smarterap")));

			final Application application = applications.iterator().next();
			application.createAccount(account);
		}
		catch (final Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "What";
	}

}
