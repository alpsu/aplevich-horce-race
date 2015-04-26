package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RaceServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceServiceTest.class);

    @Test
    public void basicCRUDTest() {
        Race race = createRace();

        Race raceFromDB = raceService.get(race.getId());
        Assert.assertNotNull(raceFromDB);
        Assert.assertEquals(raceFromDB.getDescription(), race.getDescription());
        Assert.assertEquals(raceFromDB.getDistance(), race.getDistance());
        Assert.assertEquals(raceFromDB.getQuantity(), race.getQuantity());
        Assert.assertEquals(raceFromDB.getStart().getTime(), race.getStart().getTime());
    }

    @Test
    public void getAllRacesTest() {
        int num = randomInteger(2, 5);

        Place place = createPlace();
        for (int i = 0; i < num; i++) {
            Race race = createRace();
            race.setPlace(place);
            raceService.saveOrUpdate(race);
        }

        List<Race> races = raceService.getAllRaceByPlace(place);
        Assert.assertEquals(races.size(), num);
    }
}