package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Place;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface PlaceDao extends AbstractDao<Long, Place>{
    List<Place> getAllPlaces();
    List<Place> getAllPlaces(SingularAttribute<Place, ?> attr, boolean ascending, int startRecord, int pageSize);
}