package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.UserAccount;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BetServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BetServiceTest.class);

    @Test
    public void basicCRUDTest() {
        LOGGER.debug("Starting basicCRUDTest for Bet");
        Bet bet = createBet();

        Bet betFromDb = betService.get(bet.getId());
        Assert.assertNotNull(betFromDb);
    }

    @Test
    public void getAllBetsByUser() {
        LOGGER.debug("Starting getAllBetsByUser");
        int num = randomInteger(2, 5);
        UserAccount user = createUser();
        for (int i = 0; i < num; i++) {
            Bet bet = createBet();
            bet.setUserAccount(user);
            betService.saveOrUpdate(bet);
        }

        List<Bet> bets = betService.getAllBetsByUser(user);
        Assert.assertEquals(bets.size(), num);
    }

    @Test
    public void getAllBetsByRunner() {
        LOGGER.debug("Starting getAllBetsByRunner");

        int num = randomInteger(2, 5);
        Runner runner = createRunner();
        for (int i = 0; i < num; i++) {
            Bet bet = createBet();
            bet.setRunner(runner);
            betService.saveOrUpdate(bet);
        }

        List<Bet> bets = betService.getAllBetsByRunner(runner);
        Assert.assertEquals(bets.size(), num);
    }

    @Test
    public void deleteAllByUser() {
        LOGGER.debug("Starting deleteAllByUserTest");
        int num = randomInteger(2, 5);
        UserAccount user = createUser();
        for (int i = 0; i < num; i++) {
            Bet bet = createBet();
            bet.setUserAccount(user);
            betService.saveOrUpdate(bet);
        }

        betService.deleteAllByUser(user);
        List<Bet> bets = betService.getAllBetsByUser(user);
        Assert.assertEquals(bets.size(), 0);
    }

    @Test
    public void deleteAllByRunner() {
        LOGGER.debug("Starting deleteAllByUserTest");

        int num = randomInteger(2, 5);
        Runner runner = createRunner();
        for (int i = 0; i < num; i++) {
            Bet bet = createBet();
            bet.setRunner(runner);
            betService.saveOrUpdate(bet);
        }
        betService.deleteAllByRunner(runner);
        List<Bet> bets = betService.getAllBetsByRunner(runner);
        Assert.assertEquals(bets.size(), 0);
    }
}