package edu.gatech.edutech.smarterap.daos;

/*
 * Copyright 2012 Stormpath, Inc. and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import edu.gatech.edutech.smarterap.dtos.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elder Crisostomo
 * @author Scott R. Leitstein for SmarterAP
 */

@Repository
public class DefaultUserDao extends BaseHibernateDao implements UserDao {

    @Override
    public User getUserByEmail(String email) throws Exception {

        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", email));
        List<?> list = new ArrayList<Object>();
        list.addAll(criteria.list());

        User user = null;

        if (!list.isEmpty()) {
            for (Object obj : list) {
                if (obj != null) {
                    user = (User) obj;
                    break;
                }
            }
        }
        return user;
    }

    @Override
    public User saveUser (User user) throws Exception {

        save(user);
        return user;
    }

    @Override
    public User updateUser(User user) throws Exception {

        update(user);

        return user;
    }

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}

