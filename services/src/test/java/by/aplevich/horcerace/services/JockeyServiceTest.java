package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Jockey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class JockeyServiceTest extends AbstractServiceTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(JockeyServiceTest.class);

    @Inject
    private JockeyService jockeyService;

    @Inject
    private RunnerService runnerService;

    @Before
    public void cleanUpData() {
        runnerService.deleteAll();
        jockeyService.deleteAll();
    }

    @Test
    public void basicCRUDTest() {
        Jockey jockey = createJockey();
        jockeyService.saveOrUpdate(jockey);

        Jockey jockeyFromDB = jockeyService.get(jockey.getId());
        Assert.assertNotNull(jockeyFromDB);
        Assert.assertEquals(jockeyFromDB.getFname(), jockey.getFname());
        Assert.assertEquals(jockeyFromDB.getLname(), jockey.getLname());

        jockeyFromDB.setFname("newFname");
        jockeyFromDB.setLname("newLname");
        jockeyService.saveOrUpdate(jockeyFromDB);
        Jockey jockeyFromDbUpdated = jockeyService.get(jockey.getId());
        Assert.assertEquals(jockeyFromDbUpdated.getFname(), jockeyFromDB.getFname());
        Assert.assertEquals(jockeyFromDbUpdated.getLname(), jockeyFromDB.getLname());
        Assert.assertNotEquals(jockeyFromDbUpdated.getFname(), jockey.getFname());
        Assert.assertNotEquals(jockeyFromDbUpdated.getLname(), jockey.getLname());

        jockeyService.delete(jockeyFromDbUpdated);
        Assert.assertNull(jockeyService.get(jockey.getId()));
    }
}