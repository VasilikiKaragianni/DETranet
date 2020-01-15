
package sample;

import base.Database;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import static javafx.fxml.FXMLLoader.load;


public class Controller implements Initializable {
    /**
     * text field.
     **/
    @FXML
    private JFXTextField error2;
    /**
     * label.
     **/
    @FXML
    private Label name;
    /**
     * label.
     **/
    @FXML
    private Label depart;
    /**
     * creating PreparedStatement with the name ps.
     **/
    private PreparedStatement ps = null;
    /**
     * text field.
     **/
    @FXML
    private JFXTextField email;
    /**
     * Password field.
     **/
    @FXML
    private JFXPasswordField pass1;
    /**
     * Password field.
     **/
    @FXML
    private JFXPasswordField pass2;
    /**
     * Datepicker field.
     **/
    @FXML
    private JFXDatePicker date;
    /**
     * text field.
     **/
    @FXML
    private JFXTextField fullname;
    /**
     * Combobox field.
     **/
    @FXML
    private ComboBox<String> comboBox;
    /**
     * List that used as options of combo box.
     **/
    ObservableList<String> list = FXCollections.observableArrayList(
            "Customer Manager", "Service Manager",
            "Deposit Manager", "Loan Manager", "Teller", "Store Manager"
    );

    /**
     * main border pane.
     **/
    @FXML
    private BorderPane borderpane;

