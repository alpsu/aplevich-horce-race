package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Place;

import javax.transaction.Transactional;
import java.util.List;

public interface PlaceService {
    Place get(Long id);

    @Transactional
    void saveOrUpdate(Place place);

    @Transactional
    void delete(Place place);

    @Transactional
    void deleteAll();

    List<Place> getAllPlaces();
}
