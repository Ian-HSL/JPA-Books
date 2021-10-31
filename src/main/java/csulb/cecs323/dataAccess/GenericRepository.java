package csulb.cecs323.dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GenericRepository<T> implements IGenericRepository<T>{
    protected EntityManager manager;
    protected EntityTransaction tx;

    public List<T> GetAll(String query){
        return manager.createNamedQuery(query).getResultList();
    }

    public void Add(T[] entities){
        for (T entity : entities)
            manager.persist(entity);
    }

    public void Remove(T[] entities){
        for(T entity : entities)
            manager.persist(entity);
    }
}
