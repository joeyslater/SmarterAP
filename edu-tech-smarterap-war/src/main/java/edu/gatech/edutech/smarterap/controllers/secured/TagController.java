package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Tag;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;

@RestController
@RequestMapping("/api/tag")
public class TagController implements CrudController<Tag>
{
	@Autowired
	private CrudService crudService;

	@Override
	public List<Tag> readAll()
	{
		return crudService.list(Tag.class);
	}

	@Override
	public Tag read(@PathVariable final Long uid)
	{
		return crudService.get(Tag.class, uid);
	}

	@Override
	public JsonResponse<Tag> create(@RequestBody final Tag dto, final HttpServletRequest request)
	{
		return crudService.create(dto);
	}

	@Override
	public JsonResponse<Tag> delete(@PathVariable final Long uid)
	{
		return crudService.delete(Tag.class, uid);
	}

	@Override
	public JsonResponse<Tag> update(final Long uid, final Tag dto)
	{
		return crudService.update(uid, dto);
	}

	@Override
	public Long count()
	{
		return crudService.count(Tag.class);
	}
}