package sample;

import base.Database;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.Date;

public class EmployeesController {


    @FXML
    private TableView<EmployeesList> tableEmployees;


    @FXML
    private TableColumn<EmployeesList, Integer> columnId;

    @FXML
    private TableColumn<EmployeesList, String> columnFullName;

    @FXML
    private TableColumn<EmployeesList, String> columnDepartment;

    @FXML
    private TableColumn<EmployeesList, Date> columnDateofHire;

    @FXML
    private TableColumn<EmployeesList, String> columnEmail;

    @FXML
    private JFXTextField error;

    @FXML
    private JFXTextField error1;

    private ObservableList<EmployeesList> data;

    /*Μεθοδος για την διαγραφή του επιλεγμένου υπαλλήλου*/
    @FXML
    void DeleteEmployee(ActionEvent event) throws SQLException {
        error.setText("");
        error1.setText("");

        EmployeesList employeesList = tableEmployees.getSelectionModel().getSelectedItem();
        int SelectedId = 0;
        try {
            String item = employeesList.getId();

            SelectedId = Integer.parseInt(item);

        } catch (NullPointerException e) {

        }

        Connection conn = Database.createConnection();
        String query1 = "Select id from keepId";
        ps = conn.prepareStatement(query1);
        ResultSet rs1 = ps.executeQuery();
        int id = 1;
        if (rs1.next()) {
            id = rs1.getInt(1);
        }

        String query2 = "SELECT department from Employee where idEmployee = '" + id + "' ";
        ps = conn.prepareStatement(query2);
        ResultSet rs2 = ps.executeQuery();
        String dp = null;
        if (rs2.next()) {
            dp = rs2.getString(1);
        }
        if (dp.equals("Store Manager")) {
            if (SelectedId != id) {
                String sql = "Delete from  Customers where idEmployee = '" + SelectedId + "'";
                String sql2 = "Delete from leaves  where idEmployee = '" + SelectedId + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps1 = conn.prepareStatement(sql2);
                ps.executeUpdate();
                ps1.executeUpdate();
                String sql1 = "Delete from Employee  where idEmployee = '" + SelectedId + "'";
                ps = conn.prepareStatement(sql1);
                ps.executeUpdate();
                tableEmployees.getItems().removeAll(tableEmployees.getSelectionModel().getSelectedItem());


            } else {
                error1.setText("You can't delete the store ");
                error.setText("manager");
            }
        } else {
            error1.setText("Only store manager can");
            error.setText("delete employees");
        }
        conn.close();
    }

    PreparedStatement ps = null;
    /*Μέθοδος για την ανανέωση του πίνακα των υπαλλήλων*/
    @FXML
    void loadEmployees(ActionEvent event) throws SQLException {
        Connection conn = Database.createConnection();
        String sql = "SELECT idEmployee, fullName, department, firstdate, email from Employee ";
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        data = FXCollections.observableArrayList();
        while (rs.next()) {
            data.add(new EmployeesList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        try {
            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnFullName.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
            columnDepartment.setCellValueFactory(new PropertyValueFactory<>("Department"));
            columnDateofHire.setCellValueFactory(new PropertyValueFactory<>("firstDate"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        } catch (NullPointerException e) {

        }
        try {
            tableEmployees.setItems(null);
            tableEmployees.setItems(data);
        } catch (NullPointerException e) {

        }
        conn.close();

    }

}
