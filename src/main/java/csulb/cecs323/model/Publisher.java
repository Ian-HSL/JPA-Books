//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package csulb.cecs323.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
/**This is the publisher of books */

@Entity
/**
 * Native query to get a list of all the publishers currently in the database*/
@NamedNativeQuery(
        name = "GetAllPublishers",
        query = "SELECT * " +
                "FROM Publishers ",
        resultClass = Publisher.class
)

/**
 * Native query to get a specific publisher by its name*/
@NamedNativeQuery(
        name = "GetPublisherByName",
        query = "SELECT * " +
                "FROM publishers " +
                "WHERE name = ?",
        resultClass = Publisher.class
)

@Table(name = "Publishers")
public class Publisher extends EntityClass{

    /**This denotes the list of books this publisher has published so far*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy="pub",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Book> books;

    @Id
    @Column(
            nullable = false,
            length = 80,
            name = "name"
    )
    /**The name of the publisher*/
    private String name;

    @Column(
            nullable = false,
            length = 24,
            unique = true,
            name = "phone"
    )
    /**The phone number you can reach this pubisher at*/
    private String phone;

    @Column(
            nullable = false,
            length = 80,
            unique = true,
            name = "email"
    )
    /**The email of the publisher*/
    private String email;

    //Constructors
    public Publisher() {
    }

    public Publisher(String name, String phone, String email) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    //displays the primary key formatted nicely
    public void displayPrimaryKey() {
        System.out.printf("Email: %s%n", email);;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String mail = String.format("%-20s",email);
        String n = String.format("%-20s", name);
        String p = String.format("%-20s", phone);
        return "Publisher{" +
                " name='" + n + " | " +
                " phone='" + p + " | " +
                " email='" + mail + " " +
                '}';
    }
}
