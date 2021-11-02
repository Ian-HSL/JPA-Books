package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

/**This class represents the types of authors, namely authoring entity, that has been writing books*/
@Entity


@Table(name = "Authoring_Entities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authoring_entity_type", discriminatorType = DiscriminatorType.STRING)

@NamedNativeQuery(
        name = "getAllAE",
        query = "SELECT * " +
                "FROM Authoring_Entities ",
        resultClass = AuthoringEntity.class
)
@NamedNativeQuery(
        name = "getAllSpecificMembers",
        query = "SELECT * " +
                "FROM Authoring_Entities " +
                "WHERE authoring_entity_type = ?",
        resultClass = AuthoringEntity.class
)
public class AuthoringEntity
{
    @OneToMany(fetch = FetchType.LAZY, mappedBy="AE",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    /**List of books this authoring entity has written*/
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    //adds a single book to this authoringentity.
    public void addBooks(Book book)
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

    public String getAuthoringEntityType() {
        return authoringEntityType;
    }

    public void setAuthoringEntityType(String authoring_entity_type) {
        this.authoringEntityType = authoring_entity_type;
    }

    public String getName() {
        return entityName;
    }

    public void setName(String name) {
        this.entityName = name;
    }

    @Id
    @Column(nullable = false, length = 30)
    /** The AuthoringEntity's email!  */
    private String email;

    @Column( length = 31)
    /** The AuthoringEntity's inheritence type (will be defined by its subclasses) */
    private String authoringEntityType;

    @Column(nullable = false, length = 80, name = "name")
    /** The AuthoringEnttity's full name. */
    private String entityName;
}
