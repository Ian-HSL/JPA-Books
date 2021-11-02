package csulb.cecs323.BusinessLayer;

import csulb.cecs323.model.*;
import csulb.cecs323.model.AdHocTeam.AdHocTeam;
import csulb.cecs323.model.AdHocTeam.AdHocTeamsMember;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface IBusinessLayer {
    /*
        Get all methods
    */

    List<AuthoringEntity> getAllAuthoringEntities();

    List<AdHocTeam> getAllAdHocTeams();

    List<WritingGroup> getAllWritingGroups();

    List<IndividualAuthor> getAllIndividualAuthors();

    List<Publisher> getAllPublishers();


    /*
        All add methods
    */

    void addBook(Book book);

    void addPublisher(Publisher newPublisher);

    void addAuthoringEntity(AuthoringEntity authoringEntity);

    void addAdHocTeamMember(AdHocTeamsMember newAdhocTeamMember);


    /*
        Book methods
    */
    List<Book> getAllBooks();

    void deleteBook(Book book);

    void updateBook(Book book);
}
