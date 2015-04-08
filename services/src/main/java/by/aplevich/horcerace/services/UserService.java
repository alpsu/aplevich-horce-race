package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.UserAccount;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    UserAccount get(Long id);

    @Transactional
    void createNewUser(UserAccount user);

    @Transactional
    void updateUser(UserAccount user);

    @Transactional
    void deteteUser(Long id);

    @Transactional
    void deleteAll();

    List<Bet> getAllBets(UserAccount user);
}