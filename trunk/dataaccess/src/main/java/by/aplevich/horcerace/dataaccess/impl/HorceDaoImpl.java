package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.HorceDao;
import by.aplevich.horcerace.datamodel.Horce;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 21.03.2015.
 */
@Repository
public class HorceDaoImpl extends AbstractDaoImpl<Long, Horce> implements HorceDao {
    protected HorceDaoImpl() {
        super(Horce.class, Long.class);
    }
}
