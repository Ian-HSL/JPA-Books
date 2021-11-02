package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Writing Group")
public class WritingGroup extends AuthoringEntity
{
    public String getHeadWriter() {
        return headWriter;
    }

    public void setHeadWriter(String head_writer) {
        this.headWriter = head_writer;
    }

    @Column(length = 80, name = "Head_Writer")
    /** The Product Universal Product Code */
    private String headWriter;

    public int getYearFormed() {
        return yearFormed;
    }

    public void setYearFormed(int year_formed) {
        this.yearFormed = year_formed;
    }

    @Column(name = "year_formed")
    /** The Product Universal Product Code */
    private int yearFormed;

    public WritingGroup(){};

    public WritingGroup(String email, String name, String head_writer, int year_formed)
    {
        //super(email, authoringEntityType, name/**,head_writer,year_formed*/);
        super.setEmail(email);
        super.setAuthoringEntityType("Writing Group");
        super.setName(name);
        this.headWriter = head_writer;
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
