package by.aplevich.horcerace.dataaccess.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.datamodel.Place;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 21.03.2015.
 */
@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Long, Place> implements PlaceDao {
    protected PlaceDaoImpl() {
        super(Place.class, Long.class);
    }
}
