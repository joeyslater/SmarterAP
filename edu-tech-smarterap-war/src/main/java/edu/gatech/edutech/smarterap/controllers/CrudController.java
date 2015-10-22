package edu.gatech.edutech.smarterap.controllers;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO Make this a concrete class with Hibernate DAO, for now... leave it as is

public interface CrudController<T> {

	@RequestMapping(value="/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<T> readAll();
	
	@RequestMapping(value="/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public T read(@PathVariable Long uid);
	
	@RequestMapping(value="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public T create();
	
}
