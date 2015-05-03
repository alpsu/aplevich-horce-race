package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Place;

import java.util.List;

public interface PlaceDao extends AbstractDao<Long, Place>{
    List<Place> getAllPlaces();
}