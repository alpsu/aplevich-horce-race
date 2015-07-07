package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Race_;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.Runner_;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Arrays;
import java.util.List;

@Repository
public class RunnerDaoImpl extends AbstractDaoImpl<Long, Runner> implements RunnerDao {
    protected RunnerDaoImpl() {
        super(Runner.class);
    }

    @Override
    public List<Runner> getAllRunnersByRaceWith(Race race) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner> criteria = cBuilder.createQuery(Runner.class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(Runner_.race), race.getId()));
        root.fetch(Runner_.race);
        root.fetch(Runner_.horce);
        root.fetch(Runner_.jockey);

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        List<Runner> results = query.getResultList();
        return results;
    }

    @Override
    public Runner getWithAllByRunner(Long runnerId) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner> criteria = cBuilder.createQuery(Runner.class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(Runner_.id), runnerId));
        root.fetch(Runner_.horce);
        root.fetch(Runner_.jockey);

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        Runner result = query.getSingleResult();
        return result;
    }

    @Override
    public List<Runner> getAllRunnersByRaceWith(Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending, int startRecord, int pageSize) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner> criteria = cBuilder.createQuery(Runner.class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(root);
        root.fetch(Runner_.horce);
        root.fetch(Runner_.jockey);
        criteria.where(cBuilder.equal(root.get(Runner_.race), raceId));
        criteria.orderBy(new OrderImpl(root.get(attr), ascending));

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        query.setFirstResult(startRecord);
        query.setMaxResults(pageSize);

        List<Runner> results = query.getResultList();
        return results;
    }

    @Override
    public Long getCount(Long raceId) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner> criteria = cBuilder.createQuery(Runner.class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(Runner_.race), raceId));

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        List<Runner> results = query.getResultList();
        return Long.valueOf(results.size());
    }
}