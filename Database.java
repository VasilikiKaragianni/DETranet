package Base;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static Connection createConnection() {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema?autoReconnect=true&useSSL=false", "root", "Applekodikos30");

            return  con;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}





