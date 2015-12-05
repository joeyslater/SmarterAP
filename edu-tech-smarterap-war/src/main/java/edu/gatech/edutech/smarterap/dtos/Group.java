package edu.gatech.edutech.smarterap.dtos;

import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class Group extends BaseDto
{
	private String	name;
	private String	description;
	private String	href;

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(final String href)
	{
		this.href = href;
	}

}
