package edu.gatech.edutech.smarterap.controllers.auth;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - Updated for SmarterAP
 */
@Controller
@RequestMapping("/logout")
public class LogoutController
{
	private final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<Integer> logout(final HttpSession session, final HttpServletResponse response)
	{
		try
		{
			session.removeAttribute("sessionUser");
			session.invalidate();
			return new JsonResponse<Integer>(true, "Logout successful", 0);
		}
		catch (final Exception e)
		{
			LOG.error("Logout has issue. Session should still be invalidated.", e);
			return new JsonResponse<Integer>(false, "Logout potentially successful", 1);
		}
	}
}
