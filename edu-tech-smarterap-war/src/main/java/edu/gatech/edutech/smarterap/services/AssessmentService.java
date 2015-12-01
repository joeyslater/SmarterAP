package edu.gatech.edutech.smarterap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Assessment;
import edu.gatech.edutech.smarterap.dtos.Course;

@Service
public class AssessmentService
{
	@Autowired
	private DatabaseDao databaseDao;

	public List<Assessment> getAssessmentsByCourse(final Long courseId)
	{
		final Course course = databaseDao.get(Course.class, courseId);
		return databaseDao.getByUniqueFieldInCollection(Assessment.class, "assessmen", "ownersAlias", "username", courseId);
	}
}
