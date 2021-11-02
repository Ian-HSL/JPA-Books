package csulb.cecs323.model.AdHocTeam;

import csulb.cecs323.model.IndividualAuthor;

import javax.persistence.*;

@Entity
@Table(name = "Ad_Hoc_Teams_Member")
public class AdHocTeamsMember
{
    //primary key mapping
    @EmbeddedId
    adHocTeamsMemberPk id;

    //maps individual author email pk to this as a fk
    @MapsId("Individual_Authors_Email")
    @ManyToOne
    @JoinColumn(nullable = false, name = "Individual_Authors_Email", referencedColumnName = "email")
    /**The name of the individual author must be existing in database already*/
    private IndividualAuthor individualAuthor;


    //maps ad hot teams email pk to this as a fk
    @MapsId("Ad_Hoc_Teams_Email")
    @ManyToOne
    @JoinColumn(nullable = false, name = "Ad_Hoc_Teams_Email", referencedColumnName = "email")
    /**The name of the adhocteam must be existing in database already*/
    private AdHocTeam adHocTeam;

    //constructors
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
