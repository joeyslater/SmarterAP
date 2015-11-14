package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return crudService.list(Subject.class);
	}

	@Override
	public Subject read(final Long uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<Subject> create(final Subject dto)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<Subject> delete(final Long uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<Subject> update(final Long uid, final Subject dto)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
