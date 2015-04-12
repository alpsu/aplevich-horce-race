package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.JockeyDao;
import by.aplevich.horcerace.datamodel.Jockey;
import by.aplevich.horcerace.services.JockeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class JockeyServiceImpl implements JockeyService{
    private static final Logger LOGGER = LoggerFactory.getLogger(JockeyServiceImpl.class);

    @Inject
    private JockeyDao dao;

    @Override
    public Jockey get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveOrUpdate(Jockey jockey) {
        if (jockey.getId() == null) {
            LOGGER.debug("Save new: {}", jockey);
            dao.insert(jockey);
        } else {
            LOGGER.debug("Update: {}", jockey);
            dao.update(jockey);
    }
    }

    @Override
    public void delete(Jockey jockey) {
        LOGGER.debug("Remove: {}", jockey);
        dao.delete(jockey.getId());
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("Remove all jockeys");
        dao.deleteAll();
    }
}