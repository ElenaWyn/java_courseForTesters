package addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactSet extends ForwardingSet<Contact> {
    private Set<Contact> delegate;

    public ContactSet(ContactSet contacts) {
        this.delegate = new HashSet<Contact>(contacts.delegate);
    }

    public ContactSet(Collection<Contact> contacts) { this.delegate = new HashSet<Contact>(contacts); }

    public ContactSet() {
        this.delegate = new HashSet<Contact>();
    }

    @Override
    protected Set<Contact> delegate() {
        return delegate;
    }

    public ContactSet withAdded(Contact contact) {
        ContactSet contacts = new ContactSet(this);
        contacts.add(contact);
        return contacts;
    }

    public ContactSet without(Contact contact) {
        ContactSet contacts = new ContactSet(this);
        contacts.remove(contact);
        return contacts;
    }

}
