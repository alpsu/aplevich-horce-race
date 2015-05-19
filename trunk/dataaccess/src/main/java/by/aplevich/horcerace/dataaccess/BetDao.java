package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.UserAccount;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface BetDao extends AbstractDao<Long, Bet>{
    Long getCount(UserAccount user);

    List<Bet> getAllBetsByUser(Long userId, SingularAttribute<Bet, ?> attr, boolean ascending, int startRecord, int pageSize);
}