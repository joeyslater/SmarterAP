package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Course extends BaseDto
{
	@Column
	private String	subject;

	@Column
	private String	period;

	@Column
	private int		year;

	public Course()
	{

	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(final String subject)
	{
		this.subject = subject;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(final String period)
	{
		this.period = period;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(final int year)
	{
		this.year = year;
	}

}
