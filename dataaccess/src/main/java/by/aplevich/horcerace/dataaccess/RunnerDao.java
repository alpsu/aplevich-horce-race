package by.aplevich.horcerace.dataaccess;

import by.aplevich.horcerace.datamodel.Runner;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

public interface RunnerDao extends AbstractDao<Long, Runner> {
}