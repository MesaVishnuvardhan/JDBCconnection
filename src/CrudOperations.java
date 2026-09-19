import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.*;

public class CrudOperations {

    private static final String  url = "jdbc:postgresql://localhost:5432/VishnuFirst";
    private static final String Username = "postgres";
    private static final String Password = "Your_Password";


    // CREATE -- >
    public static void insertDetails(int StudentId , String StudentName, int StudentMarks){


        String sql = "INSERT INTO \"Student\" VALUES(?,?,?)";
        try(Connection connection = DriverManager.getConnection(url,Username,Password)){

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1,StudentId);
        stmt.setString(2, StudentName);
        stmt.setInt(3,StudentMarks);

        int rows  = stmt.executeUpdate();
        System.out.println("Inserted" + rows);

        } catch (SQLException e) {
            System.out.println("Error..!" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // READ --- >
    public static void getDetails(int StudentId){
        String sql = "SELECT * FROM \"Student\" WHERE \"StudentId\" = ?";
        try(Connection connection = DriverManager.getConnection(url,Username,Password)){

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,StudentId);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("ID : " + rs.getInt("StudentId"));
                System.out.println("Name : " + rs.getString("StudentName"));
                System.out.println("Marks : " + rs.getInt("StudentMarks"));
            }else{
                System.out.println("Student not found....!");
            }
        }catch (SQLException e) {
            System.out.println("Error..!" + e.getMessage());
        }
    }

    // UPDATE -- >
    public static void updateDetails(int StudentId , String NewName , int NewMarks){
        String sql = "UPDATE \"Student\" SET \"StudentName\" = ?, \"StudentMarks\" = ? WHERE \"StudentId\" = ?";

        try(Connection connection = DriverManager.getConnection(url,Username,Password)){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,NewName);
            stmt.setInt(2,NewMarks);
            stmt.setInt(3,StudentId);

            int rows = stmt.executeUpdate();
            System.out.println("Updated" + rows);
        }catch (SQLException e) {
            System.out.println("Error..!" + e.getMessage());
        }
    }

    // DELETE --- >

    public static void deleteDetails(int StudentId){
        String sql = "DELETE FROM \"Student\" WHERE \"StudentId\" = ?";

        try(Connection connection = DriverManager.getConnection(url,Username,Password)){

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1,StudentId);

            int rows = stmt.executeUpdate();
            System.out.println("Deleted" + rows);

        } catch (Exception e) {
            System.out.println("Error..!" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Insert The StudentDetails :");
        insertDetails(6,"Springboot",99);

        System.out.println("Reading the Details..:");
        getDetails(6);

        System.out.println("Updated the Details : ");
        updateDetails(1,"Springboot",99);

        System.out.println("Deleting the Details : ");
        deleteDetails(2);
    }
}
