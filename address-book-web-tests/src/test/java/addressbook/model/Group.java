package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("group")
public class Group {
    @Expose
    private  String groupName;
    @Expose
    private  String groupHeader;

    public void setId(int id) {
        this.id = id;
    }

    @Expose
    private  String groupFooter;
    @XStreamOmitField
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && Objects.equals(groupName, group.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, id);
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
