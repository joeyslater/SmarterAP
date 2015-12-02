package edu.gatech.edutech.smarterap.daos;

import edu.gatech.edutech.smarterap.dtos.User;

/**
 * Created by Scott R. Leitstein on 11/30/15.
 */
public interface UserDao {

    User getUserByEmail(String email) throws Exception;

    User saveUser (User user) throws Exception;

    User updateUser (User user) throws Exception;
}
