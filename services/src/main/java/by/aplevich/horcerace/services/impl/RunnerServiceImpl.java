package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.services.RunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
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
    public void saveOrUpdate(Runner runner) {
        if (runner.getId() == null) {
            LOGGER.debug("Save new: {}", runner);
            dao.insert(runner);
        } else {
            LOGGER.debug("Update: {}", runner);
            dao.update(runner);
        }
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
        //return dao.getAllByFieldRestriction(Runner_.race, race.getId());
        return dao.getAllRunnersByRaceWith(race);
    }

    @Override
    public List<Runner> getAllRunnerByRace(Long placeId, Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending) {
        LOGGER.debug("Get all runner in race: {}", raceId);

        List<Runner> allRunnersByRaceWith = dao.getAllRunnersByRaceWith(placeId, raceId, attr, ascending);
        return allRunnersByRaceWith;
        //return dao.getAllRunnersByRaceWith(placeId, raceId, attr, ascending);
    }

   /* @Override
    public List<Object[]> getAllRunnerByRace(Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending) {
        LOGGER.debug("Get all runner in race: {}", raceId);

        List<Object[]> allRunnersByRaceWith = dao.getAllRunnersByRaceWith(raceId, attr, ascending);
        return allRunnersByRaceWith;
        //return dao.getAllRunnersByRaceWith(placeId, raceId, attr, ascending);
    }*/

    @Override
    public Long getCount(Long raceId) {
        return dao.getCount(raceId);
    }
}