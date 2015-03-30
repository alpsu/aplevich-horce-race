package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.JockeyDao;
import by.aplevich.horcerace.datamodel.Jockey;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 21.03.2015.
 */
@Repository
public class JockeyDaoImpl extends AbstractDaoImpl<Long, Jockey> implements JockeyDao {
    protected JockeyDaoImpl() {
        super(Jockey.class, Long.class);
    }
}
