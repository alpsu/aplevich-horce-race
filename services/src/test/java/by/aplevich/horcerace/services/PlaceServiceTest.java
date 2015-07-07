package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Place;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlaceServiceTest extends AbstractServiceTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceTest.class);

    @Test
    public void basicCRUDTest() {
        Place place = createPlace();

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

    @Test
    public void getAllPlaceTest() {
        int num = randomInteger(2, 5);

        for (int i = 0; i < num; i++) {
            Place place = createPlace();
            placeService.saveOrUpdate(place);
        }

        List<Place> places = placeService.getAllPlaces();
        Assert.assertEquals(places.size(), num);
    }
}