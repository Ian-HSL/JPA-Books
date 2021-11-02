package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

/**This class represents the types of authors, namely authoring entity, that has been writing books*/
@Entity

@Table(name = "Authoring_Entities")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authoring_entity_type", discriminatorType = DiscriminatorType.STRING)

@NamedNativeQuery(
        name = "GetAllAuthoringEntities",
        query = "SELECT * " +
                "FROM Authoring_Entities ",
        resultClass = AuthoringEntity.class
)

public class AuthoringEntity extends EntityClass
{
    @OneToMany(fetch = FetchType.LAZY, mappedBy="AE",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    /**List of books this authoring entity has written*/
    protected List<Book> books;

    @Id
    @Column(nullable = false, length = 30, name = "email")
    /** The AuthoringEntity's email!  */
    protected String email;

    @Column( length = 31, name = "authoring_entity_type")
    /** The AuthoringEntity's inheritence type (will be defined by its subclasses) */
    protected String authoringEntityType;

    @Column(nullable = false, length = 80, name = "name")
    /** The AuthoringEnttity's full name. */
    protected String entityName;

    public AuthoringEntity(){}

    public void displayPrimaryKey(){
        System.out.printf("Email: %-20sEntity Type: %-20s%n", email, authoringEntityType);
    }

    /*
        Getter methods
    */

    public List<Book> getBooks() {
        return books;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthoringEntityType() {
        return authoringEntityType;
    }

    public String getName() {
        return entityName;
    }

    /*
        Setter methods
    */

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthoringEntityType(String authoring_entity_type) {
        this.authoringEntityType = authoring_entity_type;
    }

    public void setName(String name) {
        this.entityName = name;
    }
}
