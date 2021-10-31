package csulb.cecs323.dataAccess.IRepositories;

import csulb.cecs323.model.Publisher;

import java.util.List;

public interface IPublisherRepository {
    List<Publisher> getAllPublishers();

    Publisher getPublisherByPhone(String phone);

}
