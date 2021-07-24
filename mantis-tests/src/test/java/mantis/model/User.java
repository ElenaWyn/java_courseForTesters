package mantis.model;

public class User {

    public String userName;
    public String login;
    public String password;
    public String mail;

    public String getMail() {
        return mail;
    }

    public User withMail(String mailBeforeAT) {
        this.mail = mail + "@localhost.localdomain";
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User withLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }
}
