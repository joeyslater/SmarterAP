package edu.gatech.edutech.smarterap.dtos;

import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class Answer extends BaseDto
{
	private int		order;
	private String	text;
	private String	message;
	private boolean	correct;

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

	public boolean isCorrect()
	{
		return correct;
	}

	public void setCorrect(final boolean correct)
	{
		this.correct = correct;
	}

}
