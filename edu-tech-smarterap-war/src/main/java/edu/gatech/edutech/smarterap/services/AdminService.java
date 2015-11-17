package edu.gatech.edutech.smarterap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.User;

@Service
public class AdminService
{
	@Autowired
	private UserService	userService;

	@Autowired
	private DatabaseDao	databaseDao;

	//TODO Will not grab users from StormPath not in Database. Will run other script that will take care of that daily.
	public List<User> retrieveAllUsers()
	{
		final List<User> usersToReturn = Lists.newArrayList();
		final List<User> users = databaseDao.list(User.class);
		for (final User user : users)
		{
			final User accountUser = userService.getUserAccount(user);
			if (accountUser != null && accountUser.getUid() != -1)
			{
				usersToReturn.add(accountUser);
			}
			else
			{
				user.setStatus("Account Not Created");
				usersToReturn.add(user);
			}
		}
		return usersToReturn;
	}

}
