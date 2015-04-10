package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class RaceServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceServiceTest.class);

    @Inject
    private RaceService raceService;

    @Inject
    private PlaceService placeService;

    @Inject
    private RunnerService runnerService;

    @Before
    public void cleanUpData() {
        runnerService.deleteAll();
        raceService.deleteAll();
    }

    @Test
    public void basicCRUDTest() {
        Race race = createRace();
        Place place = createPlace();
        placeService.saveOrUpdate(place);
        race.setPlace(placeService.get(place.getId()));
        raceService.saveOrUpdate(race);

        Race raceFromDB = raceService.get(race.getId());
        Assert.assertNotNull(raceFromDB);
        Assert.assertEquals(raceFromDB.getDescription(), race.getDescription());
        Assert.assertEquals(raceFromDB.getDistance(), race.getDistance());
        Assert.assertEquals(raceFromDB.getQuantity(), race.getQuantity());
        Assert.assertEquals(raceFromDB.getStart().getTime(), race.getStart().getTime());
    }

    @Test
    public void getAllRacesTest() {
        Race raceOne = createRace();
        Race raceTwo = createRace();
        Place place = createPlace();
        placeService.saveOrUpdate(place);
        raceOne.setPlace(placeService.get(place.getId()));
        raceTwo.setPlace(placeService.get(place.getId()));
        raceService.saveOrUpdate(raceOne);
        raceService.saveOrUpdate(raceTwo);

        List<Race> races = raceService.getAllRaceByPlace(place);
        Assert.assertEquals(races.size(), 2);
    }
}