package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Place;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by admin on 25.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class PlaceServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceTest.class);

    @Inject
    private PlaceService placeService;

    @Test
    public void Test(){
        LOGGER.warn("PlaceServiceTest log message");
        Assert.assertNotNull(placeService);
//        Place place = placeService.get(1l);
//        Assert.assertNotNull(place);
    }
}
