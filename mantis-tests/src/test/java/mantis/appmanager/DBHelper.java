package mantis.appmanager;

import mantis.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    Connection conn = null;
    public List<User> allUsers() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT `id`, `username`, `email` FROM `mantis_user_table`");
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User().withId(rs.getInt("id")).withUserName(rs.getString("username")).withLogin(rs.getString("username")).withMail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();
            return users;
            // System.out.println(groups);


            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;
    }
}
