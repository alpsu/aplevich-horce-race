package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.JockeyDao;
import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.services.JockeyService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 25.03.2015.
 */
@Service
public class JockeyServiceImpl implements JockeyService{
    @Inject
    private JockeyDao dao;

    @Override
    public Jockey get(Long id) {
        return dao.getById(id);
    }
}
