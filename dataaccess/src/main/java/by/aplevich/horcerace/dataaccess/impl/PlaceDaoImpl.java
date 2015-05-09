package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.datamodel.Place;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Long, Place> implements PlaceDao {
    protected PlaceDaoImpl() {
        super(Place.class);
    }

    @Override
    public List<Place> getAllPlaces() {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Place> criteria = cBuilder.createQuery(Place.class);
        Root<Place> root = criteria.from(Place.class);

        criteria.select(root);

        TypedQuery<Place> query = getEm().createQuery(criteria);
        List<Place> result = query.getResultList();
        return result;
    }

    @Override
    public List<Place> getAllPlaces(SingularAttribute<Place, ?> attr, boolean ascending, int startRecord, int pageSize) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Place> criteria = cBuilder.createQuery(Place.class);
        Root<Place> root = criteria.from(Place.class);

        criteria.select(root);
        criteria.orderBy(new OrderImpl(root.get(attr), ascending));
        TypedQuery<Place> query = getEm().createQuery(criteria);
        query.setFirstResult(startRecord);
        query.setMaxResults(pageSize);

        List<Place> results = query.getResultList();
        return results;
    }
}