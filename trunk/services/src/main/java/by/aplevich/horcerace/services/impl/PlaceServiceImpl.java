package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.PlaceDao;
import by.aplevich.horcerace.services.PlaceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 25.03.2015.
 */
@Service
public class PlaceServiceImpl implements PlaceService{
    @Inject
    private PlaceDao placeDao;
}
