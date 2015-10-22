package edu.gatech.edutech.smarterap.controllers.auth;

import org.springframework.http.MediaType;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stormpath.spring.security.provider.StormpathUserDetails;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.utils.UserBuilderUtil;

@Controller
@RequestMapping("/api/account")
public class UserController {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User config(@AuthenticationPrincipal StormpathUserDetails details) {
		User user = UserBuilderUtil.build(details);
		return user;
	}

}
