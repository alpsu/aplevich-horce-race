package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.User;

import javax.transaction.Transactional;

public interface UserService {
    User get(Long id);

    /*@Transactional
    void createNewUser(User user);

    @Transactional
    void updateUser(User user);

    @Transactional
    void deteteUser(Long id);*/
}