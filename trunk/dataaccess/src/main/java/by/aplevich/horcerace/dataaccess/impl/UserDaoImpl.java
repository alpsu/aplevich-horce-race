package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.UserDao;
import by.aplevich.horcerace.datamodel.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Long, User> implements UserDao{
    protected UserDaoImpl(){
        super(User.class);
    }
}