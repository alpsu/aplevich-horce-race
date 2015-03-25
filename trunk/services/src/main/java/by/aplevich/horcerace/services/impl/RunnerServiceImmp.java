package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.RunnerDao;
import by.aplevich.horcerace.services.RunnerService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 25.03.2015.
 */
@Service
public class RunnerServiceImmp implements RunnerService {
    @Inject
    private RunnerDao runnerDao;
}
