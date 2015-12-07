package edu.gatech.edutech.smarterap.services;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ilike;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Subject;
import edu.gatech.edutech.smarterap.dtos.Tag;

@Service
public class TagService
{
	@Autowired
	private DatabaseDao databaseDao;

	public List<Tag> query(final Long subjectId, final String q)
	{
		final ArrayList<Criterion> restrictions = Lists.newArrayList();
		restrictions.add(eq("subject", new Subject(subjectId)));
		restrictions.add(ilike("name", q, MatchMode.ANYWHERE));
		return databaseDao.query(Tag.class, restrictions, 0, 100);
	}
}
