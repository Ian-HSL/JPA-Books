package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class adHocTeamsMemberPk implements Serializable {

    public String getIndividual_Authors_Email() {
        return Individual_Authors_Email;
    }

    public void setIndividual_Authors_Email(String individual_Authors_Email) {
        Individual_Authors_Email = individual_Authors_Email;
    }

    public String getAd_Hoc_Teams_Email() {
        return Ad_Hoc_Teams_Email;
    }

    public void setAd_Hoc_Teams_Email(String ad_Hoc_Teams_Email) {
        Ad_Hoc_Teams_Email = ad_Hoc_Teams_Email;
    }


    @Column(
            nullable = false,
            length = 30

    )
    /**The name of the publisher*/
    private String Individual_Authors_Email;
    //private IndividualAuthors IA;


    @Column(
            nullable = false,
            length = 30

    )
    /**The name of the publisher*/
    private String Ad_Hoc_Teams_Email;
    //private AdHocTeams AHT;

    public adHocTeamsMemberPk() {};
//    public Ad_Hoc_Teams_Member_pk(String ia, String aht)
//    {
//        this.Individual_Authors_Email = ia;
//        this.Ad_Hoc_Teams_Email = aht;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof adHocTeamsMemberPk)) return false;
        adHocTeamsMemberPk that = (adHocTeamsMemberPk) o;
        return Objects.equals(getIndividual_Authors_Email(), that.getIndividual_Authors_Email()) && Objects.equals(getAd_Hoc_Teams_Email(), that.getAd_Hoc_Teams_Email());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndividual_Authors_Email(), getAd_Hoc_Teams_Email());
    }
}
