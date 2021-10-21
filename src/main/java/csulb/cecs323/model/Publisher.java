//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
/**This is the publisher of books */

@Entity

public class Publisher {

    /**This denotes the list of books this publisher has published so far*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy="pub",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Books> books;

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Id
    @Column(
            nullable = false,
            length = 80

    )
    /**The name of the publisher*/
    private String name;
    @Column(
            nullable = false,
            length = 80,
            unique = true
    )
    /**The phone number you can reach this pubisher at*/
    private String phone;
    @Column(
            nullable = false,
            length = 24,
            unique = true
    )
    /**The email of the publisher*/
    private String email;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Constructors
    public Publisher() {
    }

    public Publisher(String name, String phone, String email) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
