package edu.gatech.edutech.smarterap.dtos;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.pojomatic.annotations.AutoProperty;

import com.google.common.collect.Sets;

import edu.gatech.edutech.smarterap.enums.QuestionType;

@Entity
@AutoProperty
public class Question extends BaseDto
{
	@Column
	private String			text;
	@Enumerated(EnumType.STRING)
	private QuestionType	type		= QuestionType.MULTIPLE_CHOICE;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
	@Fetch(FetchMode.SELECT)
	private List<Answer>	answers;

	@Column
	private String			hint;

	@Column
	private Long			difficulty	= 3L;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "question_tag", joinColumns = @JoinColumn(name = "question_id") , inverseJoinColumns = @JoinColumn(name = "tag_id") )
	@Fetch(FetchMode.SELECT)
	private Set<Tag>		tags		= Sets.newHashSet();

	@ManyToOne
	private Subject			subject;

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

	public List<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(final List<Answer> answers)
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

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(final Set<Tag> tags)
	{
		this.tags = tags;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(final Subject subject)
	{
		this.subject = subject;
	}

	public Long getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(final Long difficulty)
	{
		this.difficulty = difficulty;
	}

}
