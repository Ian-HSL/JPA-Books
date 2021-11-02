package csulb.cecs323.dataAccess.IRepositories;

import csulb.cecs323.model.Book;

import java.util.List;

public interface IGenericRepository<T> {
    List<T> getAll();

    T getSingle(String queryName, Object[] parameterList);

    void add(T entity);

    void remove(T entity);

    void update(String query, Object[] parameterList);
}
