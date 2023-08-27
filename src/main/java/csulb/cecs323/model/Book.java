package csulb.cecs323.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * Class that represents a book object by it's isbn, title, publisher etc like in a library*/
@Entity

/**
 * This native query gets all the books currently in the database*/
@NamedNativeQuery(
        name = "GetAllBooks",
        query = "SELECT * " +
                "FROM books ",
        resultClass = Book.class
)

@NamedNativeQuery(
        name = "getBooksPK",
        query = "SELECT ISBN " +
                "FROM books ",
        resultClass = Books.class
)
@NamedNativeQuery(
        name = "findBook",
        query = "SELECT * " +
                "FROM Books " +
                "WHERE title = ?" ,
        resultClass = Books.class
)

@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"title", "publisher_name"}),
                @UniqueConstraint(columnNames = {"title","authoring_entity_name"})},
        name = "Books"
)
public class Book extends EntityClass{
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
            length = 17,
            name = "ISBN")
    /**The unique code identifier of the book*/
    private String ISBN;

    @Column(nullable = false,
            length = 80,
            name = "title")
    /**The name/title of the book*/
    private String title;

    @Column(nullable = false,
            name = "year_published"
            )
    /**The year the book was published*/
    private int yearPublished;

    //Constructors
    public Book(){}

    public Book(String ISBN, String title, int year_published, AuthoringEntity AE, Publisher pub)
    {
        this.ISBN=ISBN;
        this.title= title;
        this.yearPublished = year_published;

        //inherits FK
        this.AE = AE;
        this.pub = pub;
    }

    public void displayPrimaryKey() {
        System.out.println("ISBN: " + ISBN);
    }

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

    @Override
    public String toString(){
        String is = String.format("%-20s", ISBN);
        String t = String.format("%-20s", title);
        return  "ISBN: " + is + " | " +
                "Title: " + t + "\n" +
                "AE: " + AE.toString() +"\n" +
                "Publisher: " + pub.toString() +"\n------------------------------------------";
    }

    public String printPK(){
        String is = String.format("%-20s", ISBN);
        String t = String.format("%-20s", title);
        return  "ISBN: " + is + " | " +
                "Title: " + t + "\n";
    }
}
