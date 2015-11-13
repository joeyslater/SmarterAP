package edu.gatech.edutech.smarterap.utils;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Sets;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.spring.security.provider.StormpathUserDetails;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.enums.SecurityRole;

public class UserBuilderUtil
{
	private UserBuilderUtil()
	{

	}

	public static User build(final StormpathUserDetails details)
	{
		final User user = new User();
		user.setGivenName(details.getProperties().get("givenName"));
		user.setSurname(details.getProperties().get("surname"));
		user.setUsername(details.getProperties().get("email"));
		user.setSecurityRoles(convertSecurities(details.getAuthorities()));
		return user;
	}

	public static User build(final Account account)
	{
		final User user = new User();
		user.setGivenName(account.getGivenName());
		user.setSurname(account.getSurname());
		user.setUsername(account.getEmail());
		if (account.getGroups() != null)
		{
			user.setSecurityRoles(convertSecurities(account.getGroups()));
		}
		return user;
	}

	private static Set<SecurityRole> convertSecurities(final Collection<? extends GrantedAuthority> authorities)
	{
		final Set<SecurityRole> roles = Sets.newHashSet();
		for (final GrantedAuthority authority : authorities)
		{
			roles.add(SecurityRole.toEnum(authority.getAuthority()));
		}
		return roles;
	}

	private static Set<SecurityRole> convertSecurities(final GroupList groups)
	{
		final Set<SecurityRole> roles = Sets.newHashSet();
		for (final Group group : groups)
		{
			roles.add(SecurityRole.toEnum(group.getHref()));
		}
		return roles;
	}

}
