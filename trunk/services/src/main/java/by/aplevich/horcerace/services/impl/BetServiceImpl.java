package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Bet_;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.services.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
}
