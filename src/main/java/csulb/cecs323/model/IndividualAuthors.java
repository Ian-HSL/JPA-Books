package csulb.cecs323.model;

import javax.persistence.*;

/**This is a class of individual authors that write books on their own. They
 * may join an adhocteam and write books together as that adhocteam group name*/
@Entity
@DiscriminatorValue(value = "Individual Authors")
public class IndividualAuthors extends AuthoringEntity {
    //Constructors
    public IndividualAuthors(){};

    public IndividualAuthors(String name, String email)
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
