package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.HorceDao;
import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.services.HorceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class HorceServiceImpl implements HorceService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HorceServiceImpl.class);

    @Inject
    private HorceDao dao;

    @Override
    public Horce get(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveOrUpdate(Horce horce) {
        if (horce.getId() == null) {
            LOGGER.debug("Save new: {}", horce);
            dao.insert(horce);
        } else {
            LOGGER.debug("Update: {}", horce);
            dao.update(horce);
        }
    }

    @Override
    public void delete(Horce horce) {
        LOGGER.debug("Remove: {}", horce);
        dao.delete(horce.getId());
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("Remove all horces");
        dao.deleteAll();
    }
}