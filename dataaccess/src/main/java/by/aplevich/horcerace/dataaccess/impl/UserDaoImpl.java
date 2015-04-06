package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.UserDao;
import by.aplevich.horcerace.datamodel.UserAccount;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Long, UserAccount> implements UserDao{
    protected UserDaoImpl(){
        super(UserAccount.class);
    }
}