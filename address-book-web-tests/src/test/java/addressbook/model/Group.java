package addressbook.model;

import java.util.Objects;

public class Group {

    private  String groupName;
    private  String groupHeader;
    private  String groupFooter;
    private int id = Integer.MAX_VALUE;;


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
