package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Tag extends BaseDto
{
	@Column
	private String	name;

	@ManyToOne(cascade = CascadeType.ALL)
	private Subject	subject;

	@Column
	private boolean	enabled	= true;

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(final Subject subject)
	{
		this.subject = subject;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;
	}

}
