package sample;

import base.Database;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class StoreMaController implements Initializable {

    @FXML
    private JFXTextField error;

    @FXML
    private JFXTextField error2;

    @FXML
    private TableView<LeavesList> tableLeaves;

    @FXML
    private TableColumn<LeavesList, Date> columnSD;

    @FXML
    private TableColumn<LeavesList, Date> columnED;

    @FXML
    private TableColumn<LeavesList, Integer> columnDays;

    @FXML
    private TableColumn<LeavesList, String> columnApproved;

    private ObservableList<LeavesList> data;

    PreparedStatement ps = null;
    /*Μέθοδος για την ανανέωση του πίνακα των αδειών των υπαλλήλων*/
    @FXML
    public void loadLeaves(ActionEvent actionEvent) throws SQLException, IOException {
        error.setText("");
        error2.setText("");
        Connection conn = Database.createConnection();

        String sql;
        try {
            data = FXCollections.observableArrayList();
            sql = "SELECT  firstdate, lastdate,daysbetween, approved from leaves ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                data.add(new LeavesList(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
        try {
            columnSD.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            columnED.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            columnDays.setCellValueFactory(new PropertyValueFactory<>("days"));
            columnApproved.setCellValueFactory(new PropertyValueFactory<>("approved"));
        } catch (NullPointerException e) {

        }

        try {
            tableLeaves.setItems(null);
            tableLeaves.setItems(data);
        } catch (NullPointerException e) {

        }
        conn.close();


    }
    /*Μέθοδος για την διαγραφή μη εγκεκριμένων αδειών από τον manager*/
    @FXML
    void deleteRequest(ActionEvent event) throws SQLException {
        error.setText("");
        error2.setText("");
        LeavesList leavesList = tableLeaves.getSelectionModel().getSelectedItem();

        try {
            String startDate = leavesList.getStartDate();
            String endDate = leavesList.getEndDate();
            String approved = leavesList.getApproved();
            Connection conn = Database.createConnection();

            if (approved.equals("pending")) {
                String sql = "Delete from leaves where firstdate = '" + startDate + "' and lastdate ='" + endDate + "' and approved = '" + approved + "'";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                tableLeaves.getItems().removeAll(tableLeaves.getSelectionModel().getSelectedItem());
            } else {
                error.setText("You can't delete approved requests");
            }
            conn.close();
        } catch (NullPointerException e) {

        }
    }


    @Override
    public void initialize (URL location, ResourceBundle resources){

        columnApproved.setCellFactory(TextFieldTableCell.forTableColumn());
        tableLeaves.setEditable(true);
        columnApproved.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<LeavesList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<LeavesList, String> event) {
                LeavesList approve = tableLeaves.getSelectionModel().getSelectedItem();
                approve.setApproved(event.getNewValue());
                Connection conn = Database.createConnection();
                try {
                    LeavesList a = event.getRowValue();
                    String f = a.getStartDate();
                    String l = a.getEndDate();
                    String sql1 = "SELECT idEmployee,approved from leaves where  firstdate = '" + f + "' and lastdate = '" + l + "'";
                    ps = conn.prepareStatement(sql1);
                    ResultSet rs1 = ps.executeQuery();
                    int id = 0;
                    String app = null;
                    if (rs1.first()) {
                        id = rs1.getInt(1);
                        app = rs1.getString(2);
                    }

                        if (app.equals("pending")) {
                            if (event.getNewValue().equals("yes") || event.getNewValue().equals("no")) {
                                String sql2 = "update leaves set approved = '" + event.getNewValue() + "' where idEmployee = '" + id + "' ";
                                ps = conn.prepareStatement(sql2);
                                ps.executeUpdate();
                            } else {
                                error.setText("Please type yes or no");
                            }
                        } else {
                            error.setText("You can only change pending");
                            error2.setText("leaves");
                        }


                } catch (SQLException e ) {
                        e.printStackTrace();
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

