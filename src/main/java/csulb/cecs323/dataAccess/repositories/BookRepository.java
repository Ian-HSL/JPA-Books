package csulb.cecs323.dataAccess.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import csulb.cecs323.dataAccess.GenericRepository;
import csulb.cecs323.dataAccess.IRepositories.IBookRepository;
import csulb.cecs323.model.Book;

import java.util.List;

public class BookRepository extends GenericRepository implements IBookRepository {
    EntityManager manager;
    EntityTransaction tx;
    public BookRepository(EntityManager manager){
        this.manager = manager;
        tx = manager.getTransaction();
    }

    public List<Book> GetAllBooks() {
        return GetAll("GetAllBooks");
    }

    public Book GetBookByISBN(String ISBN) {
        return (Book) manager.createNamedQuery("GetBookByISBN").getSingleResult();
    }
}
