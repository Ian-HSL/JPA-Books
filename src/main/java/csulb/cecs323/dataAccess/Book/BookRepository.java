package csulb.cecs323.dataAccess.Book;

import javax.persistence.EntityManager;

import csulb.cecs323.dataAccess.Repository.Repository;
import csulb.cecs323.model.Book;

public class BookRepository extends Repository<Book> implements IBookRepository {
    public BookRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
        getAllQueryName = "GetAllBooks";
    }
}
