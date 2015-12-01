package edu.gatech.edutech.smarterap.dtos;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Assessment extends BaseDto
{
	@Column
	private String			name;

	@Column
	private String			description;

	@ManyToOne(cascade = CascadeType.ALL)
	private Course			course;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "assessment_question", joinColumns = @JoinColumn(name = "assessment_id") , inverseJoinColumns = @JoinColumn(name = "question_id") )
	@Fetch(FetchMode.SELECT)
	private Set<Question>	questions;

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

}
