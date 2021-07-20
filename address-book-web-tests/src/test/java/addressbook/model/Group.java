package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class Group {
    @Expose
    @Column(name = "group_name")
    private  String groupName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && Objects.equals(groupName, group.groupName) && Objects.equals(groupHeader, group.groupHeader) && Objects.equals(groupFooter, group.groupFooter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, groupHeader, groupFooter, id);
    }

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private  String groupHeader;

    public void setId(int id) {
        this.id = id;
    }

    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private  String groupFooter;
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;


    public Group withId(int id) {
        this.id = id;
        return this;
    }

    public Group withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public Group withGroupHeader(String groupHeader) {
        this.groupHeader = groupHeader;
        return this;
    }

    public Group withGroupFooter(String groupFooter) {
        this.groupFooter = groupFooter;
        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }
}
