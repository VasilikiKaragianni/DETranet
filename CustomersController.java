package sample;
import base.Database;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {
    @FXML
    private TableView<CustomersList> tableCustomers;

    @FXML
    private TableColumn<CustomersList, Integer> columnId;

    @FXML
    private TableColumn<CustomersList, String> columnName;

    @FXML
    private TableColumn<CustomersList, Integer> columnLoans;

    @FXML
    private TableColumn<CustomersList, Double> columnAmount;

    @FXML
    private TableColumn<CustomersList, String> columnType;

    @FXML
    private JFXButton btnLoad;

    private ObservableList<CustomersList>data;
    private Database db;

    @FXML
    void loadCustomers(ActionEvent event) throws SQLException {
        try {
            Connection conn = Database.createConnection();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Business");
            while (rs.next()) {
                data.add(new CustomersList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLoans.setCellValueFactory(new PropertyValueFactory<>("loans"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableCustomers.setItems(null);
        tableCustomers.setItems(data);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = new Database();
    }
}
