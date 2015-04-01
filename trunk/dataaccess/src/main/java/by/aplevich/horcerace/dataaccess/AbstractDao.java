package by.aplevich.horcerace.dataaccess;

public interface AbstractDao<ID, Entity> {

    Entity getById(ID id);
}
