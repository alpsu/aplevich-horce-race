package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.services.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

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

    @Override
    public List<Place> getAllPlaces() {
        return dao.getAllPlaces();
    }

    @Override
    public List<Place> getAllPlaces(SingularAttribute<Place, ?> attr, boolean ascending, int startRecord, int pageSize) {
        return dao.getAllPlaces(attr,ascending,startRecord,pageSize);
    }

    @Override
    public Place getByName(String name) {
        return dao.getByName(name);
    }
}