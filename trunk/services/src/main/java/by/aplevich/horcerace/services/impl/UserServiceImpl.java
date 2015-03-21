package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.UserDao;
import by.aplevich.horcerace.services.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 21.03.2015.
 */
@Service
public class UserServiceImpl implements UserService{
    @Inject
    private UserDao userDao;
}
