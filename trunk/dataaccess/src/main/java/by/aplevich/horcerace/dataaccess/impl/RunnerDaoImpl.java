package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Race_;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.Runner_;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
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
        criteria.distinct(true);

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        List<Runner> results = query.getResultList();
        return results;
    }

    @Override
    public List<Runner> getAllRunnersByRaceWith(Long placeId, Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner> criteria = cBuilder.createQuery(Runner.class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(root);
        root.fetch(Runner_.horce);
        root.fetch(Runner_.jockey);
        root.fetch(Runner_.race).fetch(Race_.place);
        criteria.where(cBuilder.equal(root.get(Runner_.race), raceId));
        criteria.orderBy(new OrderImpl(root.get(attr), ascending));


        criteria.distinct(true);

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        List<Runner> results = query.getResultList();
        return results;
    }

/*    @Override
    public List<Runner[]> getAllRunnersByRaceWith(Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner[]> criteria = cBuilder.createQuery(Runner[].class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(cBuilder.array(root.get("horce_id"), root.get("jockey_d"), root.get("koefficient"), root.get("place")));
        root.fetch(Runner_.horce);
        root.fetch(Runner_.jockey);
        //root.fetch(Runner_.race);
        criteria.where(cBuilder.equal(root.get(Runner_.race), raceId));
        criteria.orderBy(new OrderImpl(root.get(attr), ascending));

        criteria.distinct(true);

        TypedQuery<Runner[]> query = getEm().createQuery(criteria);
        List<Runner[]> results = query.getResultList();
        return results;
    }*/
/*
    SELECT c.name, c.capital.name FROM Country c
    can be defined using the criteria API as follows:
    CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
    Root<Country> c = q.from(Country.class);
    q.select(cb.array(c.get("name"), c.get("capital").get("name")));
    The array method builds a CompoundSelection instance, which represents results as arrays.
    The following code demonstrates execution of the query and iteration over the results:
    List<Object[]> results = em.createQuery(q).getResultList();
    for (Object[] result : results) {
        System.out.println("Country: " + result[0] + ", Capital: " + result[1]);
    }*/

    @Override
    public Long getCount(Long raceId) {
        CriteriaBuilder cBuilder = getEm().getCriteriaBuilder();

        CriteriaQuery<Runner> criteria = cBuilder.createQuery(Runner.class);
        Root<Runner> root = criteria.from(Runner.class);

        criteria.select(root);
        criteria.where(cBuilder.equal(root.get(Runner_.race), raceId));
        criteria.distinct(true);

        TypedQuery<Runner> query = getEm().createQuery(criteria);
        List<Runner> results = query.getResultList();
        return Long.valueOf(results.size());
    }
}

/*

SELECT in Criteria Queries
The criteria query API provides several ways for setting the SELECT clause.
        Single Selection
        Setting a single expression SELECT clause is straightforward.
        For example, the following JPQL query:
        SELECT DISTINCT c.currency FROM Country c
        can be built as a criteria query as follows:
        CriteriaQuery<Country> q = cb.createQuery(Country.class);
        Root<Country> c = q.from(Country.class);
        q.select(c.get("currency")).distinct(true);
        The select method takes one argument of type Selection and sets it as the SELECT clause content (overriding previously set SELECT content if any). Every valid criteria API expression can be used as selection, because all the criteria API expressions are represented by a sub interface of Selection - Expression (and its descendant interfaces).
        The distinct method can be used to eliminate duplicate results as demonstrated in the above code (using method chaining).
        Multi Selection
        The Selection interface is also a super interface of CompoundSelection, which represents multi selection (which is not a valid expression by its own and can be used only in the SELECT clause).
        The CriteriaBuilder interface provides three factory methods for building CompoundSelectioninstances - array, tuple and construct.
        CriteriaBuilder's array
        The following JPQL query:
        SELECT c.name, c.capital.name FROM Country c
        can be defined using the criteria API as follows:
        CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
        Root<Country> c = q.from(Country.class);
        q.select(cb.array(c.get("name"), c.get("capital").get("name")));
        The array method builds a CompoundSelection instance, which represents results as arrays.
        The following code demonstrates execution of the query and iteration over the results:
        List<Object[]> results = em.createQuery(q).getResultList();
        for (Object[] result : results) {
        System.out.println("Country: " + result[0] + ", Capital: " + result[1]);
        }
        CriteriaBuilder's tuple
        The Tuple interface can be used as a clean alternative to Object[]:
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<Country> c = q.from(Country.class);
        q.select(cb.tuple(c.get("name"), c.get("capital").get("name")));
        The tuple method builds a CompoundSelectionjavax.persistence.criteria.CompoundSelectionJPA interfaceThe CompoundSelection interface defines a compound selection item (tuple, array, or result of constructor).
        See JavaDoc Reference Page... instance, which represents Tuple results.
        The following code demonstrates execution of the query and iteration over the results:
        List<Tuple> results = em.createQuery(q).getResultList();
        for (Tuple t : results) {
        System.out.println("Country: " + t.get(0)  + ", Capital: " + t.get(1));
        }
        The Tuple interface defines several other methods for accessing the result data.*/
