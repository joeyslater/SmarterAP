package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController
{
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> readAll()
	{
		return adminService.retrieveAllUsers();
	}

}
