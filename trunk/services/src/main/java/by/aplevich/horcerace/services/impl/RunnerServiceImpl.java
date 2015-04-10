package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.datamodel.Runner_;
import by.aplevich.horcerace.services.RunnerService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class RunnerServiceImpl implements RunnerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerServiceImpl.class);

    @Inject
    private RunnerDao dao;

    @Override
    public Runner get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void createRunner(Runner runner) {
        Validate.isTrue(runner.getId() == null, "This method should be called for 'not saved runner yet' runner. Only");
        LOGGER.debug("Create runnner: {}", runner);
        dao.insert(runner);
    }

    @Override
    public void delete(Runner runner) {
        LOGGER.debug("Delete runner: {}", runner);
        dao.delete(runner.getId());
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("Delete all runner:");
        dao.deleteAll();
    }

    @Override
    public void deleteAllInRace(Race race) {
        LOGGER.debug("Deleting all runner in race: {}", race);
        List<Runner> runners = getAllRunnerByRace(race);
        List<Long> ids = new ArrayList<>();
        for (Runner runner : runners) {
            ids.add(runner.getId());
        }
        dao.delete(ids);
    }

    @Override
    public List<Runner> getAllRunnerByRace(Race race) {
       LOGGER.debug("Get all runner in race: {}", race);
        return dao.getAllByFieldRestriction(Runner_.race, race.getId());
    }
}