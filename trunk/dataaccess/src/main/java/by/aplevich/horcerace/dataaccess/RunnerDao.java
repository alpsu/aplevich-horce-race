package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Runner;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface RunnerDao extends AbstractDao<Long, Runner> {
    List<Runner>  getAllRunnersByRaceWith(Race race);

    List<Runner> getAllRunnersByRaceWith(Long raceId, SingularAttribute<Runner, ?> attr, boolean ascending, int startRecord, int pageSize);

    Long getCount(Long raceId);
}