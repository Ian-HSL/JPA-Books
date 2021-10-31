package csulb.cecs323.BusinessLayer;

import csulb.cecs323.model.*;

import java.util.List;

public interface IBusinessLayer {
    List<Book> getAllBooks();

    Book getBookByISBN(String ISBN);

    List<Publisher> getAllPublishers();
/*
    List<AuthoringEntity> getAllAEs();

    List<AuthoringEntity> getAllIndividualAuthors();

    List<AuthoringEntity> getAllAdHocTeams();
 */
}
