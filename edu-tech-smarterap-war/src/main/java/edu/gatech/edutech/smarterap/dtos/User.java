package edu.gatech.edutech.smarterap.dtos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.gatech.edutech.smarterap.enums.SecurityRole;

@Entity(name = "SmarterApUser")
@AutoProperty
@JsonInclude(Include.NON_NULL)
public class User extends BaseDto implements Serializable
{
	@Transient
	@JsonIgnore
	private static final long	serialVersionUID	= 5105193881992016174L;

	@JsonIgnore
	@Column(unique = true)
	private String				href;

	@Transient
	private String				givenName;

	@Transient
	private String				surname;

	@Column(unique = true)
	private String				username;

	@JsonProperty
	@Transient
	private String				password;
	@JsonIgnore
	@Transient
	private String				confirmedPassword;
	@Transient
	private String				status;
	@Transient
	private Set<SecurityRole>	securityRoles;

	public String getHref()
	{
		return href;
	}

	public void setHref(final String href)
	{
		this.href = href;
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String Username)
	{
		username = Username;
	}

	@JsonIgnore
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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(final String status)
	{
		this.status = status;
	}

}