    /**
     * button used to change border pane.
     **/
    @FXML
    void employees(final MouseEvent event) throws SQLException {
        loadUi("employees");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void customers(final MouseEvent event) throws SQLException {
        loadUi("customers");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void bonus(final MouseEvent event) throws SQLException {
        loadUi("ProgressIndicator");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void leaves(final MouseEvent event) throws SQLException {
        loadUi("leaves");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void news(final MouseEvent event) throws SQLException {
        loadUi("news");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void efficiency(final MouseEvent event) throws SQLException {
        loadUi("Efficiency");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void email(final MouseEvent event) throws SQLException {
        loadUi("Email");
    }

    /**
     * button used to change border pane.
     **/
    @FXML
    void setGoals(final MouseEvent event) throws SQLException {
        loadUi("Rejection");
    }

    /**
     * giving null value at con variable.
     **/
    private Connection con = null;
    /**
     * giving null value at preparedStatement variable.
     **/
    private PreparedStatement preparedStatement = null;
    /**
     * giving null value at resultSet variable.
     **/
    private ResultSet resultSet = null;
    /**
     * text field.
     **/
    @FXML
    private JFXTextField logemail;
    /**
     * Password field.
     **/
    @FXML
    private JFXPasswordField logpass;
    /**
     * text field.
     **/
    @FXML
    private JFXTextField error1;


    //Method that opens a specific fxml file when the user press a button
    private void loadUi(final String ui) throws SQLException {

        Connection conn = Database.createConnection();
        String query1 = "Select id from keepId";
        ps = conn.prepareStatement(query1);
        ResultSet rs1 = ps.executeQuery();
        int id = 1;
        if (rs1.next()) {
            id = rs1.getInt(1);
        }
        String query2 = "SELECT department from Employee where idEmployee ="
                + "'" + id + "'";
        ps = conn.prepareStatement(query2);
        ResultSet rs2 = ps.executeQuery();
        String dp = null;
        if (rs2.next()) {
            dp = rs2.getString(1);
        }

        Parent root = null;
        try {
            if (dp.equals("Store Manager")) {
                if (ui.equals("leaves")) {
                    root = load(getClass().getResource(
                            "StoreManagerLeaves.fxml"));
                } else if (ui.equals("Rejection")) {
                    root = load(getClass().getResource("Set goals.fxml"));
                } else {
                    root = load(getClass().getResource(ui + ".fxml"));
                }
            } else {
                root = load(getClass().getResource(ui + ".fxml"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            borderpane.setCenter(root);
        } catch (NullPointerException e) {

        }
        conn.close();
    }


    /**
     * method that leads the user to main window.
     **/
    public void gotoMainWindow1(final ActionEvent event)
            throws IOException, SQLException {
        String status = creds();

        if (status.equals("Success")) {

            Parent tableViewParent = load(getClass().getResource("MainWindow.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }

    }

/*Αυτη η μέθοδο; ελέγχει αν τα στοιχεία που βάζει ο χρήστση στο log in  είναι έγκυρα*/
    public String creds() throws IOException, SQLException {
        String email = logemail.getText();
        String password = logpass.getText();
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        String sql = "SELECT * FROM Employee Where email = ? and password = ?";
        String status = "Success";
        if (email.isEmpty() || password.isEmpty()) {

            error1.setText("Email or Password is Missing");

            status = "Error";
        } else {
            //query
            sql = "SELECT * FROM Employee Where email = ? and password = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    error1.setText("Wrong Email or Password");
                    status = "Error";
                } else {

                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }

        }


        String query = "Select idEmployee from Employee where email = '" + email + "' and password = '" + password + "'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            int Id = rs.getInt(1);
            PreparedStatement ps = connection.prepareStatement("Insert into keepId(id,idEmployee) values (?,?)");
            ps.setInt(1, Id);
            ps.setInt(2, Id);
            ps.executeUpdate();
        }
        connection.close();
        return status;
    }

    /*Αυτή η μέθοδος χρησιμοποιείται για να ανοίξει το παράθυρο του log in*/
    public void GotoLogIn(ActionEvent event) throws IOException {
        Parent tableViewParent = load(getClass().getResource("Log-in.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    /*Αυτή η μέθοδος χρησιμοποιείται για να ανοίξει το παράθυρο του Sign up*/
    public void GotoSignUp(ActionEvent event) throws IOException {
        Parent tableViewParent = load(getClass().getResource("Sign up form.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    /*Αυτή η μέθοδο επιστρέφει  τον μισθό του κάθε υπαλλήλου με βάση το τμήμα του*/
    public static double getSalary(String department) {
        double salary;

        switch (department) {
            case "Customer Manager":
                salary = 1800;
                break;
            case "Service Manager":
                salary = 2100;
                break;
            case "Deposit Manager":
                salary = 2150;
                break;
            case "Loan Manager":
                salary = 2050;
                break;
            case "Teller":
                salary = 850;
                break;
            case "Store Manager":
                salary = 4500;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + department);
        }
        return salary;

    }

    /*Με αυτή τη μέθοδο υλοποιείται η εγγραφή του υπαλλήλου*/
    public void signed(ActionEvent actionEvent) throws SQLException {

        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();

        boolean Empty = true;
        try {
            if (date.getValue() != null) {
                Empty = email.getText().isEmpty() || pass1.getText().isEmpty() || pass2.getText().isEmpty() || comboBox.getValue().isEmpty() || fullname.getText().isEmpty();
            }
        } catch (NullPointerException e) {

        }

        if (pass1.getText().equals(pass2.getText()) && !Empty) {

            try {

                String query = "select max(idEmployee)" +
                        "from Employee";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                int id1 = 0;
                String id = "0";
                try {
                    if (rs.next()) {
                        id = rs.getString(1);
                        if (!id.equals("null")) {
                            id1 = Integer.parseInt(id) + 1;
                        }
                    }
                } catch (NullPointerException e) {

                }

                double overall = 0;
                int leaves = 31;

                PreparedStatement posted = connection.prepareStatement("INSERT INTO Employee (idEmployee, fullName, department, leaves, overall, firstDate, salary, email, password) VALUES (?,?,?,?,?,?,?,?,?)");
                posted.setInt(1, id1);
                posted.setString(2, fullname.getText());
                posted.setString(3, comboBox.getValue());
                posted.setInt(4, leaves);
                posted.setDouble(5, overall);
                posted.setDate(6, Date.valueOf(date.getValue()));
                posted.setDouble(7, getSalary(comboBox.getValue()));
                posted.setString(8, email.getText());
                posted.setString(9, pass1.getText());
                posted.executeUpdate();
                Parent tableViewParent = load(getClass().getResource("Log-in.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (Empty) {

            error2.setText("Please type in all fields");

        } else if (!pass1.getText().equals(pass2.getText())) {

            error2.setText("Passwords do not Match");

        }
        connection.close();
    }
    /*Αυτή η μέθοδος καλείται κατά την αποσύνδεση του εκάστοτε υπαλλήλου, σε επιστρέφει στο μενού του log in
     και διαγράφει κάποια συγκεκριμένα στοιχεία απο την βάση*/
    public void logOut(ActionEvent event) throws IOException, SQLException {
        Connection conn1 = Database.createConnection();
        String query = "Select id from keepId";
        preparedStatement = conn1.prepareStatement(query);
        ResultSet rs1 = preparedStatement.executeQuery();
        int id = 1;
        if (rs1.next()) {
            id = rs1.getInt(1);
        }

        String query2 = "Delete from keepId where id = '" + id + "'";
        PreparedStatement ps1 = conn1.prepareStatement(query2);
        ps1.executeUpdate();

        Parent tableViewParent = load(getClass().getResource("Log-in.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        conn1.close();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            comboBox.setItems(list);
        } catch (NullPointerException ignored) {

        }

        try {
            Connection conn = Database.createConnection();
            String query = "Select id from keepId";
            int id = 1;

            ps = conn.prepareStatement(query);
            ResultSet rs1 = ps.executeQuery();

            if (rs1.next()) {
                id = rs1.getInt(1);
            }

            String query2 = "SELECT fullname , department from Employee where idEmployee = '" + id + "' ";
            String fn = null;
            String dp = null;

            ps = conn.prepareStatement(query2);
            ResultSet rs2 = ps.executeQuery();
            if (rs2.next()) {
                fn = rs2.getString(1);
                dp = rs2.getString(2);
            }

            try {
                name.setText(fn);
                depart.setText(dp);
            } catch (NullPointerException e) {

            }
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        Parent root = null;
        try {

            root = load(getClass().getResource("Welcome.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            borderpane.setCenter(root);
        } catch (NullPointerException e) {

        }
    }

}
