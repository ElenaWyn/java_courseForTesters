package addressbook.model;

import java.util.Objects;

public class Contact {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private final  String firstname;
    private final String lastname;
    public String middlename;
    public String nickname;
    public String title;
    public String company;
    private final String address;
    private final String tel_home;
    public String tel_mobile;
    public String tel_work;
    public String fax;
    public String email;
    public String email2;
    public String email3;
    public String homepage;
    public String birth_d;
    public String birth_m;
    public String birth_y;
    public String an_d;
    public String an_m;
    public String an_y;
    public String group;
    public String address_sec;
    public String home_sec;
    public String notes;


    public Contact(String firstname, String lastname, String address, String tel_home) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.tel_home = tel_home;
    }

    public Contact(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = null;
        this.tel_home = null;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(firstname, contact.firstname) && Objects.equals(lastname, contact.lastname) && Objects.equals(middlename, contact.middlename) && Objects.equals(nickname, contact.nickname) && Objects.equals(title, contact.title) && Objects.equals(company, contact.company) && Objects.equals(address, contact.address) && Objects.equals(tel_home, contact.tel_home) && Objects.equals(tel_mobile, contact.tel_mobile) && Objects.equals(tel_work, contact.tel_work) && Objects.equals(fax, contact.fax) && Objects.equals(email, contact.email) && Objects.equals(email2, contact.email2) && Objects.equals(email3, contact.email3) && Objects.equals(homepage, contact.homepage) && Objects.equals(birth_d, contact.birth_d) && Objects.equals(birth_m, contact.birth_m) && Objects.equals(birth_y, contact.birth_y) && Objects.equals(an_d, contact.an_d) && Objects.equals(an_m, contact.an_m) && Objects.equals(an_y, contact.an_y) && Objects.equals(group, contact.group) && Objects.equals(address_sec, contact.address_sec) && Objects.equals(home_sec, contact.home_sec) && Objects.equals(notes, contact.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, tel_home);
    }

    public Contact(int id, String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = null;
        this.tel_home = null;
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getTel_home() {
        return tel_home;
    }

}
