package edu.gatech.edutech.smarterap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.VerificationEmailRequest;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.resource.ResourceException;

import edu.gatech.edutech.smarterap.daos.StormpathDao;
import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.enums.SecurityRole;

@Service
public class StormpathService
{
	@Autowired
	private StormpathDao stormpathDao;

	public AuthenticationResult authenticate(final AuthenticationRequest request)
	{
		return stormpathDao.getApplication().authenticateAccount(request);
	}

	public Account getTestAccount(final String string)
	{
		return stormpathDao.getClient().getResource("https://api.stormpath.com/v1/accounts/PIxmeeDbTNL5SqBOAWs3c", Account.class);
	}

	public Account getNewAccount()
	{
		return stormpathDao.getDataStore().instantiate(Account.class);
	}

	public Account createNewAccount(final User user) throws ResourceException
	{
		final Account account = getNewAccount();
		account.setEmail(user.getEmail());
		account.setGivenName(user.getGivenName());
		account.setSurname(user.getSurname());
		account.setPassword(user.getPassword());
		stormpathDao.getApplication().createAccount(account);

		final GroupList groups = stormpathDao.getApplication().getGroups();
		for (final Group group : groups)
		{
			if (SecurityRole.STUDENT.toString().equals(group.getHref()))
			{
				account.addGroup(group);
				break;
			}
		}
		return account;
	}

	public Account verifyAccountEmail(final String token)
	{
		return stormpathDao.getTenant().verifyAccountEmail(token);
	}

	public void sendVerificationEmail(final String email)
	{
		final VerificationEmailRequest verificationEmailRequest = Applications.verificationEmailBuilder().setLogin(email).setAccountStore(stormpathDao.getDirectory()).build();
		stormpathDao.getApplication().sendVerificationEmail(verificationEmailRequest);
	}

	public void sendPasswordResetEmail(final String email)
	{
		stormpathDao.getApplication().sendPasswordResetEmail(email);
	}

}
