package sample;



import base.Database;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class SetGoalController implements Initializable {



    @FXML
    private JFXTextField error2;
    @FXML
    private JFXTextField target;

    @FXML
    private JFXComboBox<String> depa;

    ObservableList<String> list = FXCollections.observableArrayList("Customer Manager", "Deposit Manager", "Loan Manager", "Service Manager", "Teller", "Store Manager"
    );



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            depa.setItems(list);
        } catch (NullPointerException ignored) {

        }
    }

    public void getGoals(ActionEvent event) throws SQLException {
        error2.setText("");
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "Goals", null);
        //* Δημιουργούμε τον πίνακα στόχους για τους υπαλλήλους αφού πρώτα δουμε οτι δεν υπάρχει ηδη
        if (!rs.next()) {
            String query = "create table 3C1QdEAmh9.Goals (\n" +
                    "Department varchar(50),\n" +
                    "Target real\n" +
                    " )";
            String query1 = "ALTER TABLE   3C1QdEAmh9.Goals \n" +
                    "RENAME TO Goals";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query1);
            try {
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
        String department = depa.getValue();
        String target1 = target.getText();
        boolean empty = true;
        try {
            empty = department.isEmpty() || target1.isEmpty();
        } catch (NullPointerException e){

        }

        if (empty) {
            error2.setText("Please , Type in all Fields");
        } else {
            connectionClass = new Database();
            connection = connectionClass.createConnection();
            try {
                String s = depa.getValue();
                Statement stmt = connection.createStatement();
                // Ο Manager θέτει στόχο για τον κάθε department
                String query = "UPDATE Goals\n" +
                        "SET Target =+'" + Integer.parseInt(target.getText()) + "'WHERE Goals.Department = '" + s + "';";

                stmt.executeUpdate(query);
                error2.setText("Goal has been set.");
                error2.setStyle("-fx-text-fill:  #15bcd8;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                error2.setText("Please input number in field goal!");
            }
        }
        connection.close();
    }

}


