package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Place;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class PlaceServiceTest extends AbstractServiceTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceTest.class);

    @Inject
    private PlaceService placeService;

    @Before
    public void cleanUpData() {
        placeService.deleteAll();
    }

    @Test
    public void basicCRUDTest() {
        Place place = createPlace();
        placeService.saveOrUpdate(place);

        Place placeFromDB = placeService.get(place.getId());
        Assert.assertNotNull(placeFromDB);
        Assert.assertEquals(placeFromDB.getName(), place.getName());

        placeFromDB.setName("newPlace");
        placeService.saveOrUpdate(placeFromDB);
        Place placeFromDbUpdated = placeService.get(place.getId());
        Assert.assertEquals(placeFromDbUpdated.getName(), placeFromDB.getName());

        placeService.delete(placeFromDbUpdated);
        Assert.assertNull(placeService.get(place.getId()));
    }
}