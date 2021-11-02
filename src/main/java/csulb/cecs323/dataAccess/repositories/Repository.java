package csulb.cecs323.dataAccess.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class Repository<T> {
    protected EntityManager manager;
    protected EntityTransaction tx;

    protected String getAllQueryName;

    public List<T> getAll(){
        return manager.createNamedQuery(getAllQueryName).getResultList();
    }

    public T getSingle(String queryName, Object[] parameterList){
        Query query = manager.createNamedQuery(queryName);

        for(int i = 0; i < parameterList.length; i++){
            query.setParameter(i + 1, parameterList[i]);
        }

        return (T) query.getSingleResult();
    }

    public void add(T entity){
        manager.persist(entity);
    }

    public void remove(T entity){
        manager.persist(entity);
    }

    public void update(String queryName, Object[] parameterList){
        Query query = manager.createNamedQuery(queryName);

        for(int i = 0; i < parameterList.length; i++) {
            query.setParameter(i + 1, parameterList[i]);
        }

        query.executeUpdate();
    }
}
