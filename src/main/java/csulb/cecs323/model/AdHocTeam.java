package csulb.cecs323.model;

import javax.persistence.*;

/** This class is an authoring entity, I guess some kind of group
 * or team that participates with other authors to write a book or many books*/
@Entity
@NamedNativeQuery(
        name = "GetAllAdHocTeams",
        query = "SELECT * " +
                "FROM authoring_entities " +
                "WHERE authoring_entity_type = 'Ad Hoc Teams'",
        resultClass = AdHocTeam.class
)

@DiscriminatorValue(value = "Ad Hoc Teams")
@Table(name = "Ad_Hoc_Teams")
public class AdHocTeam extends AuthoringEntity {
    public AdHocTeam(){};

    public AdHocTeam(String name, String email)
    {
        super.setEmail(email);
        super.setAuthoringEntityType("Ad Hoc Teams");
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
}
