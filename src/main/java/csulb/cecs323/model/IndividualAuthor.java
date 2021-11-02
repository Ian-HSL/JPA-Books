package csulb.cecs323.model;

import javax.persistence.*;
import javax.persistence.Entity;

/**This is a class of individual authors that write books on their own. They
 * may join an adhocteam and write books together as that adhocteam group name*/
@Entity

/**
 * Native query that gets all the individual authors in the database only*/
@NamedNativeQuery(
        name = "GetAllIndividualAuthors",
        query = "SELECT * " +
                "FROM Authoring_Entities " +
                "WHERE authoring_entity_type = 'Individual Authors'",
        resultClass = IndividualAuthor.class
)

@DiscriminatorValue(value = "Individual Authors")
public class IndividualAuthor extends AuthoringEntity {
    //Constructors
    public IndividualAuthor(){};

    public IndividualAuthor(String name, String email)
    {
        super.setEmail(email);
        super.setAuthoringEntityType("Individual Authors");
        super.setName(name);
    }

    @Override
    public String toString() {
        String mail = String.format("%-20s",this.getEmail());
        String name = String.format("%-20s", this.getName());
        return "IndividualAuthors{ " +
                "Email= " + mail + " | " +
                "Name= " + name + " }" ;
    }
}
