package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.datamodel.Place;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Long, Place> implements PlaceDao {
    protected PlaceDaoImpl() {
        super(Place.class);
    }
}