package edu.gatech.edutech.smarterap.controllers.secured;

import static com.google.common.collect.Sets.newHashSet;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Course;

@Controller
@RequestMapping("/api/course")
public class CourseController implements CrudController<Course> {

	private HashSet<Course> courses = newHashSet();
	
	{
		{
			Course course = new Course();
			course.setSubject("Computer Science");
			course.setPeriod("1");
			course.setYear(2015);
			course.setUid(0);
			courses.add(course);
		}
		{
			Course course = new Course();
			course.setSubject("Computer Science");
			course.setPeriod("2");
			course.setYear(2015);
			course.setUid(1);
			courses.add(course);
		}
		{
			Course course = new Course();
			course.setSubject("Computer Science");
			course.setPeriod("1");
			course.setYear(2016);
			course.setUid(2);
			courses.add(course);
		}
		
		{
			Course course = new Course();
			course.setSubject("Biology");
			course.setPeriod("1");
			course.setYear(2016);
			course.setUid(3);
			courses.add(course);
		}
	}
	
	
	@Override
	public Set<Course> readAll() {	
		return courses;
	}

	@Override
	public Course read(@PathVariable Long uid) {
		for (Course course : courses) {
			if(course.getUid() == uid){
				return course;
			}
		}
		return null;
	}

	@Override
	public Course create() {
		// TODO Auto-generated method stub
		return null;
	}
}
