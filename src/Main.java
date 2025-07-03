import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            if(conn != null){
                System.out.println("Connection Successfully!");
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }
    }
}