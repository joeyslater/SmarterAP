package edu.gatech.edutech.smarterap.enums;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public enum SecurityRole
{
	// hrefs gathered from Stormpath
	ADMIN("https://api.stormpath.com/v1/groups/4fSFW1N4MOPaPRt892dnX1"), STUDENT("https://api.stormpath.com/v1/groups/3zVltAWBLvUcqQ6TK2Bczb"), TEACHER(
	        "https://api.stormpath.com/v1/groups/4PDEkxAduB0BRqXVFaIQpv");

	private static final Map<String, SecurityRole> VALUES = Maps.newHashMap();

	static
	{
		for (final SecurityRole role : values())
		{
			VALUES.put(role.toString(), role);
		}
	}

	private final String href;

	private SecurityRole(final String href)
	{
		this.href = href;
	}

	@Override
	public final String toString()
	{
		return href;
	}

	public static String[] returnAllRoles()
	{
		final List<String> hrefs = Lists.newArrayList();
		for (final SecurityRole role : SecurityRole.values())
		{
			hrefs.add(role.toString());
		}
		return hrefs.toArray(new String[hrefs.size()]);
	}

	public static SecurityRole toEnum(final String href)
	{
		if (StringUtils.isNotBlank(href))
		{
			return VALUES.get(href);
		}
		return null;
	}

	public static Set<SecurityRole> getSecurityFromHrefs(final String... hrefs)
	{
		final Set<SecurityRole> roles = Sets.newHashSet();
		if (hrefs.length > 0)
		{
			for (final String href : hrefs)
			{
				roles.add(SecurityRole.toEnum(href));
			}
		}
		return roles;
	}
}
