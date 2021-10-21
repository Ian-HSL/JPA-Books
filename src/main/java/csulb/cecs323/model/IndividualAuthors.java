package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;

/**This is a class of individual authors that write books on their own. They
 * may join an adhocteam and write books together as that adhocteam group name*/
@Entity
@DiscriminatorValue(value = "IndividualAuthors")
public class IndividualAuthors extends AuthoringEntity{


    /**This is the list of different groups this author writes books with when they aren't just
     * writing books individually if they even do*/
/**    @ManyToMany
    private List<AdHocTeams> adHocTeamsList;
    public List<AdHocTeams> getAdHocTeamsList() {
        return adHocTeamsList;
    }

    public void setAdHocTeamsList(List<AdHocTeams> adHocTeamsList) {
        this.adHocTeamsList = adHocTeamsList;
    }

    //Adds a single adhocteam to author (author joined this adhocteam)
    public void addAdHocTeam(AdHocTeams aht)
    {
        if(adHocTeamsList.contains(aht))
        {
            System.out.println(">ERROR>This author is already part of the AdHocTeam...");
            return;
        }
        else
        {   System.out.println("Added our author to " + aht.getName() + " 's adhocteam!");
            adHocTeamsList.add(aht);
        }
    }
*/
    //Constructors
    public IndividualAuthors(){};

    public IndividualAuthors(String email, String name)
    {

        super.setEmail(email);
        super.setAuthoring_entity_type("IndividualAuthors");
        super.setName(name);


    }
}
