package edu.gatech.edutech.smarterap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.services.CrudService;

@Controller
@RequestMapping("")
public class TestController
{
	@Autowired
	private DatabaseDao	databaseDao;

	@Autowired
	private CrudService	crudService;

	/**
	 * This is an example of a GET, if you return a String you must add quotes
	 * around it
	 *
	 * @return Things to Print
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String helloWorld()
	{
		return "\"Hello World 2\"";
	}

	/**
	 * Test Creating a DTO
	 * @return
	 */
	@RequestMapping(value = "/dto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Course> getCourseTest()
	{
		final Course dto = new Course();
		dto.setSubject("AP Computer Science");

		crudService.create(dto);
		return crudService.list(Course.class);
	}

}
