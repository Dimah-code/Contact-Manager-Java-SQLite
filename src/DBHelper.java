import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:contacts.db";
    public static Connection connect(){
        try{
            return DriverManager.getConnection(DB_URL);
        }catch (SQLException e){
            System.out.println("Connection Failed! " + e.getMessage());
            return null;
        }
    }
    public static void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS contacts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT NOT NULL, " +
                "lastName TExT, " +
                "phone TEXT)";
        try(Connection conn = connect(); Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        }catch(SQLException e){
            System.out.println("Table Creation Failed!: " + e.getMessage());
        }
    }
}
