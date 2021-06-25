package addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class GroupSet extends ForwardingSet<Group> {

    private Set<Group> delegate;

    public GroupSet(GroupSet groups) {
        this.delegate = new HashSet<Group>(groups.delegate);
    }

    public GroupSet() {
        this.delegate = new HashSet<Group>();
    }

    @Override
    protected Set<Group> delegate() {
        return delegate;
    }

    public GroupSet withAdded(Group group) {
        GroupSet groups = new GroupSet(this);
        groups.add(group);
        return groups;
    }

    public GroupSet without(Group group) {
        GroupSet groups = new GroupSet(this);
        groups.remove(group);
        return groups;
    }
}
