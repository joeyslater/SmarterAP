package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Subject extends BaseDto
{
	@Column(unique = true)
	private String	name;
	@Column
	private String	category;

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(final String category)
	{
		this.category = category;
	}

}
