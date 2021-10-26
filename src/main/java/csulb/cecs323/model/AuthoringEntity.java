package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

/**This class represents the types of authors, namely authoring entity, that has been writing books*/
@Entity
@Table(name = "Authoring_Entities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authoring_entity_type", discriminatorType = DiscriminatorType.STRING)
public class AuthoringEntity
{
    @OneToMany(fetch = FetchType.LAZY, mappedBy="AE",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    /**List of books this authoring entity has written*/
    private List<Books> books;

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    //adds a single book to this authoringentity.
    public void addBooks(Books book)
    {
        if(books.contains(book))
        {
            System.out.println(">ERROR>This book is already recorded...");
        }
        else
        {   System.out.println("Added " + book.getTitle() + " - BY - " + getName() );
            books.add(book);
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthoring_entity_type() {
        return authoring_entity_type;
    }

    public void setAuthoring_entity_type(String authoring_entity_type) {
        this.authoring_entity_type = authoring_entity_type;
    }

    public String getName() {
        return entityName;
    }

    public void setName(String name) {
        this.entityName = name;
    }

//    public String getHead_writer() {
//        return head_writer;
//    }
//
//    public void setHead_writer(String head_writer) {
//        this.head_writer = head_writer;
//    }
//
//    public int getYear_formed() {
//        return year_formed;
//    }
//
//    public void setYear_formed(int year_formed) {
//        this.year_formed = year_formed;
//    }

    @Id
    @Column(nullable = false, length = 30)
    /** The AuthoringEntity's email!  */
    private String email;

    @Column( length = 31)
    /** The AuthoringEntity's inheritence type (will be defined by its subclasses) */
    private String authoring_entity_type;

    @Column(nullable = false, length = 80, name = "name")
    /** The AuthoringEnttity's full name. */
    private String entityName;

    //Only default constructor made. We never make an authoring entitiy, but I'm not sure
    //if we make it abstract or not. :P

//    @Column(length = 80)
//    /** The Product Universal Product Code */
//    private String head_writer;
//
//    @Column
//    /** The Product Universal Product Code */
//    private int year_formed;


//    public AuthoringEntity(){};
//    public AuthoringEntity(String email, String authoring_entity_type, String name /**,String head_writer, int year_formed*/)
//    {
////        this.head_writer = head_writer;
////        this.year_formed = year_formed;
//        this.email = email;
//        this.authoring_entity_type = authoring_entity_type;
//        this.name = name;
//    }



}
