package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Horce;
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
public class HorceServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HorceServiceTest.class);

    @Inject
    private HorceService horceService;

    @Test
    public void Test(){
        LOGGER.warn("HorceServiceTest log message");
        Assert.assertNotNull(horceService);
        Horce horce = horceService.get(1l);
        Assert.assertNotNull(horce);
    }
}
