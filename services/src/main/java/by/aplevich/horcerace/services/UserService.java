package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.UserAccount;

import javax.transaction.Transactional;

public interface UserService {
    UserAccount get(Long id);

    @Transactional
    void createNewUser(UserAccount user);

    @Transactional
    void updateUser(UserAccount user);

    @Transactional
    void deteteUser(Long id);
}