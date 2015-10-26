package edu.gatech.edutech.smarterap.dtos;

import java.util.Set;

import edu.gatech.edutech.smarterap.enums.SecurityRole;

public class User
{
	private String				username;
	private String				givenName;
	private String				surname;
	private String				emailAddress;

	private Set<SecurityRole>	securityRoles;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getGivenName()
	{
		return givenName;
	}

	public void setGivenName(final String givenName)
	{
		this.givenName = givenName;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(final String surName)
	{
		surname = surName;
	}

	public Set<SecurityRole> getSecurityRoles()
	{
		return securityRoles;
	}

	public void setSecurityRoles(final Set<SecurityRole> securityRoles)
	{
		this.securityRoles = securityRoles;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(final String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

}
