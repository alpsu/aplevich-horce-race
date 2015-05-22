package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Place;

import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.util.List;

public interface PlaceService {
    Place get(Long id);

    Place getByName(String name);

    @Transactional
    void saveOrUpdate(Place place);

    @Transactional
    void delete(Place place);

    @Transactional
    void deleteAll();

    List<Place> getAllPlaces();

    List<Place> getAllPlaces(SingularAttribute<Place, ?> attr, boolean ascending, int startRecord, int pageSize);
}
