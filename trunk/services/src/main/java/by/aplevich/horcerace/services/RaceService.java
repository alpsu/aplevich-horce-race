package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;

import javax.transaction.Transactional;
import java.util.List;

public interface RaceService {
    Race get(Long id);

    @Transactional
    void saveOrUpdate(Race race);

    @Transactional
    void deleteAll();

    @Transactional
    void delete(Race race);

    List<Race> getAllRaceByPlace(Place place);
}