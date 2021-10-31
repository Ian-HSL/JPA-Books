package csulb.cecs323.dataAccess;

import java.util.List;

public interface IGenericRepository<T>{
    List<T> GetAll(String query);

    void Add(T[] entities);

    void Remove(T[] entities);
}
