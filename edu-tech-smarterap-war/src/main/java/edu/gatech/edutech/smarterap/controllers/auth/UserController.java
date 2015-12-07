package edu.gatech.edutech.smarterap.controllers.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stormpath.sdk.account.Account;

import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.utils.UserBuilderUtil;

@RestController
@RequestMapping("/account")
public class UserController
{
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User config(final HttpServletRequest request, final HttpSession session, final HttpServletResponse response)
	{
		User user = null;
		try
		{
			final Account account = (Account) session.getAttribute("sessionUser");
			if (account == null)
			{
				response.setStatus(401);
			}
			else
			{
				user = UserBuilderUtil.build(account);
			}
			return user;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
