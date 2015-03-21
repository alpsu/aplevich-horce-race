package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.RaceDao;
import by.aplevich.horcerace.services.RaceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 21.03.2015.
 */
@Service
public class RaceServiceImpl implements RaceService {
    @Inject
    private RaceDao raceDao;
}
