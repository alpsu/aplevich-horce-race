package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Bet;

import java.util.List;

/**
 * Created by admin on 21.03.2015.
 */
public interface BetDao extends AbstractDao<Long, Bet>{
    public List<Bet> getAllBetsByUser(Long id);
}