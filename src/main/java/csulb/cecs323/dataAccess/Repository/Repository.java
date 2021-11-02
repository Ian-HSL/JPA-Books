package csulb.cecs323.dataAccess.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class Repository<T> {
    protected EntityManager manager;
    protected EntityTransaction tx;

    protected String getAllQueryName;

    public List<T> getAll(){
        return manager.createNamedQuery(getAllQueryName).getResultList();
    }

    public List<T> getList(String queryName, Object[] parameterList){
        Query query = manager.createNamedQuery(queryName);

        for(int i = 0; i < parameterList.length; i++){
            query.setParameter(i + 1, parameterList[i]);
        }

        return query.getResultList();
    }

    public T getSingle(String queryName, Object[] parameterList){
        Query query = manager.createNamedQuery(queryName);

        for(int i = 0; i < parameterList.length; i++){
            query.setParameter(i + 1, parameterList[i]);
        }

        return (T) query.getSingleResult();
    }

    public void add(T entity) {
        tx.begin();
        manager.persist(entity);
        tx.commit();
    }

    public void remove(T entity){
        tx.begin();
        manager.remove(entity);
        tx.commit();
    }

    public void update(T entity){
        tx.begin();
        manager.merge(entity);
        tx.commit();
    }
}
