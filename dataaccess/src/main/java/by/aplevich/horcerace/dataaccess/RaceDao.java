package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Race;

import java.util.List;

public interface RaceDao extends AbstractDao<Long, Race> {
    List<Race> getAllRacesWithPlace();
}