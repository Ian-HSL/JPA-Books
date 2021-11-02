package csulb.cecs323.dataAccess.Publisher;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.Publisher;

import javax.persistence.EntityManager;

public class PublisherRepository extends Repository<Publisher> implements IPublisherRepository {
    public PublisherRepository(EntityManager manager){
        super.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllPublishers";
    }
}
