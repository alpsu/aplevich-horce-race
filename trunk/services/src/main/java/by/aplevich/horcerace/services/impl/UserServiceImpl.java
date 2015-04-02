package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.UserDao;
import by.aplevich.horcerace.datamodel.User;
import by.aplevich.horcerace.services.UserService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserDao dao;

    @Override
    public User get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void createNewUser(User userOne) {
        Validate.isTrue(userOne.getId() == null,
                "This method should be called for 'not saved yet' user only. Use UPDATE instead");
        dao.insert(userOne);
    }

    @Override
    public void updateUser(User userOne) {
        dao.update(userOne);
    }

    @Override
    public void deteteUser(Long id) {
        dao.delete(id);
    }
}