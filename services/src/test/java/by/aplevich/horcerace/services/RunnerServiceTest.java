package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RunnerServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerServiceTest.class);

    @Test
    public void basicCRUDTest() {
        LOGGER.debug("Starting basicCRUDTest for Runner");
        Runner runner = createRunner();

        Runner runnerFromDb = runnerService.get(runner.getId());
        Assert.assertNotNull(runnerFromDb);
    }

    @Test
    public void getAllRunnersTest() {
        LOGGER.debug("Starting getAllRunnersTest");
        Runner runnerOne = createRunner();
        Runner runnerTwo = createRunner();
        Race race = createRace();
        runnerOne.setRace(race);
        runnerTwo.setRace(race);
        runnerService.saveOrUpdate(runnerOne);
        runnerService.saveOrUpdate(runnerTwo);

        List<Runner> runners = runnerService.getAllRunnerByRace(race);
        Assert.assertEquals(runners.size(), 2);
    }

    @Test
    public void deleteAllInRaceTest() {
        LOGGER.debug("Starting deleteAllInRaceTest");
        Runner runnerOne = createRunner();
        Runner runnerTwo = createRunner();
        Race race = createRace();
        runnerOne.setRace(race);
        runnerTwo.setRace(race);
        runnerService.saveOrUpdate(runnerOne);
        runnerService.saveOrUpdate(runnerTwo);

        runnerService.deleteAllInRace(race);
        List<Runner> runners = runnerService.getAllRunnerByRace(race);
        Assert.assertEquals(runners.size(), 0);
    }
}