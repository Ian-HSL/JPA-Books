package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity//(name = "WritingGroup")
@DiscriminatorValue(value = "WritingGroup")
public class WritingGroup extends AuthoringEntity
{
    public String getHead_writer() {
        return head_writer;
    }

    public void setHead_writer(String head_writer) {
        this.head_writer = head_writer;
    }

    @Column(length = 80)
    /** The Product Universal Product Code */
    private String head_writer;

    public int getYear_formed() {
        return year_formed;
    }

    public void setYear_formed(int year_formed) {
        this.year_formed = year_formed;
    }

    @Column
    /** The Product Universal Product Code */
    private int year_formed;

    public WritingGroup(){};

    public WritingGroup(String email, String name, String head_writer, int year_formed)
    {
        //super(email, authoringEntityType, name/**,head_writer,year_formed*/);
        super.setEmail(email);
        super.setAuthoring_entity_type("WritingGroup");
        super.setName(name);
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }
}
