package csulb.cecs323.dataAccess.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Instance of generic repository for updating database functions*/
public class Repository<T> {
    protected EntityManager manager;
    protected EntityTransaction tx;

    protected String getAllQueryName;

    /**
     * Gets a list of all of a certain query (no parameters) */
    public List<T> getAll(){
        return manager.createNamedQuery(getAllQueryName).getResultList();
    }

    /**
     * Get list of a specific objects in database such as writing group, individual authors, books, etc.
     * @param queryName : native query used
     * @param parameterList list of parameters for this native query
     * */
    public List<T> getList(String queryName, Object[] parameterList){
        Query query = manager.createNamedQuery(queryName);

        for(int i = 0; i < parameterList.length; i++){
            query.setParameter(i + 1, parameterList[i]);
        }

        return query.getResultList();
    }

    /**
     * Gets a single object from the database
     * @param queryName : native query used
     * @param parameterList list of parameters for this native query
     * */
    public T getSingle(String queryName, Object[] parameterList){
        Query query = manager.createNamedQuery(queryName);

        for(int i = 0; i < parameterList.length; i++){
            query.setParameter(i + 1, parameterList[i]);
        }

        return (T) query.getSingleResult();
    }

    /**
     * Adds any object type to the database
     * @param entity: object to be added to database
     * */
    public void add(T entity) {
        tx.begin();
        manager.persist(entity);
        tx.commit();
    }

    /**
     * Removes any object type from the database
     * @param entity: object to be removed from database*/
    public void remove(T entity){
        tx.begin();
        manager.remove(entity);
        tx.commit();
    }

    /**
     * Updates any object type from the database by updating entity param
     * @param entity : object to be updated*/
    public void update(T entity){
        tx.begin();
        manager.merge(entity);
        tx.commit();
    }
}
