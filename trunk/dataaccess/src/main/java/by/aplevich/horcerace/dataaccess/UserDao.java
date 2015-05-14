package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.datamodel.enums.UserRole;

import java.util.List;

public interface UserDao extends AbstractDao<Long, UserAccount> {
    List<UserRole> getUserRole(Long userId);
}