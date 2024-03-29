package csulb.cecs323.model;

import javax.persistence.*;

/**
 * This class represents a group of writers where there is assumed one main writer that is known to the public*/
@Entity

/**
 * Native Query where we get all the writing group authoring entites from the database*/
@NamedNativeQuery(
        name = "GetAllWritingGroups",
        query = "SELECT * " +
                "FROM Authoring_Entities " +
                "WHERE authoring_entity_type = 'Writing Group'",
        resultClass = WritingGroup.class
)

@DiscriminatorValue(value = "Writing Group")
public class WritingGroup extends AuthoringEntity
{
    @Column(length = 80, name = "Head_Writer")
    /** the main writer for this writing group. though other authors are not mentioned */
    private String headWriter;

    @Column(name = "year_formed")
    /** year the group was formed */
    private int yearFormed;

    public WritingGroup(){};

    public WritingGroup(String email, String name, String head_writer, int year_formed)
    {
        super.setEmail(email);
        super.setAuthoringEntityType("Writing Group");
        super.setName(name);
        this.setHeadWriter(head_writer);
        this.setYearFormed(year_formed);
    }

    public String getHeadWriter() {
        return headWriter;
    }

    public int getYearFormed() {
        return yearFormed;
    }

    public void setHeadWriter(String head_writer) {
        this.headWriter = head_writer;
    }

    public void setYearFormed(int year_formed) {
        this.yearFormed = year_formed;
    }

    @Override
    public String toString() {
        String n = String.format("%-20s", this.getName());
        String e = String.format("%-20s", this.getEmail());
        String hw = String.format("%-20s", headWriter);

        return "WritingGroup{" +
                "Email= " + this.getEmail() + " | " +
                "Name= " + n + " | " +
                "head_writer= " + headWriter + " | " +
                "year_formed= " + yearFormed +
                '}';
    }
}
