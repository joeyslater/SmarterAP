package edu.gatech.edutech.smarterap.controllers;

import static edu.gatech.edutech.smarterap.utils.UserBuilderUtil.build;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Sets;
import com.stormpath.sdk.account.Account;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.Subject;
import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.StormpathService;
import edu.gatech.edutech.smarterap.services.UserService;

@Controller
@RequestMapping("")
public class TestController
{
	@Autowired
	private DatabaseDao			databaseDao;

	@Autowired
	private CrudService			crudService;

	@Autowired
	private StormpathService	stormpathService;

	@Autowired
	private UserService			userService;

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
	public List<Course> getCourseTest(final HttpSession session)
	{
		Subject subject = databaseDao.getByUniqueField(Subject.class, "name", "AP Computer Science A");
		if (subject == null)
		{
			subject = new Subject();
			subject.setCategory("Math & Computer Science");
			subject.setName("AP Computer Science A");
			crudService.create(subject);
		}

		final Course dto = new Course();
		dto.setName("AP Computer Science");
		dto.setSection("Fall 2015");
		dto.setSubject(subject);

		final User user = userService.get(((Account) session.getAttribute("sessionUser")).getHref());

		final Account account = stormpathService.getClient().getResource("https://api.stormpath.com/v1/accounts/PIxmeeDbTNL5SqBOAWs3c", Account.class);
		if (userService.get(account.getHref()) == null)
		{
			crudService.create(build(account));
		}
		final User user2 = userService.get(account.getHref());
		dto.setOwners(Sets.newHashSet(user, user2));
		dto.setStudents(Sets.newHashSet(user));

		final Course dto2 = new Course();
		dto2.setName("AP Computer Science 2");
		dto2.setSection("Fall 2015");
		dto2.setSubject(subject);
		dto2.setOwners(Sets.newHashSet(user));
		dto2.setStudents(Sets.newHashSet(user, user2));

		crudService.create(dto);
		crudService.create(dto2);
		return crudService.list(Course.class);
	}

}
