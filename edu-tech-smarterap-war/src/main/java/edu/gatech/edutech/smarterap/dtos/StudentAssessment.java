package edu.gatech.edutech.smarterap.dtos;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.ObjectUtils;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity(name = "assessment")
@AutoProperty
@JsonInclude(Include.NON_NULL)
public class StudentAssessment extends BaseDto implements Comparable<StudentAssessment>
{
	@Column
	private String	name;

	@Column
	private String	description;

	@Column(name = "open_date")
	private Date	openDate;

	@Column(name = "close_date")
	private Date	closeDate;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Course	course;

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

	public Date getOpenDate()
	{
		return openDate;
	}

	public void setOpenDate(final Date openDate)
	{
		this.openDate = openDate;
	}

	public Date getCloseDate()
	{
		return closeDate;
	}

	public void setCloseDate(final Date closeDate)
	{
		this.closeDate = closeDate;
	}

	public Course getCourse()
	{
		return course;
	}

	public void setCourse(final Course course)
	{
		this.course = course;
	}

	@Override
	public int compareTo(final StudentAssessment o)
	{
		final int a = ObjectUtils.compare(openDate, o.getOpenDate(), true);
		if (a == 0)
		{
			return name.compareToIgnoreCase(o.getName());
		}
		return a;
	}

}
