package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.services.AdminService;
import edu.gatech.edutech.smarterap.services.StormpathService;

@RestController
@RequestMapping("/api/admin")
public class AdminController
{
	@Autowired
	private AdminService		adminService;

	@Autowired
	private StormpathService	stormpathService;

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> readAll()
	{
		return adminService.retrieveAllUsers();
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@RequestBody final User user)
	{
		adminService.updateUser(user);
	}

	@RequestMapping(value = "/user/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void resetPassword(@RequestBody final User user)
	{
		stormpathService.sendPasswordResetEmail(user.getUsername());
	}

	@RequestMapping(value = "/user/verification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void resendVerification(@RequestBody final User user)
	{
		stormpathService.sendVerificationEmail(user.getUsername());
	}

}
