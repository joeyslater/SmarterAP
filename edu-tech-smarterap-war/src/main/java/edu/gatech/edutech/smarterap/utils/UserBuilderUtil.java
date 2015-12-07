package edu.gatech.edutech.smarterap.utils;

import java.util.Set;

import com.google.common.collect.Sets;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.GroupList;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.enums.SecurityRole;

public class UserBuilderUtil
{
	private UserBuilderUtil()
	{

	}

	public static User build(final Account account)
	{
		final User user = new User();
		user.setGivenName(account.getGivenName());
		user.setSurname(account.getSurname());
		user.setUsername(account.getEmail());
		user.setStatus(account.getStatus().toString());
		user.setHref(account.getHref());
		if (account.getGroups() != null)
		{
			user.setSecurityRoles(convertSecurities(account.getGroups()));
		}
		return user;
	}

	private static Set<SecurityRole> convertSecurities(final GroupList groups)
	{
		final Set<SecurityRole> roles = Sets.newHashSet();
		for (final com.stormpath.sdk.group.Group group : groups)
		{
			roles.add(SecurityRole.toEnum(group.getHref()));
		}
		return roles;
	}

}
