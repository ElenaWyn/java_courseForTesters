package addressbook;

public class Contact {
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
