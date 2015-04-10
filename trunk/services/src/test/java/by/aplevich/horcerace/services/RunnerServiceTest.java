package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class RunnerServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerServiceTest.class);

    @Inject
    private RunnerService runnerService;

    @Inject
    private HorceService horceService;

    @Inject
    private JockeyService jockeyService;

    @Inject
    private RaceService raceService;

    @Inject
    private PlaceService placeService;

    @Inject
    protected BetService betService;

    Race race = createRace();

    @Before
    public void cleanUpData() {
        //betService.deleteAll();
       runnerService.deleteAll();
    }

    @Test
    public void basicCRUDTest(){
        LOGGER.debug("Starting basicCRUDTest for Runner");
        Runner runner = createRunner();
        Horce horce = createHorce();
        Jockey jockey = createJockey();
        Race race = createRace();
        Place place = createPlace();
        placeService.saveOrUpdate(place);
        race.setPlace(placeService.get(place.getId()));
        raceService.saveOrUpdate(race);
        jockeyService.saveOrUpdate(jockey);
        horceService.saveOrUpdate(horce);
        runner.setHorce(horce);
        runner.setJockey(jockey);
        runner.setRace(race);
        runnerService.createRunner(runner);

        Runner runnerFromDb = runnerService.get(runner.getId());
        Assert.assertNotNull(runnerFromDb);
    }

    @Test
    public void getAllRunnersTest() {
        LOGGER.debug("Starting getAllRunnersTest");
        createTwoRunners();

        List<Runner> runners = runnerService.getAllRunnerByRace(race);
        Assert.assertEquals(runners.size(), 2);
    }

    @Test
    public void deleteAllInRaceTest() {
        LOGGER.debug("Starting deleteAllInRaceTest");
        createTwoRunners();

        runnerService.deleteAllInRace(race);
        List<Runner> runners = runnerService.getAllRunnerByRace(race);
        Assert.assertEquals(runners.size(), 0);
    }

    private void createTwoRunners() {
        Runner runnerOne = createRunner();
        Runner runnerTwo = createRunner();
        Horce horceOne = createHorce();
        Horce horceTwo = createHorce();
        Jockey jockeyOne = createJockey();
        Jockey jockeyTwo = createJockey();
        Place place = createPlace();
        placeService.saveOrUpdate(place);
        race.setPlace(placeService.get(place.getId()));
        raceService.saveOrUpdate(race);
        jockeyService.saveOrUpdate(jockeyOne);
        jockeyService.saveOrUpdate(jockeyTwo);
        horceService.saveOrUpdate(horceOne);
        horceService.saveOrUpdate(horceTwo);
        runnerOne.setHorce(horceOne);
        runnerTwo.setHorce(horceTwo);
        runnerOne.setJockey(jockeyOne);
        runnerTwo.setJockey(jockeyTwo);
        runnerOne.setRace(race);
        runnerTwo.setRace(race);
        runnerService.createRunner(runnerOne);
        runnerService.createRunner(runnerTwo);
    }
}
