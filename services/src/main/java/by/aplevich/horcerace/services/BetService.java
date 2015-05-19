package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.UserAccount;

import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface BetService {
    Bet get(Long id);

    @Transactional
    void saveOrUpdate(Bet bet);

    @Transactional
    void delete(Bet bet);

    @Transactional
    void deleteAll();

    @Transactional
    void deleteAllByUser(UserAccount user);

    @Transactional
    void deleteAllByRunner(Runner runner);

    List<Bet> getAllBetsByUser(UserAccount user);

    List<Bet> getAllBetsByRunner(Runner runner);

    Long getCount(UserAccount user);

    List<Bet> getAllBetsByUser(Long userId, SingularAttribute<Bet, ?> attr, boolean ascending, int startRecord, int pageSize);

    BigDecimal getWin(Bet bet);
}