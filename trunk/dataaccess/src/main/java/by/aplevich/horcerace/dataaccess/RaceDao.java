package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Place;
import by.aplevich.horcerace.datamodel.Race;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface RaceDao extends AbstractDao<Long, Race> {
    List<Race> getAllRacesWithPlace();

    List<Race> getAllRacesWithPlaceByPlace(Place place);
    List<Race> getAllRacesWithPlaceByPlace(Long placeId, SingularAttribute<Race, ?> attr, boolean ascending, int first, int pageSize);
    Long getCount(Long placeId);
}