package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RaceDao;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Race_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
}