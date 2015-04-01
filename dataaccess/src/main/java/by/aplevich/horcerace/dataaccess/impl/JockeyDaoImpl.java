package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.JockeyDao;
import by.aplevich.horcerace.datamodel.Jockey;
import org.springframework.stereotype.Repository;

@Repository
public class JockeyDaoImpl extends AbstractDaoImpl<Long, Jockey> implements JockeyDao {
    protected JockeyDaoImpl() {
        super(Jockey.class);
    }
}