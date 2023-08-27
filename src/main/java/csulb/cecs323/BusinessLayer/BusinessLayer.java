package csulb.cecs323.BusinessLayer;

import csulb.cecs323.dataAccess.AuthoringEntity.AdHocTeamMember.IAdHocTeamMemberRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.AdHocTeam.AdHocTeamRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.AdHocTeam.IAdHocTeamRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.IAuthoringEntityRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.IndividualAuthor.IIndividualAuthorRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.IndividualAuthor.IndividualAuthorRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.WritingGroup.WritingGroupRepository;
import csulb.cecs323.dataAccess.Book.IBookRepository;
import csulb.cecs323.dataAccess.Publisher.IPublisherRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.AdHocTeamMember.AdHocTeamMemberRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.AuthoringEntityRepository;
import csulb.cecs323.dataAccess.Book.BookRepository;
import csulb.cecs323.dataAccess.Publisher.PublisherRepository;
import csulb.cecs323.dataAccess.AuthoringEntity.WritingGroup.IWritingGroupRepository;
import csulb.cecs323.model.*;
import csulb.cecs323.model.AdHocTeam.AdHocTeam;
import csulb.cecs323.model.AdHocTeam.AdHocTeamsMember;

import javax.persistence.EntityManager;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
/**
 * Repositories to get all the objects from the database and store them/update them by
 * calling data access methods with error handling exceptions.
 * */
public class BusinessLayer implements IBusinessLayer {
    private final IBookRepository _booksRepository;
    private final IPublisherRepository _publisherRepository;
    private final IAuthoringEntityRepository _authoringRepository;
    private final IAdHocTeamMemberRepository _adHocTeamMemberRepository;
    private final IWritingGroupRepository _writingGroupRepository;
    private final IAdHocTeamRepository _adHocTeamRepository;
    private final IIndividualAuthorRepository _individualRepository;

    public BusinessLayer(EntityManager manager){
        _booksRepository = new BookRepository(manager);
        _publisherRepository = new PublisherRepository(manager);
        _authoringRepository = new AuthoringEntityRepository(manager);
        _adHocTeamMemberRepository = new AdHocTeamMemberRepository(manager);
        _writingGroupRepository = new WritingGroupRepository(manager);
        _adHocTeamRepository = new AdHocTeamRepository(manager);
        _individualRepository = new IndividualAuthorRepository(manager);
    }

    /*
        Get all methods : gets a list of all objects of this description from database
    */

    public List<AuthoringEntity> getAllAuthoringEntities(){
        return _authoringRepository.getAll();
    }

    public List<AdHocTeam> getAllAdHocTeams(){
        return _adHocTeamRepository.getAll();
    }

    public List<WritingGroup> getAllWritingGroups(){
        return _writingGroupRepository.getAll();
    }

    public List<IndividualAuthor> getAllIndividualAuthors(){
        return _individualRepository.getAll();
    }

    public List<Publisher> getAllPublishers(){
        return _publisherRepository.getAll();
    }


    /*
        All Add methods
    */

    /**Adds publisher to database if it doesn't already exist
     * @param newPublisher : new publisher to be added*/
    public void addPublisher(Publisher newPublisher){
        try{
            _publisherRepository.add(newPublisher);
        } catch (Exception e){
            System.out.println("Publisher already exists.");
        }
    }
    /**Adds authoring entity to database if it doesn't already exist
     * @param newAuthoringEntity: new authoring entity to be added*/
    public void addAuthoringEntity(AuthoringEntity newAuthoringEntity){
        try{
            _authoringRepository.add(newAuthoringEntity);
        } catch (Exception e){
            System.out.println("Authoring entity already exists. ");
        }
    }

    /**Adds adhocteammember to database if it doesn't already exist
     * @param newAdHocTeamMember : new ad hoc team member to be added*/
    public void addAdHocTeamMember(AdHocTeamsMember newAdHocTeamMember){
        try{
            _adHocTeamMemberRepository.add(newAdHocTeamMember);
        } catch (Exception e){
            System.out.println("Author already in team. ");
        }
    }

    /*
        Book methods
    */
    /**
     * Gets all the books from the database
     * */
    public List<Book> getAllBooks(){
        return _booksRepository.getAll();
    }

    /**Adds new book to database if it doesn't already exist
     * @param newBook : new book to be added*/
    public void addBook(Book newBook) {
        try {
            _booksRepository.add(newBook);
        } catch (Exception e){
            System.out.println("Book already exists. ");
        }
    }

    /**
     * Deletes book from database
     * @param book : book to be deleted from database*/
    public void deleteBook(Book book){
        _booksRepository.remove(book);
    }

    /**
     * updates book from database
     * @param book : book to be updated in database*/
    public void updateBook(Book book){
        _booksRepository.update(book);
    }

    /*
        All get single methods
    */

    /**
     * Gets a single publisher by its name */
    public Publisher getPublisherByName(String name){
        Object[] parameterList = new Object[1];
        parameterList[0] = name;

        return _publisherRepository.getSingle("GetPublisherByName", parameterList);
    }

    /**
     * Gets an authoring entity from database by its name */
    public AuthoringEntity getAuthoringEntityByName(String name){
        Object[] parameterList = new Object[1];
        parameterList[0] = name;

        return _authoringRepository.getSingle("GetAuthoringEntityByName", parameterList);
    }
}
