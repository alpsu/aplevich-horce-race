package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.Bet;
import org.springframework.stereotype.Repository;

@Repository
public class BetDaoImpl extends AbstractDaoImpl<Long, Bet> implements  BetDao {
    protected BetDaoImpl() {
        super(Bet.class);
    }
}