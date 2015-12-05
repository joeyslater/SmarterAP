package edu.gatech.edutech.smarterap.services;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Question;
import edu.gatech.edutech.smarterap.dtos.QuestionQuery;

@Service
public class QuestionService
{
	@Autowired
	private DatabaseDao databaseDao;

	public Pair<List<Question>, Long> query(final QuestionQuery questionQuery)
	{
		final Long count = databaseDao.count(Question.class, questionQuery.toCriterionList());
		final List<Question> list = databaseDao.query(Question.class, questionQuery.toCriterionList(), questionQuery.getStart(), questionQuery.getNum());
		return Pair.of(list, count);
	}

}
