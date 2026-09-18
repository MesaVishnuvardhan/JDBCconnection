import java.sql.*;  // Import the Package of the JDBC

public class Demojdbc {
    public static void main(String[] args) throws Exception {
        /*
           1 -> import the package
           2 -> load and register
           3 -> create the Connection
           4 -> create the Statement
           5 -> excute the statement
           6 -> Close Connection
         */

        String url = "jdbc:postgresql://localhost:5432/VishnuFirst"; // parse url at the using Db name
        String username = "postgres"; // Postgress username
        String password = "YOUR_PASSWORD"; // password of the postgresql
        String query = "select \"StudentName\" from \"Student\" where \"StudentId\" = 1";

        Class.forName("org.postgresql.Driver"); // This Step is for optional

        // Create Connections
        Connection connection = DriverManager.getConnection(url,username,password);

        // Create the Statement
        Statement st = connection.createStatement();
        st.executeQuery(query);

        System.out.println("Connection established....");

        connection.close();

    }
}
