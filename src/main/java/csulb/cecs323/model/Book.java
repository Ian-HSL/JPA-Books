package csulb.cecs323.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity

@NamedNativeQuery(
        name = "GetAllBooks",
        query = "SELECT * " +
                "FROM books ",
        resultClass = Book.class
)

@NamedNativeQuery(
        name = "GetBookByISBN",
        query = "SELECT *" +
                "FROM books " +
                "WHERE ISBN = ? ",
        resultClass = Book.class
)

@NamedNativeQuery(
        name = "GetBookByTitleAndAuthoringEntityName",
        query = "SELECT * " +
                "FROM books " +
                "WHERE title = ? AND authoring_entity_name = ? ",
        resultClass = Book.class
)

@NamedNativeQuery(
        name = "GetBookByTitleAndYear",
        query = "SELECT * " +
                "FROM books " +
                "WHERE title = ? AND publisher_name = ? ",
        resultClass = Book.class
)

@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"title", "publisher_name"}),
                @UniqueConstraint(columnNames = {"title","authoring_entity_name"})},
        name = "Books"
)
public class Book {
    public AuthoringEntity getAE() {
        return AE;
    }

    public void setAE(AuthoringEntity AE) {
        this.AE = AE;
    }

    public Publisher getPub() {
        return pub;
    }

    public void setPub(Publisher pub) {
        this.pub = pub;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }



    /**This denotes that each book has a valid existing author*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "authoring_entity_name", referencedColumnName = "email")
    private AuthoringEntity AE;

    /**This denotes that each book has a valid existing publishser*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "publisher_name", referencedColumnName = "name")
    private Publisher pub;

    @Id
    @Column(nullable = false,
            length = 17)
    /**The unique code identifier of the book*/
    private String ISBN;

    @Column(nullable = false,
            length = 80)
    /**The name/title of the book*/
    private String title;

    @Column(nullable = false
            )
    /**The year the book was published*/
    private int yearPublished;

    //Constructors
    public Book(){};

    public Book(String ISBN, String title, int year_published, AuthoringEntity AE, Publisher pub)
    {
        this.ISBN=ISBN;
        this.title= title;
        this.yearPublished = year_published;

        //inherits FK
        this.AE = AE;
        this.pub = pub;
    }

    @Override
    public String toString(){
        String is = String.format("%-20s", ISBN);
        String t = String.format("%-20s", title);
        return  "ISBN: " + is + " | " +
                "Title: " + t + "\n" +
                "AE: " + AE.toString() +"\n" +
                "Publisher: " + pub.toString() +"\n------------------------------------------";
    }
}
