package sample;
import base.Database;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {


    @FXML
    private JFXTextField name;

    @FXML
    private JFXDatePicker dateofloan;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXComboBox type;

    @FXML
    private TableView<CustomersList> tableCustomers;

    @FXML
    private TableColumn<CustomersList, Integer> columnId;

    @FXML
    private TableColumn<CustomersList, String> columnName;

    @FXML
    private TableColumn<CustomersList, java.util.Date> columnDate;

    @FXML
    private TableColumn<CustomersList, Double> columnAmount;

    @FXML
    private TableColumn<CustomersList, String> columnType;



    private ObservableList<CustomersList>data;


    PreparedStatement preparedStatement = null;

    ObservableList<String> list = FXCollections.observableArrayList("Private", "Business");


    /*Αυτή η μέθοδος ανανεώνει τον πίνακα των πελατών με τα στοιχεία που υπάρχουν στη βάση*/
    @FXML
    public  void loadCustomers(ActionEvent event) throws SQLException, IOException {

        Connection conn = Database.createConnection();
        String query = "Select id from keepId";
        preparedStatement = conn.prepareStatement(query);
        ResultSet rs1 = preparedStatement.executeQuery();
        int id = 1;
        if (rs1.next()) {
            id = rs1.getInt(1);
        }


        String sql;
        try {

            data = FXCollections.observableArrayList();
            assert conn != null;
            sql = "SELECT * FROM Customers   where idEmployee = '" + id + "' ";
            preparedStatement = conn.prepareStatement(sql);
                    ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                data.add(new CustomersList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        try {
            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            columnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            columnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        } catch (NullPointerException e) {

        }
        try {
            tableCustomers.setItems(null);
            tableCustomers.setItems(data);
        } catch (NullPointerException e) {

        }
        conn.close();
    }

    @FXML
    private JFXTextField error;
    
    /*Αυτη η μέθοδος προσθέτει πελάτες με δεδομένα που δίνει ο εκάστοτε υπάλληλος*/
    @FXML
    void addCustomer(ActionEvent event) throws SQLException {
        error.setText("");
        Connection conn = Database.createConnection();
        String query1 = "Select id from keepId";
        preparedStatement = conn.prepareStatement(query1);
        ResultSet rs = preparedStatement.executeQuery();
        int empId = 1;
        if (rs.next()) {
            empId = rs.getInt(1);
        }
        boolean empty = true;
        try {
            if (dateofloan.getValue() != null && type.getValue() != null) {
                empty = name.getText().isEmpty() || amount.getText().isEmpty();
            }
        } catch (NullPointerException e){

        }
        try {
            if (!empty ) {
                String query = "select max(idBusiness) from Customers";
                Statement stmt = conn.createStatement();
                ResultSet rs1 = stmt.executeQuery(query);

                int id1 = 1;
                String id = "1";
                try {
                    if (rs1.next()) {
                        id = rs1.getString(1);
                        if (!id.equals("null")) {
                            id1 = Integer.parseInt(id) + 1;
                        }
                    }
                } catch (NullPointerException e){

                }
                try {
                    System.out.println(id1);
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO Customers(idBusiness, name , date, amount , type, idEmployee) values (?,?,?,?,?,?)");
                    ps.setInt(1, id1);
                    ps.setString(2, name.getText());
                    ps.setDate(3, Date.valueOf((dateofloan.getValue())));
                    ps.setDouble(4, Double.parseDouble(amount.getText()));
                    ps.setString(5, type.getValue().toString());
                    ps.setInt(6, empId);
                    ps.executeUpdate();
                }  catch (NumberFormatException e) {
                                error.setText("Please input number in field amount.");
                            }
            } else {
                error.setText("Please type in all fields");
            }

        }catch(Exception e){

        }
        conn.close();


    }
    /*Αυτή η μέθοδος διαγράφει τον επιλεγμένο υπάλληλο*/
    @FXML
    void DeleteCustomer(ActionEvent event) throws SQLException {
        CustomersList customersList = tableCustomers.getSelectionModel().getSelectedItem();
        int SelectedId = 0;
        try {
            String item = customersList.getId();

            SelectedId = Integer.parseInt(item);

        } catch (NullPointerException e){

        }
        Connection conn = Database.createConnection();
        String sql = "Delete from Customers where idBusiness = '"+SelectedId+"'";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        tableCustomers.getItems().removeAll(tableCustomers.getSelectionModel().getSelectedItem());
        conn.close();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            type.setItems(list);
        } catch (NullPointerException ignored) {

        }

    }
}

