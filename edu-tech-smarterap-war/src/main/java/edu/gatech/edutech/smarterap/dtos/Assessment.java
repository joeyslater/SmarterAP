package edu.gatech.edutech.smarterap.dtos;

import static com.google.common.collect.Sets.newHashSet;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@AutoProperty
public class Assessment extends BaseDto implements Comparable<Assessment>
{
	@Column
	private String			name;

	@Column
	private String			description;

	@Column(name = "open_date")
	private Date			openDate;

	@Column(name = "close_date")
	private Date			closeDate;

	@ManyToOne(cascade = CascadeType.ALL)
	private Course			course;

	@JsonIgnore
	@Transient
	private Long			courseId;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "assessment_question", joinColumns = @JoinColumn(name = "assessment_id") , inverseJoinColumns = @JoinColumn(name = "question_id") )
	@Fetch(FetchMode.SELECT)
	private Set<Question>	questions	= newHashSet();

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

	public Course getCourse()
	{
		return course;
	}

	public void setCourse(final Course course)
	{
		this.course = course;
	}

	public Set<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(final Set<Question> questions)
	{
		this.questions = questions;
	}

	@JsonIgnore
	public Long getCourseId()
	{
		return courseId;
	}

	@JsonProperty
	public void setCourseId(final Long courseId)
	{
		this.courseId = courseId;
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

	@Override
	public int compareTo(final Assessment o)
	{
		final int a = ObjectUtils.compare(openDate, o.getOpenDate(), true);
		if (a == 0)
		{
			return name.compareToIgnoreCase(o.getName());
		}
		return a;
	}

}
