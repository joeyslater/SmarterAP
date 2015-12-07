package edu.gatech.edutech.smarterap.dtos;

import static com.google.common.collect.Sets.newHashSet;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ilike;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

@AutoProperty
public class QuestionQuery extends BaseDto
{
	@JsonIgnore
	private static final long	serialVersionUID	= 1L;

	private long				page;
	private int					start				= 0;
	private int					num					= 10;
	private String				queryText;
	private Subject				subject;
	private Set<Tag>			tags				= newHashSet();
	private Long				difficulty;

	public long getPage()
	{
		return page;
	}

	public void setPage(final long page)
	{
		this.page = page;
	}

	public String getQueryText()
	{
		return queryText;
	}

	public void setQueryText(final String queryText)
	{
		this.queryText = queryText;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(final Subject subject)
	{
		this.subject = subject;
	}

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(final Set<Tag> tags)
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
		if (StringUtils.isNotEmpty(queryText))
		{
			criterias.add(ilike("text", "%" + queryText + "%"));
		}
		if (difficulty != null)
		{
			criterias.add(eq("difficulty", difficulty));
		}
		if (subject != null)
		{
			criterias.add(eq("subject", subject));
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
