package edu.gatech.edutech.smarterap.services;

import static edu.gatech.edutech.smarterap.utils.UserBuilderUtil.build;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountList;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.User;

@Service
@Transactional
public class UserService
{
	@Autowired
	private DatabaseDao			databaseDao;

	@Autowired
	private StormpathService	stormpathService;

	public User get(final String href)
	{
		return databaseDao.getByUniqueField(User.class, "href", href);
	}

	public User getUserFromEmail(final String email)
	{
		final Map<String, Object> query = Maps.newHashMap();
		query.put("email", email);
		final AccountList accounts = stormpathService.getClient().getAccounts(query);
		if (accounts != null && accounts.getSize() == 1)
		{
			final User user = build(accounts.single());
			final User dbUser = get(user.getHref());
			if (dbUser != null)
			{
				user.setUid(dbUser.getUid());
			}
			return user;
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
				final Account account = stormpathService.getClient().getResource(user.getHref(), Account.class);
				if (account != null)
				{
					course.getOwnerNames().add(account.getFullName());
				}
			}
		}
		return ownedCourses;
	}

	public Set<String> getNamesFromUsers(final Set<User> users)
	{
		final Set<String> names = Sets.newHashSet();
		for (final User user : users)
		{
			final Account account = stormpathService.getClient().getResource(user.getHref(), Account.class);
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
			final Account account = stormpathService.getClient().getResource(student.getHref(), Account.class);
			studentsWithDetails.add(build(account));
		}
		return studentsWithDetails;
	}

}
