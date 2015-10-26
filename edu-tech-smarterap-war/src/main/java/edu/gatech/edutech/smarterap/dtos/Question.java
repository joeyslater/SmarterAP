package edu.gatech.edutech.smarterap.dtos;

import java.util.Set;

import org.pojomatic.annotations.AutoProperty;

import edu.gatech.edutech.smarterap.enums.QuestionType;

@AutoProperty
public class Question extends BaseDto
{
	private String			text;
	private QuestionType	type;
	private Set<Answer>		answers;
	private String			hint;

	public String getText()
	{
		return text;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

	public QuestionType getType()
	{
		return type;
	}

	public void setType(final QuestionType type)
	{
		this.type = type;
	}

	public Set<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(final Set<Answer> answers)
	{
		this.answers = answers;
	}

	public String getHint()
	{
		return hint;
	}

	public void setHint(final String hint)
	{
		this.hint = hint;
	}

}
