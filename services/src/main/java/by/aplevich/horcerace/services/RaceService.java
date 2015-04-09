package by.aplevich.horcerace.services;

import by.aplevich.horcerace.datamodel.Race;

import javax.transaction.Transactional;

public interface RaceService {
    Race get(Long id);

    @Transactional
    void saveOrUpdate(Race race);

    @Transactional
    void deleteAll();

    @Transactional
    void delete(Race race);
}