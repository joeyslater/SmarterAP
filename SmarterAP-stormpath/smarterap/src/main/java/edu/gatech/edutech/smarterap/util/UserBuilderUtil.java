package edu.gatech.edutech.smarterap.util;

import com.google.common.collect.Sets;
import com.stormpath.spring.security.provider.StormpathUserDetails;
import edu.gatech.edutech.smarterap.model.User;
import edu.gatech.edutech.smarterap.enums.SecurityRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class UserBuilderUtil
{
	private UserBuilderUtil()
	{

	}

	public static User build(final StormpathUserDetails details)
	{
		final User user = new User();
		user.setUserName(details.getProperties().get("username"));
		user.setFirstName(details.getProperties().get("givenName"));
		user.setLastName(details.getProperties().get("surname"));
		user.setEmail(details.getProperties().get("email"));
//		user.setSecurityRoles(convertSecurities(details.getAuthorities()));
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
}
