package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.dataaccess.RaceDao;
import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;
import by.aplevich.horcerace.datamodel.Race_;
import by.aplevich.horcerace.services.RaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceServiceImpl.class);

    @Inject
    private RaceDao dao;

    @Override
    public Race get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveOrUpdate(Race race) {
        if (race.getId() == null) {
            LOGGER.debug("Save new: {}", race);
            dao.insert(race);
        } else {
            LOGGER.debug("Update: {}", race);
            dao.update(race);
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("Delete all races");
        dao.deleteAll();
    }

    @Override
    public void delete(Race race) {
        LOGGER.debug("Delete: {}", race);
        dao.deleteAll();
    }

    @Override
    public List<Race> getAllRaceByPlace(Place place) {
        LOGGER.debug("Get all race in place: {}", place);
        return dao.getAllByFieldRestriction(Race_.place, place.getId());
    }

    @Override
    public List<Race> getAllRacesWithPlace() {
        return dao.getAllRacesWithPlace();
    }

    @Override
    public List<Race> getAllRacesWithPlaceByPlace(Place place) {
        return dao.getAllRacesWithPlaceByPlace(place);
    }

    @Override
    public List<Race> getAllRacesWithPlaceByPlace(Long placeId, SingularAttribute<Race, ?> attr, boolean ascending, int first, int pageSize) {
        return dao.getAllRacesWithPlaceByPlace(placeId, attr, ascending, first, pageSize);
    }

    @Override
    public Long getCount(Long placeId) {
        return dao.getCount(placeId);
    }
}