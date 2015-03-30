package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.Bet;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 21.03.2015.
 */
@Repository
public class BetDaoImpl extends AbstractDaoImpl<Long, Bet> implements  BetDao {
    protected BetDaoImpl() {
        super(Bet.class, Long.class);
    }
}
