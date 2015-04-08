package by.aplevich.horcerace.services;

import by.aplevich.horcerace.AbstractServiceTest;
import by.aplevich.horcerace.datamodel.Horce;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class HorceServiceTest extends AbstractServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HorceServiceTest.class);

    @Inject
    private HorceService horceService;

    @Before
    public void cleanUpData() {
        horceService.deleteAll();
    }

    @Test
    public void basicCRUDTest() {
        Horce horce = createHorce();
        horceService.saveOrUpdate(horce);

        Horce horceFromDB = horceService.get(horce.getId());
        Assert.assertNotNull(horceFromDB);
        Assert.assertEquals(horceFromDB.getName(), horce.getName());
        Assert.assertEquals(horceFromDB.getTrainer(), horce.getTrainer());
        Assert.assertEquals(horceFromDB.getAge(), horce.getAge());

        horceFromDB.setName("newName");
        horceService.saveOrUpdate(horceFromDB);
        Horce horceFromDbUpdated = horceService.get(horce.getId());
        Assert.assertEquals(horceFromDbUpdated.getName(), horceFromDB.getName());
        Assert.assertNotEquals(horceFromDbUpdated.getName(), horce.getName());

        horceService.delete(horceFromDbUpdated);
        Assert.assertNull(horceService.get(horce.getId()));
    }
}