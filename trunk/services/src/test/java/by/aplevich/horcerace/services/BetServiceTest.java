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
        Bet betOne = createBet();
        Bet betTwo = createBet();
        UserAccount user = createUser();
        betOne.setUserAccount(user);
        betTwo.setUserAccount(user);
        betService.saveOrUpdate(betOne);
        betService.saveOrUpdate(betTwo);

        List<Bet> bets = betService.getAllBetsByUser(user);
        Assert.assertEquals(bets.size(), 2);
    }

    @Test
    public void getAllBetsByRunner() {
        LOGGER.debug("Starting getAllBetsByRunner");
        Bet betOne = createBet();
        Bet betTwo = createBet();
        Runner runner = createRunner();
        betOne.setRunner(runner);
        betTwo.setRunner(runner);
        betService.saveOrUpdate(betOne);
        betService.saveOrUpdate(betTwo);

        List<Bet> bets = betService.getAllBetsByRunner(runner);
        Assert.assertEquals(bets.size(), 2);
    }

    @Test
    public void deleteAllByUser() {
        LOGGER.debug("Starting deleteAllByUserTest");
        Bet betOne = createBet();
        Bet betTwo = createBet();
        UserAccount user = createUser();
        betOne.setUserAccount(user);
        betTwo.setUserAccount(user);
        betService.saveOrUpdate(betOne);
        betService.saveOrUpdate(betTwo);

        betService.deleteAllByUser(user);
        List<Bet> bets = betService.getAllBetsByUser(user);
        Assert.assertEquals(bets.size(), 0);
    }

    @Test
    public void deleteAllByRunner() {
        LOGGER.debug("Starting deleteAllByUserTest");
        Bet betOne = createBet();
        Bet betTwo = createBet();
        Runner runner = createRunner();
        betOne.setRunner(runner);
        betTwo.setRunner(runner);
        betService.saveOrUpdate(betOne);
        betService.saveOrUpdate(betTwo);

        betService.deleteAllByRunner(runner);
        List<Bet> bets = betService.getAllBetsByRunner(runner);
        Assert.assertEquals(bets.size(), 0);
    }
}