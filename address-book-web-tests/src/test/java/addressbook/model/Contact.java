package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("group")
public class Contact {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

    public Contact() {

    }


    public Contact withId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    @Expose
    private   String firstname;
    @Expose
    private  String lastname;
    @Expose
    public String middlename;
    @Expose
    public String nickname;
    @Expose
    public String title;
    @Expose
    public String company;
    @Expose
    private String address;


    @Expose
    private String tel_home;
    @Expose
    public String tel_mobile;
    @Expose
    public String tel_work;
    public String AllPhones;

    public Contact withAllPhones(String allPhones) {
        this.AllPhones = allPhones;
        return this;
    }

    public String getAllPhones() {
        return AllPhones;
    }

    public Contact withTel_mobile(String tel_mobile) {
        this.tel_mobile = tel_mobile;
        return this;
    }


    public Contact withEmail(String email) {
        this.email = email;
        return this;
    }

    public Contact withEmail2(String email2) {
        this.email2 = email2;
        return this;

    }

    public String getTel_mobile() {
        return tel_mobile;
    }

    public String getTel_work() {
        return tel_work;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public Contact withEmail3(String email3) {
        this.email3 = email3;
        return this;

    }

    public Contact withTel_work(String tel_work) {
        this.tel_work = tel_work;
        return this;
    }

    @Expose
    public String fax;
    @Expose
    public String email;
    @Expose
    public String email2;
    @Expose
    public String email3;
    public String allMails;

    public String getAllMails() {
        return allMails;
    }

    public Contact withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    @Expose
    public String homepage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(firstname, contact.firstname) && Objects.equals(lastname, contact.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

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
    public File photo;

    public File getPhoto() {
        return photo;
    }

    public Contact withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public Contact withFirstname (String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Contact withLastname (String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Contact withAddress (String address) {
        this.address = address;
        return this;
    }

    public Contact withTel_home (String tel_home) {
        this.tel_home = tel_home;
        return this;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
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
