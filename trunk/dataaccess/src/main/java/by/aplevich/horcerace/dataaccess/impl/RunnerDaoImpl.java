package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Runner;
import org.springframework.stereotype.Repository;

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
}