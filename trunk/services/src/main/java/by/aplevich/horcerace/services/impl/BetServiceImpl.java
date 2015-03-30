package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.services.BetService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by admin on 21.03.2015.
 */
@Service
public class BetServiceImpl implements BetService {
    @Inject
    private BetDao dao;

    @Override
    public Bet get(Long id) {
        return dao.getById(id);
    }
}
