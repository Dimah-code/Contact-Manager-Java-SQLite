import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        DBHelper.createTable();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n 1. Add contact \n 2. Show contacts \n 0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){

                case 1 -> addContact(sc);
                case 2 -> showContacts();
                case 0 -> exit(0);
                default -> System.out.println("Invalid Option");
            }
        }
    }
    private static void addContact(Scanner sc){
        System.out.println("Firstname: ");
        String firstName = sc.nextLine();
        System.out.println("Lastname: ");
        String lastName = sc.nextLine();
        System.out.println("Phone number: ");
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
    private static void showContacts(){
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
    }
}