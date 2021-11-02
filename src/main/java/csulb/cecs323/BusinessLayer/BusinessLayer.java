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
        Get all methods
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

    public void addPublisher(Publisher newPublisher){
        try{
            _publisherRepository.add(newPublisher);
        } catch (Exception e){
            System.out.println("Publisher already exists.");
        }
    }

    public void addAuthoringEntity(AuthoringEntity newAuthoringEntity){
        try{
            _authoringRepository.add(newAuthoringEntity);
        } catch (Exception e){
            System.out.println("Authoring entity already exists. ");
        }
    }

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
    public List<Book> getAllBooks(){
        return _booksRepository.getAll();
    }

    public void addBook(Book newBook) {
        try {
            _booksRepository.add(newBook);
        } catch (Exception e){
            System.out.println("Book already exists. ");
        }
    }

    public void deleteBook(Book book){
        _booksRepository.remove(book);
    }

    public void updateBook(Book book){
        _booksRepository.update(book);
    }

    /*
        All get single methods
    */

    public Publisher getPublisherByName(String name){
        Object[] parameterList = new Object[1];
        parameterList[0] = name;

        return _publisherRepository.getSingle("GetPublisherByName", parameterList);
    }

    public AuthoringEntity getAuthoringEntityByName(String name){
        Object[] parameterList = new Object[1];
        parameterList[0] = name;

        return _authoringRepository.getSingle("GetAuthoringEntityByName", parameterList);
    }
}
