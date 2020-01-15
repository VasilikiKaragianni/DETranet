package base;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static Connection createConnection() {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/3C1QdEAmh9?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "3C1QdEAmh9", "23Ag8U15Kq");
            //System.out.println("Database Connected");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}





