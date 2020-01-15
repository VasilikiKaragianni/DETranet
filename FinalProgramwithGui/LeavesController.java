package sample;

import base.Database;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.jca.GetInstance;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;

public class LeavesController implements Initializable {

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

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

    private ObservableList<LeavesList>data;

    PreparedStatement ps = null;
    @FXML
    public void loadLeaves(ActionEvent actionEvent) throws SQLException , IOException {
        error.setText("");
        Connection conn = Database.createConnection();

        String query = "Select id from keepId";
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int id = 1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        String sql;
        try {
            data = FXCollections.observableArrayList();
            sql = "SELECT  firstdate, lastdate,daysbetween, approved from leaves where idEmployee = '"+id+"'";
            ps = conn.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                data.add(new LeavesList(rs1.getString(1),rs1.getString(2), rs1.getString(3), rs1.getString(4)));
            }
        } catch (SQLException ex){
            System.err.println("Error " + ex);
        }
        try {
            columnSD.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            columnED.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            columnDays.setCellValueFactory(new PropertyValueFactory<>("days"));
            columnApproved.setCellValueFactory(new PropertyValueFactory<>("approved"));
        } catch (NullPointerException e){

        }

        try {
            tableLeaves.setItems(null);
            tableLeaves.setItems(data);
        } catch (NullPointerException e) {

        }
        conn.close();


    }

    @FXML
    private JFXTextField error;

   @FXML
    void requestLeave(ActionEvent actionEvent) throws SQLException {
       error.setText("");
        Connection conn = Database.createConnection();

        String query1 = "Select id from keepId";
        ps = conn.prepareStatement(query1);
        ResultSet rs1 = ps.executeQuery();
        int id = 1;
        if (rs1.next()) {
            id = rs1.getInt(1);
        }


        try {

            PreparedStatement ps1 = conn.prepareStatement("INSERT into leaves(firstdate, lastdate, daysbetween , approved , idEmployee, checkApp) values (?,?,?,?,?,?)");
            long daysb = ChronoUnit.DAYS.between(startDate.getValue(), endDate.getValue());
            int daysbetween = Math.toIntExact(daysb);
            //ps1.setDate(1, java.sql.Date.valueOf(startDate.getValue()));
            //ps1.setDate(2, java.sql.Date.valueOf(endDate.getValue()));
            ps1.setString(1,startDate.getValue().toString());
            ps1.setString(2,endDate.getValue().toString());
            ps1.setInt(3,daysbetween);
            ps1.setString(4, "pending");
            ps1.setInt(5, id);
            ps1.setInt(6, 0);



            String query2 = "SELECT  daysbetween, idEmployee , checkApp , approved  from leaves where idEmployee = '"+id+"'";
            ps = conn.prepareStatement(query2);
            ResultSet rs3 = ps.executeQuery();
            boolean exLeave = true;
            if (rs3.last()){
                String approvedV = rs3.getString(4);
                int checked = rs3.getInt(3);
                exLeave = false;
                if(approvedV.equals("yes") && checked == 0){
                    String sql1 = "SELECT leaves from Employee where idEmployee = '" + id + "'";
                    ps = conn.prepareStatement(sql1);
                    ResultSet rs5 = ps.executeQuery();
                    int oldLeaves = 31;
                    if (rs5.next()){
                        oldLeaves = rs5.getInt(1);
                    }

                    int reqDays = rs3.getInt(1);
                    String sql2 = "update Employee set leaves = '"+oldLeaves+"' - '"+reqDays+"' where idEmployee = '" + id + "'";
                    ps = conn.prepareStatement(sql2);
                    ps.executeUpdate();
                    String sql3 = "update leaves set checkApp = 1 where idEmployee = '" + id + "'";
                    ps = conn.prepareStatement(sql3);
                    ps.executeUpdate();
                }
            }


            boolean pRequests = false;

            if(!exLeave) {
                String query3 = "Select  approved  from leaves where idEmployee = '" + id + "'";
                ps = conn.prepareStatement(query3);
                ResultSet rs4 = ps.executeQuery();
                String appRequests = null;
                if (rs4.last()) {
                    appRequests = rs4.getString(1);
                }

                if (appRequests.contains("pending") ){
                    pRequests = true;
                }


            }


            String sql = "Select leaves  from Employee where idEmployee = '"+id+"' ";
            ps = conn.prepareStatement(sql);
            ResultSet rs2 = ps.executeQuery();
            int leavesLeft = 0;
            if (rs2.next()){
                leavesLeft = rs2.getInt(1);
            }
            long days = ChronoUnit.DAYS.between(startDate.getValue(), endDate.getValue());
            int days1 = Math.toIntExact(days);

            if(leavesLeft >= days1 && pRequests == false ){
                if (startDate.getValue().isAfter(LocalDate.now()) && startDate.getValue().isBefore(endDate.getValue())) {
                    ps1.executeUpdate();
                }else {
                    error.setText("Please enter  valid dates");
                }
            } else if (leavesLeft <= days1 && pRequests == false){
                error.setText("You don't have so many days left");
            }else {
                error.setText("You have another request pending");
            }
        } catch (Exception e){

        }
        conn.close();

    }


    @FXML
    void deleteRequest(ActionEvent event) throws SQLException {
        error.setText("");
        LeavesList leavesList = tableLeaves.getSelectionModel().getSelectedItem();

        try {
            String startDate = leavesList.getStartDate();
            String endDate = leavesList.getEndDate();
            String approved = leavesList.getApproved();
            Connection conn = Database.createConnection();
            if(approved.equals("pending")) {
                String sql = "Delete from leaves where firstdate = '"+startDate+"' and lastdate ='"+endDate+"' and approved = '"+approved+"'";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                tableLeaves.getItems().removeAll(tableLeaves.getSelectionModel().getSelectedItem());
            } else {
                error.setText("You can't delete approved requests");
            }
            conn.close();
        } catch (NullPointerException e){

        }




    }





        @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



}
