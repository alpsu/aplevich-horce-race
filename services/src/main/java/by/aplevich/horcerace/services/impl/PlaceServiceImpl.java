package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.services.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service
public class PlaceServiceImpl implements PlaceService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Inject
    private PlaceDao dao;

    @Override
    public Place get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveOrUpdate(Place place) {
        if (place.getId() == null) {
            LOGGER.debug("Save new: {}", place);
            dao.insert(place);
        } else {
            LOGGER.debug("Update: {}", place);
            dao.update(place);
        }
    }

    @Override
    public void delete(Place place) {
        LOGGER.debug("Remove: {}", place);
        dao.delete(place.getId());
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("Remove all places");
        dao.deleteAll();
    }
}