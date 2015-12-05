package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Subject;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController implements CrudController<Subject>
{
	@Autowired
	private CrudService crudService;

	@Override
	public List<Subject> readAll()
	{
		final List<Subject> subjects = crudService.list(Subject.class);
		Collections.sort(subjects);
		return subjects;
	}

	@Override
	public Subject read(@PathVariable final Long uid)
	{
		return crudService.get(Subject.class, uid);
	}

	@Override
	public JsonResponse<Subject> create(@RequestBody final Subject dto, final HttpServletRequest request)
	{
		return crudService.create(dto);
	}

	@Override
	public JsonResponse<Subject> delete(@PathVariable final Long uid)
	{
		return crudService.delete(Subject.class, uid);
	}

	@Override
	public JsonResponse<Subject> update(final Long uid, final Subject dto)
	{
		return crudService.update(uid, dto);
	}

	@Override
	public Long count()
	{
		return crudService.count(Subject.class);
	}

}
