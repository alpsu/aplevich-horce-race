package by.aplevich.horcerace;

import by.aplevich.horcerace.datamodel.*;
import by.aplevich.horcerace.datamodel.enums.BetType;
import by.aplevich.horcerace.datamodel.enums.Currency;
import by.aplevich.horcerace.datamodel.enums.UserRole;
import by.aplevich.horcerace.services.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public abstract class AbstractServiceTest {
    protected static final RandomData RANDOM_DATA = new RandomDataImpl();
    private final static Random random = new Random();
    private static final int RANDOM_STRING_SIZE = 8;

    @Inject
    protected BetService betService;
    @Inject
    protected HorceService horceService;
    @Inject
    protected JockeyService jockeyService;
    @Inject
    protected PlaceService placeService;
    @Inject
    protected RaceService raceService;
    @Inject
    protected RunnerService runnerService;
    @Inject
    protected UserService userService;

    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
    }

    public static String randomString(final String prefix) {
        return String.format("%s-%s", new Object[]{prefix, randomString()});
    }

    public static int randomTestObjectsCount() {
        return RANDOM_DATA.nextInt(0, 5) + 1;
    }

    public static int randomInteger() {
        return randomInteger(0, 9999);
    }

    public static int randomInteger(final int lower, final int upper) {
        return RANDOM_DATA.nextInt(lower, upper);
    }

    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }

    public static long randomLong() {
        return RANDOM_DATA.nextLong(0, 9999999);
    }

    public static BigDecimal randomBigDecimal() {
        return new BigDecimal(randomDouble()).setScale(2, RoundingMode.HALF_UP);
    }

    public static double randomDouble() {
        final double value = random.nextDouble() + randomInteger();
        return Math.round(value * 1e2) / 1e2;

    }

    public static <T> T randomFromCollection(final Collection<T> all) {
        final int size = all.size();
        final int item = new Random().nextInt(size); // In real life, the Random
        // object should be
        // rather more shared
        // than this
        int i = 0;
        for (final T obj : all) {
            if (i == item) {
                return obj;
            }
            i = i + 1;
        }
        return null;
    }

    static Date randomDate() {
        final int year = randBetween(1900, 2010);
        final GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.YEAR, year);
        final int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }

    public static int randBetween(final int start, final int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    protected Horce createHorce() {
        Horce horce = new Horce();
        horce.setAge(randomInteger(4, 9));
        horce.setName(randomString("horce"));
        horce.setTrainer(randomString("trainer"));
        horceService.saveOrUpdate(horce);
        return horce;
    }


    protected UserAccount createUser() {
        UserAccount userTwo = new UserAccount();
        userTwo.setLogin(randomString("login"));
        userTwo.setPassword(randomString("pass"));
        userTwo.setName(randomString("name"));
        userTwo.setRole(randomFromCollection(Arrays.asList(UserRole.values())));
        userService.createNewUser(userTwo);
        return userTwo;
    }

    protected Jockey createJockey() {
        Jockey jockey = new Jockey();
        jockey.setFname(randomString("first name"));
        jockey.setLname(randomString("last name"));
        jockeyService.saveOrUpdate(jockey);
        return jockey;
    }

    protected Place createPlace() {
        Place place = new Place();
        place.setName(randomString("place"));
        placeService.saveOrUpdate(place);
        return place;
    }

    protected Race createRace() {
        Race race = new Race();
        Place place = createPlace();
        race.setDescription(randomString("desc"));
        race.setDistance(randomString("distance"));
        race.setStart(randomDate());
        race.setQuantity(randBetween(3, 6));
        race.setPlace(place);
        placeService.saveOrUpdate(place);
        raceService.saveOrUpdate(race);
        return race;
    }

    protected Runner createRunner() {
        Runner runner = new Runner();
        Horce horce = createHorce();
        horceService.saveOrUpdate(horce);
        Jockey jockey = createJockey();
        jockeyService.saveOrUpdate(jockey);
        Race race = createRace();
        raceService.saveOrUpdate(race);
        runner.setKoefficient(randomDouble());
        runner.setHorce(horce);
        runner.setJockey(jockey);
        runner.setRace(race);
        runnerService.saveOrUpdate(runner);
        return runner;
    }

    protected Bet createBet() {
        Bet bet = new Bet();
        bet.setType(randomFromCollection(Arrays.asList(BetType.values())));
        bet.setCurrency(randomFromCollection(Arrays.asList(Currency.values())));
        bet.setSum(randomBigDecimal());
        Runner runner = createRunner();
        runnerService.saveOrUpdate(runner);
        UserAccount userAccount = createUser();
        userService.updateUser(userAccount);
        bet.setUserAccount(userAccount);
        bet.setRunner(runner);
        betService.saveOrUpdate(bet);
        return bet;
    }

    @Before
    public void cleanUpData() {
        betService.deleteAll();
        runnerService.deleteAll();
        raceService.deleteAll();
        userService.deleteAll();
        jockeyService.deleteAll();
        placeService.deleteAll();
        horceService.deleteAll();
    }
}