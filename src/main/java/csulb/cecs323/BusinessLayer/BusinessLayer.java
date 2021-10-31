package csulb.cecs323.BusinessLayer;

import csulb.cecs323.dataAccess.IRepositories.IBookRepository;
import csulb.cecs323.dataAccess.IRepositories.IPublisherRepository;
import csulb.cecs323.dataAccess.repositories.BookRepository;
import csulb.cecs323.dataAccess.repositories.PublisherRepository;
import csulb.cecs323.model.Book;
import csulb.cecs323.model.Publisher;

import javax.persistence.EntityManager;
import java.util.List;

public class BusinessLayer implements IBusinessLayer {

    private IBookRepository _booksRepository;
    private IPublisherRepository _publisherRepository;

    public BusinessLayer(EntityManager manager){
        _booksRepository = new BookRepository(manager);
        _publisherRepository = new PublisherRepository(manager);
    }

    public List<Book> getAllBooks(){
        return _booksRepository.GetAllBooks();
    }

    public Book getBookByISBN(String ISBN){
        return _booksRepository.GetBookByISBN(ISBN);
    }


    public List<Publisher> getAllPublishers(){
        return _publisherRepository.getAllPublishers();
    }
/*
    public List<Publisher> getAllPublishers(){

    }

    public List<AuthoringEntity> getAllAEs(){

    }

    public List<AuthoringEntity> getAllIndividualAuthors(){

    }

    public List<AuthoringEntity> getAllAdHocTeams(){

    }
*/

}
