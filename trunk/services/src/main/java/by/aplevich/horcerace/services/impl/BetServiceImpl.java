package by.aplevich.horcerace.services.impl;

import by.aplevich.horcerace.dataaccess.BetDao;
import by.aplevich.horcerace.datamodel.Bet;
import by.aplevich.horcerace.datamodel.UserAccount;
import by.aplevich.horcerace.services.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BetServiceImpl.class);

    @Inject
    private BetDao dao;

    @Override
    public Bet get(Long id) {
        return dao.getById(id);
    }
}
