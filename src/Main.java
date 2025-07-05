import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        DBHelper.createTable();
        Scanner sc = new Scanner(System.in);
        ContactService contactService = new ContactService();
        while(true){
            System.out.println("\n1. Add contact \n" +
                    "2. Show contacts \n" +
                    "3. Delete contact \n" +
                    "0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){

                case 1 -> contactService.addContact(sc);
                case 2 -> contactService.showContacts();
                case 3 -> contactService.deleteContact(sc);
                case 0 -> exit(0);
                default -> System.out.println("Invalid Option");
            }
        }
    }

}