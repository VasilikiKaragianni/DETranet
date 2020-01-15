package sample;

import base.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ProgressIndicatorController implements Initializable {

    @FXML
    private ProgressIndicator pi;
    PreparedStatement ps = null;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getprogress(ActionEvent event) throws SQLException {
        Connection conn =  Database.createConnection();
        String query1 = "Select id from keepId";
        ps = conn.prepareStatement(query1);
        ResultSet rs1 = ps.executeQuery();
        int id = 1;
        if (rs1.next()) {
            id = rs1.getInt(1);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        // Υπολογίζεται το σύνολο των δανείων που έχει πουλησει ο πελάτης
        String query2 = "select SUM(amount) " +
                "from Customers  " +
                "where idEmployee = '"+id+"' AND year(date)='" +year+"'";
        ps = conn.prepareStatement(query2);
        ResultSet rs2 = ps.executeQuery();
        double sum = 0;

        if(rs2.next()){
            sum = rs2.getDouble(1);
        }
        String query3 = "Select department from Employee where idEmployee = '"+id+"'";
        ps = conn.prepareStatement(query3);
        ResultSet rs3 = ps.executeQuery();
        String dp = null;
        if(rs3.next()) {
            dp = rs3.getString(1);
        }
  // Παιρνούμε τον στόχο που έχει τεθεί για το department που ανήκει ο υπάλληλος
        String query4 = "SELECT target from Goals where department= '"+dp+"'";
        ps = conn.prepareStatement(query4);
        ResultSet rs4 = ps.executeQuery();

        double goals=0;
        if(rs4.next()) {
             goals=rs4.getDouble(1);

        }

        try {
            double progressValue = 0;
            if (goals == 0) {
                label.setText("Contact your Manager to set your Goal");
            } else {
                if (sum != 0) {
                    progressValue = sum / goals;
                }
                pi.setProgress(progressValue);
                if (progressValue > 1) {
                    label.setText("You have reached your goals, contact HR for your Bonus.");
                } else {
                    progressValue = (1 - progressValue) * 100;
                    label.setText("You still have " + progressValue + "% left to reach your goals.");
                }
            }
            } catch(ArithmeticException e){

            }
        conn.close();
        }

    }

