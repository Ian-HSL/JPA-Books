package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
//@IdClass(Ad_Hoc_Teams_Member_pk.class)
@Entity

public class Ad_Hoc_Teams_Member
{
    @EmbeddedId Ad_Hoc_Teams_Member_pk id;
//    /**This denotes the list of books this publisher has published so far*/
//    @OneToMany(fetch = FetchType.LAZY, mappedBy="pub",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Books> books;
//
//    public List<Books> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Books> books) {
//        this.books = books;
//    }


    @MapsId("Individual_Authors_Email")
    @ManyToOne
    @JoinColumn(nullable = false, name = "Individual_Authors_Email", referencedColumnName = "email")
    /**The name of the publisher*/
    private IndividualAuthors IA;


    @MapsId("Ad_Hoc_Teams_Email")
    @ManyToOne
    @JoinColumn(nullable = false, name = "Ad_Hoc_Teams_Email", referencedColumnName = "email")
    /**The name of the publisher*/
    private AdHocTeams AHT;

    public Ad_Hoc_Teams_Member (){};

    public Ad_Hoc_Teams_Member(AdHocTeams aht, IndividualAuthors ia)
    {
        this.IA = ia;
        this.AHT = aht;
    }


}
