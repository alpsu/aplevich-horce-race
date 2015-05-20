package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.*;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Repository
public class BetDaoImpl extends AbstractDaoImpl<Long, Bet> implements BetDao {
    protected BetDaoImpl() {
        super(Bet.class);
    }

    @Override
    public Long getCount(UserAccount user) {
        return Long.valueOf(getAllByFieldRestriction(Bet_.userAccount, user).size());
    }

    @Override
    public List<Bet> getAllBetsByUser(Long userId, SingularAttribute<Bet, ?> attr, boolean ascending, int startRecord, int pageSize) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Bet> criteria = cBuilder.createQuery(Bet.class);
        Root<Bet> root = criteria.from(Bet.class);

        criteria.select(root);
        root.fetch(Bet_.userAccount);
        //root.fetch(Bet_.runner);
        criteria.where(cBuilder.equal(root.get(Bet_.userAccount), userId));
        criteria.orderBy(new OrderImpl(root.get(attr), ascending));
        criteria.distinct(true);

        TypedQuery<Bet> query = getEm().createQuery(criteria);
        query.setFirstResult(startRecord);
        query.setMaxResults(pageSize);
        List<Bet> results = query.getResultList();
        return results;
    }

    @Override
    public Bet getByIdWithRunner(Long betId) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<Bet> criteria = cBuilder.createQuery(Bet.class);
        Root<Bet> root = criteria.from(Bet.class);

        criteria.select(root);
        root.fetch(Bet_.runner).fetch(Runner_.race);
        criteria.where(cBuilder.equal(root.get(Bet_.id), betId));

        TypedQuery<Bet> query = getEm().createQuery(criteria);
        return query.getSingleResult();
    }
}