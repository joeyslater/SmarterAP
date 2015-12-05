package edu.gatech.edutech.smarterap.dtos;

import static com.google.common.collect.Sets.newHashSet;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

@AutoProperty
public class QuestionQuery implements Serializable
{
	@JsonIgnore
	private static final long	serialVersionUID	= 1L;

	private long				page;
	private int					start				= 0;
	private int					num					= 10;
	private String				text;
	private String				subjectName;
	private Set<String>			tags				= newHashSet();
	private Long				difficulty;

	public long getPage()
	{
		return page;
	}

	public void setPage(final long page)
	{
		this.page = page;
	}

	public String getText()
	{
		return text;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

	public String getSubjectName()
	{
		return subjectName;
	}

	public void setSubjectName(final String subjectName)
	{
		this.subjectName = subjectName;
	}

	public Set<String> getTags()
	{
		return tags;
	}

	public void setTags(final Set<String> tags)
	{
		this.tags = tags;
	}

	public Long getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(final Long difficulty)
	{
		this.difficulty = difficulty;
	}

	public List<Criterion> toCriterionList()
	{
		final List<Criterion> criterias = Lists.newArrayList();
		if (difficulty != null)
		{
			criterias.add(Restrictions.eq("difficulty", difficulty));
		}
		return criterias;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(final int start)
	{
		this.start = start;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(final int num)
	{
		this.num = num;
	}

}
