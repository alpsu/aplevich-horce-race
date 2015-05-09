package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Horce;

import javax.transaction.Transactional;
import java.util.List;

public interface HorceService {
    Horce get(Long id);

    @Transactional
    void saveOrUpdate(Horce horce);

    @Transactional
    void delete(Horce horce);

    @Transactional
    void deleteAll();

    List<Horce> getAllHorces();
}
