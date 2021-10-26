package csulb.cecs323.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//@Table(uniqueConstraints = {@UniqueConstraint(columnNames =
//        {"first_name", "last_name", "phone"})})
@Entity

@NamedNativeQuery(
        name = "getAllBooks",
        query = "SELECT * " +
                "FROM books ",
        resultClass = Books.class
)

@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"title", "publisher_name"}),
                @UniqueConstraint(columnNames = {"title","authoring_entity_name"})}
)
public class Books {
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

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
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
    private int year_published;



    //Constructors
    public Books(){};

    public Books(String ISBN,  String title, int year_published, AuthoringEntity AE, Publisher pub)
    {
        this.ISBN=ISBN;
        this.title= title;
        this.year_published = year_published;

        //inherits FK
        this.AE = AE;
        this.pub = pub;
    }

    @Override
    public String toString(){
        return  "ISBN: " + ISBN + "\n" +
                "Title: " + title;
    }
}
