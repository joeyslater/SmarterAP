package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Response extends BaseDto
{
	@ManyToOne(cascade = CascadeType.ALL)
	private User		user;

	@ManyToOne(cascade = CascadeType.ALL)
	private Question	question;

	@ManyToOne(cascade = CascadeType.ALL)
	private Answer		answer;

	@Column
	private Boolean		correct;

	public User getUser()
	{
		return user;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(final Question question)
	{
		this.question = question;
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public void setAnswer(final Answer answer)
	{
		this.answer = answer;
	}

	public Boolean getCorrect()
	{
		return correct;
	}

	public void setCorrect(final Boolean correct)
	{
		this.correct = correct;
	}

}
