package edu.gatech.edutech.smarterap.enums;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public enum SecurityRole {
	ADMIN("https://api.stormpath.com/v1/groups/4fSFW1N4MOPaPRt892dnX1"), STUDENT(
			"https://api.stormpath.com/v1/groups/3zVltAWBLvUcqQ6TK2Bczb"), TEACHER(
					"https://api.stormpath.com/v1/groups/4PDEkxAduB0BRqXVFaIQpv");

	private static final Map<String, SecurityRole> VALUES = Maps.newHashMap();

	static {
		for (SecurityRole role : values()) {
			VALUES.put(role.toString(), role);
		}
	}

	private String href;

	private SecurityRole(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return href;
	}

	public static String[] returnAllRoles() {
		List<String> hrefs = Lists.newArrayList();
		for (SecurityRole role : SecurityRole.values()) {
			hrefs.add(role.toString());
		}
		return hrefs.toArray(new String[hrefs.size()]);
	}

	public static SecurityRole toEnum(String href) {
		if (StringUtils.isNotBlank(href)) {
			return VALUES.get(href);
		}
		return null;
	}

	public static Set<SecurityRole> getSecurityFromHrefs(String... hrefs) {

		Set<SecurityRole> roles = Sets.newHashSet();
		if (hrefs.length > 0) {
			for (String href : hrefs) {
				roles.add(SecurityRole.toEnum(href));
			}
		}
		return roles;
	}
}
