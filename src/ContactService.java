import java.sql.*;
import java.util.Scanner;

public class ContactService {
    public void addContact(Scanner sc){
        System.out.print("Firstname: ");
        String firstName = sc.nextLine();
        System.out.print("Lastname: ");
        String lastName = sc.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = sc.nextLine();

        String sql = "INSERT INTO contacts(firstName, lastName, phone) VALUES (?, ? , ?)";
        try(Connection conn = DBHelper.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.executeUpdate();
            System.out.println("Contact Created Successfully!");
        }catch (SQLException e){
            System.out.println("Contact creation failed! " + e.getMessage());
        }
    }
    public void showContacts(){
        String sql = "SELECT * FROM contacts";
        try(Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            System.out.println("\n ----- Contacts List ----- ");

            while (rs.next()){
                System.out.println(rs.getString("id") + " | " +
                        rs.getString("firstName") + " | " +
                        rs.getString("lastName") + " | " +
                        rs.getString("phone")
                );
            }
        }catch (SQLException e){
            System.out.println("Failed to load contacts: " + e.getMessage());
        }
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println("Error");
        }
    }
    public void deleteContact(Scanner sc){
        System.out.println("\nEnter Contact's ID: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM contacts WHERE id = ?";
        try(Connection conn = DBHelper.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1,id);
            int affectedRow = pstmt.executeUpdate();
            if(affectedRow > 0){
                System.out.println("Contact with id " + id + " deleted successfully!");
            }else{
                System.out.println("Contact not found!");
            }
        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
