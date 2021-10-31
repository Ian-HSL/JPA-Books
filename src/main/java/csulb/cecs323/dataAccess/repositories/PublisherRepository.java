package csulb.cecs323.dataAccess.repositories;

import csulb.cecs323.dataAccess.GenericRepository;
import csulb.cecs323.dataAccess.IRepositories.IPublisherRepository;
import csulb.cecs323.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class PublisherRepository extends GenericRepository<Publisher> implements IPublisherRepository {
    public PublisherRepository(EntityManager manager){
        super.manager = manager;
        tx = manager.getTransaction();
    }

    public List<Publisher> getAllPublishers() {
        return GetAll("GetAllPublishers");
    }

    public Publisher getPublisherByPhone(String phone){
        return manager.createNamedQuery("GetPublisherByPhone", Publisher.class)
                .setParameter(1, phone).getSingleResult();
    }

    public Publisher getPublisherByEmail(String email){
        return manager.createNamedQuery("GetPublisherByEmail", Publisher.class)
                .setParameter(1, email).getSingleResult();
    }
}
