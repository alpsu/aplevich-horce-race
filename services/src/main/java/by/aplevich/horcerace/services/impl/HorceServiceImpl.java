package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.HorceDao;
import by.aplevich.horcerace.datamodel.Horce;
import by.aplevich.horcerace.services.HorceService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 25.03.2015.
 */
@Service
public class HorceServiceImpl implements HorceService{
    @Inject
    private HorceDao dao;

    @Override
    public Horce get(Long id) {
        return dao.getById(id);
    }
}
