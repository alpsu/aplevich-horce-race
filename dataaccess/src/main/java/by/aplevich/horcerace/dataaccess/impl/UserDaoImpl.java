package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.UserDao;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.UserAccount;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Long, UserAccount> implements UserDao{
    protected UserDaoImpl(){
        super(UserAccount.class);
    }
}