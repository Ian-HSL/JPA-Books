package csulb.cecs323.BusinessLayer;

import csulb.cecs323.dataAccess.IRepositories.IAdHocTeamMemberRepository;
import csulb.cecs323.dataAccess.IRepositories.IAuthoringEntityRepository;
import csulb.cecs323.dataAccess.IRepositories.IBookRepository;
import csulb.cecs323.dataAccess.IRepositories.IPublisherRepository;
import csulb.cecs323.dataAccess.repositories.AdHocTeamMemberRepository;
import csulb.cecs323.dataAccess.repositories.AuthoringEntityRepository;
import csulb.cecs323.dataAccess.repositories.BookRepository;
import csulb.cecs323.dataAccess.repositories.PublisherRepository;
import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import java.util.List;

public class BusinessLayer implements IBusinessLayer {

    private final IBookRepository _booksRepository;
    private final IPublisherRepository _publisherRepository;
    private final IAuthoringEntityRepository _authoringRepository;
    private final IAdHocTeamMemberRepository _adHocTeamMemberRepository;

    public BusinessLayer(EntityManager manager){
        _booksRepository = new BookRepository(manager);
        _publisherRepository = new PublisherRepository(manager);
        _authoringRepository = new AuthoringEntityRepository(manager);
        _adHocTeamMemberRepository = new AdHocTeamMemberRepository(manager);
    }

    /*
        Get all methods
    */

    public List<Book> getAllBooks(){
        return _booksRepository.getAll();
    }

    public List<AuthoringEntity> getAllAuthoringEntities(){ return _authoringRepository.getAll();}

    public List<AdHocTeam> getAllAdHocTeams(){
        return _authoringRepository.getAllAdHocTeams();
    }

    public List<WritingGroup> getAllWritingGroups(){
        return _authoringRepository.getAllWritingGroups();
    }

    public List<IndividualAuthors> getAllIndividualAuthors(){ return _authoringRepository.getAllIndividualAuthors();}

    public List<Publisher> getAllPublishers(){
        return _publisherRepository.getAll();
    }


    /*
        Add methods
    */

    public void addBook(Book newBook){
        _booksRepository.add(newBook);
    }

    public void addPublisher(Publisher newPublisher){
        _publisherRepository.add(newPublisher);
    }

    public void addAuthoringEntity(AuthoringEntity newAuthoringEntity){
        _authoringRepository.add(newAuthoringEntity);
    }

    public void addAdHocTeamMember(AdHocTeamsMember newAdHocTeamMember){
        _adHocTeamMemberRepository.add(newAdHocTeamMember);
    }

    /*
        Get book methods
    */

    public Book getBookByISBN(String ISBN){
        Object[] parameterList = new Object[1];
        parameterList[0] = ISBN;

        return _booksRepository.getSingle("GetBookByISBN", parameterList);
    }

    public Book getBookByTitleAndAuthoringEntityName(String title, String yearPublished){
        Object[] parameterList = new Object[2];
        parameterList[0] = title;
        parameterList[1] = yearPublished;

        return _booksRepository.getSingle("GetBookByTitleAndYear", parameterList);
    }

    public Book getBookByTitleAndPublisher(String title, String publisherName){
        Object[] parameterList = new Object[2];
        parameterList[0] = title;
        parameterList[1] = publisherName;

        return _booksRepository.getSingle("GetBookByTitleAndPublisher", parameterList);
    }

    public void deleteBook(String title, String publisherName, String authoringEntityName){
        try {
            // Just evaluate the first candidate key
            Book book = getBookByTitleAndPublisher(title, publisherName);

            _booksRepository.remove(book);
        } catch (Exception e){
            System.out.println("Book does not exist.");
        }
    }




}
