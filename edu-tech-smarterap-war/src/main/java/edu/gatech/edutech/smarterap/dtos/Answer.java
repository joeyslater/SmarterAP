package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@AutoProperty
public class Answer extends BaseDto
{
	@Column
	private int			order;
	@Column
	private String		text;
	@Column
	private String		message;
	@Column
	@JsonIgnore
	private boolean		correct;
	@ManyToOne
	private Question	question;

	public int getOrder()
	{
		return order;
	}

	public void setOrder(final int order)
	{
		this.order = order;
	}

	public String getText()
	{
		return text;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	@JsonIgnore
	public boolean isCorrect()
	{
		return correct;
	}

	@JsonProperty
	public void setCorrect(final boolean correct)
	{
		this.correct = correct;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(final Question question)
	{
		this.question = question;
	}

}
