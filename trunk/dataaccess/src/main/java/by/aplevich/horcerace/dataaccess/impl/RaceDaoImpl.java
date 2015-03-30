package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.RaceDao;
import by.aplevich.horcerace.datamodel.Race;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 21.03.2015.
 */
@Repository
public class RaceDaoImpl extends AbstractDaoImpl<Long, Race> implements RaceDao {
    protected RaceDaoImpl() {
        super(Race.class, Long.class);
    }
}
