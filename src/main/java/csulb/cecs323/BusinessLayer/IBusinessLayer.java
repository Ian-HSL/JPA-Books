package csulb.cecs323.BusinessLayer;

import csulb.cecs323.model.*;

import java.util.List;

public interface IBusinessLayer {
    /*
        Get all methods
    */
    List<Book> getAllBooks();

    List<AuthoringEntity> getAllAuthoringEntities();

    List<AdHocTeam> getAllAdHocTeams();

    List<WritingGroup> getAllWritingGroups();

    List<IndividualAuthors> getAllIndividualAuthors();

    List<Publisher> getAllPublishers();

    void deleteBook(String title, String publisherName, String authoringEntityName);


    /*
        All add methods
    */

    void addBook(Book book);

    void addPublisher(Publisher newPublisher);

    void addAuthoringEntity(AuthoringEntity authoringEntity);

    void addAdHocTeamMember(AdHocTeamsMember newAdhocTeamMember);

}
