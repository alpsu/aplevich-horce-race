package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.datamodel.Runner;
import by.aplevich.horcerace.services.RunnerService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RunnerServiceImpl implements RunnerService {
    @Inject
    private RunnerDao dao;

    @Override
    public Runner get(Long id) {
        return dao.getById(id);
    }
}
