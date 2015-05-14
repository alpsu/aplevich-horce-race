package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.UserDao;
import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.datamodel.enums.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Long, UserAccount> implements UserDao {
    protected UserDaoImpl() {
        super(UserAccount.class);
    }

    @Override
    public List<UserRole> getUserRole(Long userId) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<UserAccount> criteria = cBuilder.createQuery(UserAccount.class);
        Root<UserAccount> root = criteria.from(UserAccount.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(UserAccount_.id), userId));

        TypedQuery<UserAccount> query = getEm().createQuery(criteria);
        List<UserAccount> results = query.getResultList();
        List<UserRole> roles = new ArrayList<>();
        for (UserAccount tmp : results) {
            roles.add(tmp.getRole());
        }
        return roles;
    }
}