package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RaceDao;
import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Race_;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Repository
public class RaceDaoImpl extends AbstractDaoImpl<Long, Race> implements RaceDao {
    protected RaceDaoImpl() {
        super(Race.class);
    }

    @Override
    public List<Race> getAllRacesWithPlace() {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Race> criteria = cBuilder.createQuery(Race.class);
        Root<Race> root = criteria.from(Race.class);

        criteria.select(root);
        root.fetch(Race_.place);
        criteria.distinct(true);

        TypedQuery<Race> query = getEm().createQuery(criteria);
        List<Race> results = query.getResultList();
        return results;
    }

    @Override
    public List<Race> getAllRacesWithPlaceByPlace(Place place) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Race> criteria = cBuilder.createQuery(Race.class);
        Root<Race> root = criteria.from(Race.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(Race_.place), place.getId()));
        root.fetch(Race_.place);
        criteria.distinct(true);

        TypedQuery<Race> query = getEm().createQuery(criteria);
        List<Race> results = query.getResultList();
        return results;
    }

    @Override
    public List<Race> getAllRacesWithPlaceByPlace(Long placeId, SingularAttribute<Race, ?> attr, boolean ascending, int first, int pageSize) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Race> criteria = cBuilder.createQuery(Race.class);
        Root<Race> root = criteria.from(Race.class);

        criteria.select(root);
        root.fetch(Race_.place);
        criteria.where(cBuilder.equal(root.get(Race_.place), placeId));
        criteria.orderBy(new OrderImpl(root.get(attr), ascending));

        criteria.distinct(true);

        TypedQuery<Race> query = getEm().createQuery(criteria);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        List<Race> results = query.getResultList();
        return results;
    }

    @Override
    public Long getCount(Long placeId) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Race> criteria = cBuilder.createQuery(Race.class);
        Root<Race> root = criteria.from(Race.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(Race_.place), placeId));
        criteria.distinct(true);

        TypedQuery<Race> query = getEm().createQuery(criteria);
        List<Race> results = query.getResultList();
        return Long.valueOf(results.size());
    }
}