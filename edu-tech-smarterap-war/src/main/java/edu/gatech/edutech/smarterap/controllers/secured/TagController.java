package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Tag;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.TagService;

@RestController
@RequestMapping("/api/tag")
public class TagController implements CrudController<Tag>
{
	@Autowired
	private CrudService	crudService;

	@Autowired
	private TagService	tagService;

	@Override
	public List<Tag> readAll()
	{
		return crudService.list(Tag.class);
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Tag> query(@RequestParam final Long subjectId, @RequestParam final String q)
	{
		return tagService.query(subjectId, q);
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