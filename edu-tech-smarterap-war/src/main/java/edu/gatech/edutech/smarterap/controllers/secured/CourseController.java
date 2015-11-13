package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;

@RestController
@RequestMapping("/api/course")
public class CourseController implements CrudController<Course>
{
	@Autowired
	private CrudService crudService;

	@Override
	public List<Course> readAll()
	{
		return crudService.list(Course.class);
	}

	@Override
	public Course read(@PathVariable final Long uid)
	{
		return crudService.get(Course.class, uid);
	}

	@Override
	public JsonResponse<Course> create(@RequestBody final Course dto)
	{
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

}
