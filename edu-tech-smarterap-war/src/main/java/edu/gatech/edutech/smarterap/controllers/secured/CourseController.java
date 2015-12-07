package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.UserService;

@RestController
@RequestMapping("/api/course")
public class CourseController implements CrudController<Course>
{
	@Autowired
	private CrudService	crudService;

	@Autowired
	private UserService	userService;

	@Override
	public Long count()
	{
		return crudService.count(Course.class);
	}

	@Override
	public List<Course> readAll()
	{
		return crudService.list(Course.class);
	}

	@Override
	public Course read(@PathVariable final Long uid)
	{
		final Course course = crudService.get(Course.class, uid);
		if (course.getStudents() != null)
		{
			course.setStudents(userService.addUserDetails(course.getStudents()));
		}
		course.setOwnerNames(userService.getNamesFromUsers(course.getOwners()));
		return course;
	}

	@Override
	public JsonResponse<Course> create(@RequestBody final Course dto, final HttpServletRequest request)
	{
		dto.getOwners().add(userService.getUserFromEmail(request.getRemoteUser()));
		return crudService.create(dto);
	}

	@Override
	public JsonResponse<Course> delete(@PathVariable final Long uid)
	{
		return crudService.delete(Course.class, uid);
	}

	@Override
	public JsonResponse<Course> update(@PathVariable final Long uid, @RequestBody final Course dto)
	{
		return crudService.update(uid, dto);
	}

	@RequestMapping("/owned")
	public List<Course> owned(final HttpServletRequest request)
	{
		return userService.getCoursesOwnedByUser(request.getUserPrincipal().getName());
	}

	//Classes you are registered for
	@RequestMapping("/registered")
	public List<Course> registered(final HttpServletRequest request)
	{
		return userService.getCoursesRegisteredByUser(request.getUserPrincipal().getName());
	}

	@RequestMapping("{uid}/students")
	@ResponseBody
	public List<User> readStudents(@PathVariable final Long uid, final HttpServletRequest request)
	{
		return userService.getStudentsForCourse(uid);
	}

	@RequestMapping("{uid}/student/add")
	@ResponseBody
	public void addStudentToCourse(@PathVariable final Long uid, @RequestBody final String email, final HttpServletRequest request)
	{
		userService.addStudentToCourse(uid, email);
	}
}
