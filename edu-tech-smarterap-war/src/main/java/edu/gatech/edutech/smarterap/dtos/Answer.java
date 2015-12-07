package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.ObjectUtils;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@AutoProperty
public class Answer extends BaseDto implements Comparable<Answer>
{
	@Column(name = "ordr")
	private Long		order;
	@Column
	private String		text;
	@Column
	private String		message;
	@Column
	private boolean		correct	= false;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "uid")
	@JsonIgnore
	private Question	question;

	public Long getOrder()
	{
		return order;
	}

	public void setOrder(final Long order)
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

	public boolean isCorrect()
	{
		return correct;
	}

	@JsonProperty
	public void setCorrect(final boolean correct)
	{
		this.correct = correct;
	}

	@JsonIgnore
	public Question getQuestion()
	{
		return question;
	}

	@JsonProperty
	public void setQuestion(final Question question)
	{
		this.question = question;
	}

	@Override
	public int compareTo(final Answer o)
	{
		return ObjectUtils.compare(order, o.getOrder(), true);
	}

}
