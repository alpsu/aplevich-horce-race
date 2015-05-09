package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Jockey;

import javax.transaction.Transactional;
import java.util.List;

public interface JockeyService {
    Jockey get(Long id);

    @Transactional
    void saveOrUpdate(Jockey jockey);

    @Transactional
    void delete(Jockey jockey);

    @Transactional
    void deleteAll();

    List<Jockey> getAllJockeys();
}
