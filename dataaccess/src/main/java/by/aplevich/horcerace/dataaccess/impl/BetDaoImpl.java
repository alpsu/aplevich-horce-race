package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Bet_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BetDaoImpl extends AbstractDaoImpl<Long, Bet> implements BetDao {
    protected BetDaoImpl() {
        super(Bet.class);
    }

    // select bets where user id is
    @Override
    public List<Bet> getAllBetsByUser(Long id) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Bet> criteriaQuery = criteriaBuilder.createQuery(Bet.class);
        Root<Bet> root = criteriaQuery.from(Bet.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(Bet_.user), id));

        TypedQuery<Bet> query = getEm().createQuery(criteriaQuery);
        List<Bet> results = query.getResultList();
        return results;
    }
}