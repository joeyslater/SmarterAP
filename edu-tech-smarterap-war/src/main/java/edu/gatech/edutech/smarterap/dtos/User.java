package edu.gatech.edutech.smarterap.dtos;

import java.util.Set;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.gatech.edutech.smarterap.enums.SecurityRole;

@AutoProperty
@JsonInclude(Include.NON_NULL)
public class User extends BaseDto
{
	private String				givenName;
	private String				surname;
	private String				username;

	private String				password;
	private String				confirmedPassword;

	private Set<SecurityRole>	securityRoles;

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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String Username)
	{
		username = Username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getConfirmedPassword()
	{
		return confirmedPassword;
	}

	public void setConfirmedPassword(final String confirmedPassword)
	{
		this.confirmedPassword = confirmedPassword;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

}
