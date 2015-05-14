package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;

import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.util.List;

public interface RunnerService {
    Runner get(Long id);

    @Transactional
    void saveOrUpdate(Runner runner);

    @Transactional
    void delete(Runner runner);

    @Transactional
    void deleteAll();

    @Transactional
    void deleteAllInRace(Race race);

    List<Runner> getAllRunnerByRace(Race race);
    List<Runner> getAllRunnerByRace(Long placeId, Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending);
    //List<Object[]> getAllRunnerByRace(Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending);
    Long getCount(Long raceId);
}