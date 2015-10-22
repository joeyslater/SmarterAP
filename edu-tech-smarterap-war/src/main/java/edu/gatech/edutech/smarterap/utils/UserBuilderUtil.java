package edu.gatech.edutech.smarterap.utils;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Sets;
import com.stormpath.spring.security.provider.StormpathUserDetails;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.enums.SecurityRole;

public class UserBuilderUtil {

	private UserBuilderUtil(){
		
	}
	
	public static User build(StormpathUserDetails details){
		User user = new User();
		user.setUsername(details.getUsername());
		user.setGivenName(details.getProperties().get("givenName"));
		user.setSurname(details.getProperties().get("surname"));
		user.setEmailAddress(details.getProperties().get("email"));
		user.setSecurityRoles(convertSecurities(details.getAuthorities()));
		return user;
	}

	private static Set<SecurityRole> convertSecurities(Collection<? extends GrantedAuthority> authorities) {
		Set<SecurityRole> roles = Sets.newHashSet();
		for (GrantedAuthority authority : authorities) {
			roles.add(SecurityRole.toEnum(authority.getAuthority()));
		}
		return roles;
	}
}