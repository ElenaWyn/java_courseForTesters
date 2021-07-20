package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionTest {

    @Test
    public void testDBConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            GroupSet groups = new GroupSet();
            while (rs.next()){
                groups.add(new Group().withId(rs.getInt("group_id")).withGroupName(rs.getString("group_name")).withGroupHeader(rs.getString("group_header"))
                        .withGroupFooter(rs.getString("group_footer")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);


            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
