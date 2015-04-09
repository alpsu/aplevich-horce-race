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

public class RaceServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceServiceTest.class);

    @Inject
    private RaceService raceService;

    @Inject
    private PlaceService placeService;

    @Before
    public void cleanUpData() {
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
        // todo: compare all fields and do test getAllRaceByPlace and do it test
//        Assert.assertEquals(horceFromDB.getName(), race.getName());
//        Assert.assertEquals(horceFromDB.getTrainer(), race.getTrainer());
//        Assert.assertEquals(horceFromDB.getAge(), race.getAge());
//
//        horceFromDB.setName("newName");
//        horceService.saveOrUpdate(horceFromDB);
//        Horce horceFromDbUpdated = horceService.get(race.getId());
//        Assert.assertEquals(horceFromDbUpdated.getName(), horceFromDB.getName());
//        Assert.assertNotEquals(horceFromDbUpdated.getName(), race.getName());
//
//        horceService.delete(horceFromDbUpdated);
//        Assert.assertNull(horceService.get(race.getId()));
    }
}