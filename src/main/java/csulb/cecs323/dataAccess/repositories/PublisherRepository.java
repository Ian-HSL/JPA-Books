package csulb.cecs323.dataAccess.repositories;

import csulb.cecs323.dataAccess.IRepositories.IPublisherRepository;
import csulb.cecs323.model.Publisher;

import javax.persistence.EntityManager;

public class PublisherRepository extends Repository<Publisher> implements IPublisherRepository {
    public PublisherRepository(EntityManager manager){
        super.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllPublishers";
    }
}
