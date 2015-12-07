package edu.gatech.edutech.smarterap.services;

import static edu.gatech.edutech.smarterap.utils.UserBuilderUtil.build;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.stormpath.sdk.account.Account;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.daos.StormpathDao;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.utils.UserBuilderUtil;

@Service
@Transactional
public class UserService
{
	@Autowired
	private DatabaseDao		databaseDao;

	@Autowired
	private StormpathDao	stormpathDao;

	public User get(final String href)
	{
		return databaseDao.getByUniqueField(User.class, "href", href);
	}

	public Long getUid(final String email)
	{
		final User user = databaseDao.getByUniqueField(User.class, "username", email);
		if (user != null)
		{
			return user.getUid();
		}
		else
		{
			return -1L;
		}
	}

	public User getUserFromEmail(final String email)
	{

		final Account account = stormpathDao.getAccount("email", email);
		if (account != null)
		{
			final User user = build(account);
			user.setUid(getUid(email));
			return user;
		}
		return null;
	}

	public User getUserAccount(final User user)
	{
		final Account account = stormpathDao.getAccount("email", user.getUsername());
		if (account != null)
		{
			final User userAccount = build(account);
			userAccount.setUid(user.getUid());
			return userAccount;
		}
		return null;
	}

	public List<Course> getCoursesOwnedByUser(final String username)
	{
		final List<Course> ownedCourses = databaseDao.getByUniqueFieldInCollection(Course.class, "owners", "ownersAlias", "username", username);
		for (final Course course : ownedCourses)
		{
			for (final User user : course.getOwners())
			{
				final Account account = stormpathDao.getAccount("email", user.getUsername());
				if (account != null)
				{
					course.getOwnerNames().add(account.getFullName());
				}
			}
		}
		return ownedCourses;
	}

	public List<Course> getCoursesRegisteredByUser(final String username)
	{
		final Map<String, Object> criterias = Maps.newHashMap();
		criterias.put("enabled", true);
		criterias.put("visible", true);
		final List<Course> registered = databaseDao.getByUniqueFieldInCollectionWithOtherCriteria(Course.class, "students", "studentsAlias", "username", username, criterias);
		for (final Course course : registered)
		{
			for (final User user : course.getOwners())
			{
				final Account account = stormpathDao.getAccount("email", user.getUsername());
				if (account != null)
				{
					course.getOwnerNames().add(account.getFullName());
				}
			}
		}
		return registered;
	}

	public Set<String> getNamesFromUsers(final Set<User> users)
	{
		final Set<String> names = Sets.newHashSet();
		for (final User user : users)
		{
			final Account account = stormpathDao.getAccount("email", user.getUsername());
			if (account != null)
			{
				names.add(account.getFullName());
			}
		}
		return names;
	}

	public Set<User> addUserDetails(final Set<User> students)
	{
		final Set<User> studentsWithDetails = Sets.newHashSet();
		for (final User student : students)
		{
			final User user = build(stormpathDao.getAccount(student.getHref()));
			if (user.getUid() == -1)
			{
				user.setStatus("disabled");
			}
			studentsWithDetails.add(student);
		}
		return studentsWithDetails;
	}

	public List<User> getStudentsForCourse(final Long uid)
	{
		final List<User> students = Lists.newArrayList();
		final Course course = databaseDao.get(Course.class, uid);
		for (final User user : course.getStudents())
		{
			students.add(UserBuilderUtil.build(stormpathDao.getAccount("email", user.getUsername()), user.getUid()));
		}
		return students;
	}

	public void addStudentToCourse(final Long uid, final String email)
	{
		final Course course = databaseDao.get(Course.class, uid);
		final String[] emails = email.split(",");
		for (String e : emails)
		{
			e = e.trim();
			final User u = databaseDao.getByUniqueField(User.class, "username", e);
			if (u == null)
			{
				User user = getUserFromEmail(e);
				if (user == null)
				{
					user = new User();
					user.setUsername(email);
					databaseDao.saveOrUpdate(user);
				}
				course.getStudents().add(user);
			}
			else
			{
				course.getStudents().add(u);
			}
		}
		databaseDao.saveOrUpdate(course);
	}
}
