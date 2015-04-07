package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Place;

import javax.transaction.Transactional;

public interface PlaceService {
    Place get(Long id);

    @Transactional
    void saveOrUpdate(Place place);

    @Transactional
    void delete(Place place);

    @Transactional
    void deleteAll();
}
