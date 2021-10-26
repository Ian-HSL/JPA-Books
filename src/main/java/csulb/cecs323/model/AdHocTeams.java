package csulb.cecs323.model;
import csulb.cecs323.model.AuthoringEntity;

import javax.persistence.*;
import java.util.List;

/** This class is a authoring entity, I guess some kind of group
 * or team that participates with other authors to write a book or many books*/
@Entity
@DiscriminatorValue(value = "AdHocTeams")
public class AdHocTeams extends AuthoringEntity {
    public AdHocTeams(){};

    public AdHocTeams(String email, String name)
    {

        super.setEmail(email);
        super.setAuthoring_entity_type("AdHocTeams");
        super.setName(name);


    }

    @Override
    public String toString() {
        String mail = String.format("%-20s",this.getEmail());
        String name = String.format("%-20s", this.getName());
        return "AdHocTeams{ " +
                "Email= " + mail + " | " +
                "Name= " + name + " }" ;
    }

    /**Maps the adhocteam to many authors. So it denotes that
     * The adhocteam is made up of many or one authors. */
    /**
    @OneToMany(mappedBy="adHocTeamsList")
    @JoinTable(
            name = "team_email",
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "email")
    )
    private List<IndividualAuthors> authors;


    public List<IndividualAuthors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<IndividualAuthors> authors) {
        this.authors = authors;
    }
    public void addAuthor(IndividualAuthors author)
    {
        if(authors.contains(author))
        {
            System.out.println(">ERROR>This author is already part of the AdHocTeam...");
            return;
        }
        else
        {   System.out.println("Added author " + author.getName() + " to team!");
            authors.add(author);
        }
    }
    */


}
