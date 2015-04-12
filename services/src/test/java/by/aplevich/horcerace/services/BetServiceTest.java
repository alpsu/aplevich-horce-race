package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Bet;
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
}