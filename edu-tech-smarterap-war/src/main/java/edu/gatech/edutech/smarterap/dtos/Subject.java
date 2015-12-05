package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Subject extends BaseDto implements Comparable<Subject>
{
	@Column(unique = true)
	private String	name;
	@Column
	private String	category;
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

	public String getCategory()
	{
		return category;
	}

	public void setCategory(final String category)
	{
		this.category = category;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;
	}

	@Override
	public int compareTo(final Subject o)
	{
		return name.compareToIgnoreCase(o.getName());
	}

}
