package csulb.cecs323.dataAccess.Repository;

import csulb.cecs323.model.Book;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Interface for generic type to list, update, add, remove whatever object types for this project*/
public interface IGenericRepository<T> {

    List<T> getAll();

    List<T> getList(String query, Object[] parameterList);

    T getSingle(String queryName, Object[] parameterList);

    void add(T entity);

    void remove(T entity);

    void update(T entity);
}
