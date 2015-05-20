package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Bet_;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.datamodel.enums.BetType;
import by.aplevich.horcerace.services.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BetServiceImpl.class);

    @Inject
    private BetDao dao;

    @Override
    public Bet get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveOrUpdate(Bet bet) {
        if (bet.getId() == null) {
            LOGGER.debug("Save new: {}", bet);
            dao.insert(bet);
        } else {
            LOGGER.debug("Update: {}", bet);
            dao.update(bet);
        }
    }

    @Override
    public void delete(Bet bet) {
        LOGGER.debug("Delete bet: {}", bet);
        dao.delete(bet.getId());
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("Delete all bets");
        dao.deleteAll();
    }

    @Override
    public void deleteAllByUser(UserAccount user) {
        LOGGER.debug("Deleting all bets in userAccount: {}", user);
        List<Bet> bets = getAllBetsByUser(user);
        List<Long> ids = new ArrayList<>();
        for (Bet bet : bets) {
            ids.add(bet.getId());
        }
        dao.delete(ids);
    }

    @Override
    public void deleteAllByRunner(Runner runner) {
        LOGGER.debug("Deleting all bets by runner: {}", runner);
        List<Bet> bets = getAllBetsByRunner(runner);
        List<Long> ids = new ArrayList<>();
        for (Bet bet : bets) {
            ids.add(bet.getId());
        }
        dao.delete(ids);
    }

    @Override
    public List<Bet> getAllBetsByUser(UserAccount user) {
        LOGGER.debug("Get all bets in userAccounts: {}", user);
        return dao.getAllByFieldRestriction(Bet_.userAccount, user.getId());
    }

    @Override
    public List<Bet> getAllBetsByRunner(Runner runner) {
        LOGGER.debug("Get all bets by runner: {}", runner);
        return dao.getAllByFieldRestriction(Bet_.runner, runner.getId());
    }

    @Override
    public Long getCount(UserAccount user) {
        return dao.getCount(user);
    }

    @Override
    public List<Bet> getAllBetsByUser(Long userId, SingularAttribute<Bet, ?> attr, boolean ascending, int startRecord, int pageSize) {
        List<Bet> allBetsByUser = dao.getAllBetsByUser(userId, attr, ascending, startRecord, pageSize);
        //System.out.println(allBetsByUser);
        return allBetsByUser;
    }

    @Override
    public BigDecimal getWin(Long betId) {
        Bet bet = dao.getByIdWithRunner(betId);


        if (bet.getType() == BetType.SINGLE) {
            return getSingle(bet, BigDecimal.ZERO, bet.getSum());
        } else {
            BigDecimal single = getSingle(bet, BigDecimal.ZERO, bet.getSum().divide(BigDecimal.valueOf(2)));
            BigDecimal ew = getEW(bet, BigDecimal.ZERO, bet.getSum().divide(BigDecimal.valueOf(2)));
            return single.add(ew);
        }
    }

    private BigDecimal getSingle(Bet bet, BigDecimal result, BigDecimal sum) {
        if (bet.getRunner().getPlace() == 1) {
            return result.add(sum).multiply(BigDecimal.valueOf(bet.getRunner().getKoefficient()));
        }
        return result;
    }

    private BigDecimal getEW(Bet bet, BigDecimal result, BigDecimal sum) {
        int place = bet.getRunner().getPlace();
        double koef = bet.getRunner().getKoefficient();
        double koeff = (--koef) * 0.25 + 1;
        double koeff2 = (koef)* 0.2 + 1;
        Integer quantity = bet.getRunner().getRace().getQuantity();
        if (quantity < 5) {
            return result;
        } else if (quantity < 8) {
            if (place < 3) {
                return result.add(sum).multiply(BigDecimal.valueOf(koeff));
            } else {
                return result;
            }
        } else if (place < 4) {
            return result.add(sum).multiply(BigDecimal.valueOf(koeff2));
        }
        return result;
    }
}
