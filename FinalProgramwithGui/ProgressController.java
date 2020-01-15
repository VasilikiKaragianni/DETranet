package sample;


import base.Database;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.TouchEvent;
import javafx.event.ActionEvent;
import java.sql.*;
import java.sql.*;


public class ProgressController {
    @FXML
    private Label note;
    public Statement stmt = null;

    @FXML
    private JFXComboBox<String> depa;

    ObservableList<String> list = FXCollections.observableArrayList(
            "BusinessCustomerManager", "BusinessCustomerManagerDelays", "BusinessCustomerManagerVip", "BusinessServiceManager",
            "DepositManager", "LoanManager", "PrivateCustomerManager", "PrivateCustomerManagerDelays", "PrivateCustomerManagerVip", "Teller", "Store Manager");

    public void note(ActionEvent event) {
        note.setText("Set goals only for store manager");
    }


    public void seegoals(ActionEvent event) throws SQLException {
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "Goals", null);
        //* Δημιουργούμε τον πίνακα στόχους για τους υπαλλήλους αφού πρώτα δουμε οτι δεν υπάρχει ηδη
        if (!rs.next()) {
            String query = "create table Goals (\n" +
                    "Department varchar(50),\n" +
                    "Target int\n" +
                    " )";
            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(query);
                stmt.executeUpdate("INSERT INTO Goals(Department,Target) VALUES ('Customer Manager',0);");
                stmt.executeUpdate("INSERT INTO Goals(Department,Target) VALUES ('Deposit Manager',0);");
                stmt.executeUpdate(" INSERT INTO Goals(Department,Target) VALUES ('Loan Manager',0);");
                stmt.executeUpdate("INSERT INTO Goals(Department,Target) VALUES ('Service Manager',0);");
                stmt.executeUpdate("INSERT INTO Goals(Department,Target) VALUES ('Teller',0);");
                stmt.executeUpdate("INSERT INTO Goals(Department,Target) VALUES ('Store Manager',0);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection.close();


    }


    public void seeprogress(ActionEvent event) throws SQLException {
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "Sums", null);
        //*  Για να δούμε την πρόοδο του κάθε υπαλλήλου φτιάχνουμε πίνακα στην βάση με τα συνολικά ποσά του αφού πρώτα ελένξουμε οτι δεν υπάρχει
        if (!rs.next()) {
            String query = "create table Sums as\n" +
                    "select  SUM(amount) as Sum,idEmployee \n" +
                    "from customers\n" +
                    "where year(date)=2020 \n" +
                    "group by idEmployee";
        }



    }


}
