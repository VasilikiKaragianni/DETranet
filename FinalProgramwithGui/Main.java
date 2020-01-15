package sample;

import base.Database;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import static javafx.fxml.FXMLLoader.load;

public class Main extends Application {

    Stage window;

    Statement stmt = null;

    @SuppressWarnings("checkstyle:OperatorWrap")
    public void start(Stage primaryStage) throws Exception {
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "Employee", null);
        //* Δημιουργούμε τον πίνακα Employee εαν είναι πρώτη φορά που ανοίγει το πρόγραμμα
        if (!rs.next()) {
            String query = "CREATE TABLE 3C1QdEAmh9.Employee\n" +
                    "\t(idEmployee INT,\n" +
                    "\tfullname VARCHAR(50),\n" +
                    "\tdepartment VARCHAR(80),\n" +
                    "\tleaves INT,\n" +
                    "\toverall DECIMAL(5),\n" +
                    "\tfirstDate DATE,\n" +
                    "\tsalary DECIMAL(5),\n" +
                    "\temail VARCHAR(40),\n" +
                    "\tpassword VARCHAR(40),\n" +
                    "\tPRIMARY KEY (idEmployee));";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        rs = dbm.getTables(null, null, "Customers", null);
        //* Παρομοίως για τους πελάτες τους
        if (!rs.next()) {
            String query = "CREATE TABLE 3C1QdEAmh9.Customers\n" +
                    "\t(idBusiness INT NOT NULL,\n" +
                    "\tname VARCHAR(30),\n" +
                    "\tdate DATE,\n" +
                    "\tamount NUMERIC,\n" +
                    "\ttype VARCHAR(30),\n" +
                    "\tidEmployee INT,\n" +
                    "\tPRIMARY KEY (idBusiness),\n" +
                    "\tFOREIGN KEY (idEmployee) REFERENCES 3C1QdEAmh9.Employee (idEmployee)) ;";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        rs = dbm.getTables(null, null, "keepId", null);
        //* Παρομοίως για τους πελάτες τους
        if (!rs.next()) {
            String query = "create table 3C1QdEAmh9.keepId\n" +
                    "\t(id int,\n" + "    idEmployee int,\n" +
                    "    foreign key (idEmployee) references 3C1QdEAmh9.Employee (idEmployee))";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        rs = dbm.getTables(null, null, "leaves", null);
        //* Παρομοίως για τους πελάτες τους
        if (!rs.next()) {
            String query = "create table 3C1QdEAmh9.leaves\n" +
                    "\t\t(firstdate date,\n" +
                    "        lastdate date,\n" +
                    "        daysbetween int,\n" +
                    "        approved varchar(7),\n" +
                    "        idEmployee int,\n" +
                    "        checkApp int,\n" +
                    "        foreign key (idEmployee) references 3C1QdEAmh9.Employee (idEmployee));";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }

        if (!rs.next()) {
            String query1 = "ALTER TABLE   3C1QdEAmh9.Employee \n" +
                    "RENAME TO Employee";
            String query2 = "ALTER TABLE   3C1QdEAmh9.Customers \n" +
                    "RENAME TO Customers";
            String query3 = "ALTER TABLE   3C1QdEAmh9.keepId \n" +
                    "RENAME TO keepId";
            String query4 = "ALTER TABLE   3C1QdEAmh9.leaves \n" +
                    "RENAME TO leaves";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);
            stmt.executeUpdate(query4);
        }


        window = primaryStage;
        window.setOnCloseRequest(e -> {
            try {
                close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Parent root = load(getClass().getResource("sample.fxml"));
        window.setTitle("DETranet");
        window.setScene(new Scene(root, 1078, 590));
        window.setResizable(false);
        window.show();
    }

    public void close() throws SQLException {
        // όταν κλείνει το πρόγραμμα σαν παράθυρο διαγράφουμε την εγγραφή στον πίνακα keepid , καθώς δεν υπάρχει λογος να υπάρχει πλέον
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        String query = "delete from keepId\n" +
                "order by id desc limit 1";
        stmt = connection.createStatement();
        stmt.executeUpdate(query);

        window.close();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

