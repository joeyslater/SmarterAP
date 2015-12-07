package edu.gatech.edutech.smarterap.services;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Assessment;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.StudentAssessment;

@Service
public class AssessmentService
{
	@Autowired
	private DatabaseDao databaseDao;

	public List<Assessment> readAllAssessmentsForCourse(final Long courseId, final int start, final int num)
	{
		final List<Assessment> assessments = databaseDao.query(Assessment.class, Lists.<Criterion> newArrayList(eq("course", new Course(courseId))), start, num);
		Collections.sort(assessments);
		return assessments;
	}

	public List<StudentAssessment> readAllStudentAssessmentsForCourse(final Long courseId, final int start, final int num)
	{
		final List<StudentAssessment> assessments = databaseDao.query(StudentAssessment.class, Lists.<Criterion> newArrayList(eq("course", new Course(courseId))), start, num);
		Collections.sort(assessments);
		return assessments;
	}

	public StudentAssessment readStudentAssessment(final Long uid)
	{
		return databaseDao.get(StudentAssessment.class, uid);
	}
}
