package edu.gatech.edutech.smarterap.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountStatus;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.User;
import edu.gatech.edutech.smarterap.enums.SecurityRole;
import edu.gatech.edutech.smarterap.utils.UserBuilderUtil;

@Service
public class AdminService
{
	@Autowired
	private UserService			userService;

	@Autowired
	private DatabaseDao			databaseDao;

	@Autowired
	private StormpathService	stormpathService;

	//TODO Will not grab users from StormPath not in Database. Will run other script that will take care of that daily.
	//	@PreAuthorize("hasRole('" + SecurityRole.ADMIN.toString() + "')")
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

	public void updateUser(final User user)
	{
		final AccountStatus status = "ENABLED".equalsIgnoreCase(user.getStatus()) ? AccountStatus.ENABLED : AccountStatus.DISABLED;
		final Account acct = stormpathService.get(user.getUsername()).setStatus(status);
		final User acctUser = UserBuilderUtil.build(acct);
		if (!Objects.equals(acctUser.getSecurityRoles(), user.getSecurityRoles()))
		{
			for (final SecurityRole security : SecurityRole.values())
			{
				if (user.getSecurityRoles().contains(security) && !acctUser.getSecurityRoles().contains(security))
				{
					acct.addGroup(security.toString());
				}
				else if (!user.getSecurityRoles().contains(security) && acctUser.getSecurityRoles().contains(security))
				{
					acct.removeGroup(security.toString());
				}
			}
		}
		acct.save();

	}

}
