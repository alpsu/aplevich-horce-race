package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Runner;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 21.03.2015.
 */
@Repository
public class RunnerDaoImpl extends AbstractDaoImpl<Long, Runner> implements RunnerDao {
    protected RunnerDaoImpl() {
        super(Runner.class, Long.class);
    }
}
