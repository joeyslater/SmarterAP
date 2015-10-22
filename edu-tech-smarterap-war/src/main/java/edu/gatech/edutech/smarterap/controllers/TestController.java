package edu.gatech.edutech.smarterap.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	/**
	 * This is an example of a GET, if you return a String you must add quotes around it
	 * @return Things to Print
	 */
	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String helloWorld(){
		return "\"Hello World 2\"";
	}
	
}
