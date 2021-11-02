package csulb.cecs323.model.AdHocTeam;

import csulb.cecs323.model.IndividualAuthor;

import javax.persistence.*;

@Entity
@Table(name = "Ad_Hoc_Teams_Member")
public class AdHocTeamsMember
{
    @EmbeddedId
    adHocTeamsMemberPk id;

    @MapsId("Individual_Authors_Email")
    @ManyToOne
    @JoinColumn(nullable = false, name = "Individual_Authors_Email", referencedColumnName = "email")
    /**The name of the publisher*/
    private IndividualAuthor individualAuthor;


    @MapsId("Ad_Hoc_Teams_Email")
    @ManyToOne
    @JoinColumn(nullable = false, name = "Ad_Hoc_Teams_Email", referencedColumnName = "email")
    /**The name of the publisher*/
    private AdHocTeam adHocTeam;

    public AdHocTeamsMember(){};

    public AdHocTeamsMember(AdHocTeam aht, IndividualAuthor ia)
    {
        this.individualAuthor = ia;
        this.adHocTeam = aht;
    }

    public void setIndividualAuthor(IndividualAuthor individualAuthor){
        this.individualAuthor = individualAuthor;
    }

    public void setAdHocTeam(AdHocTeam adHocTeam){
        this.adHocTeam = adHocTeam;
    }

    @Override
    public String toString() {
//        String ia = String.format("%40s", IA);
//        String aht = String.format("%40s", AHT);
        return "Ad_Hoc_Teams_Member{" +
                ", IndividualAuthor=" + individualAuthor + " | " +
                ", AdHocTeamName=" + adHocTeam +
                '}';

    }


}
