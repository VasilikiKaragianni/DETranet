package sample;


import base.Database;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class EfficiencyController implements Initializable {

    @FXML
    private Label eff1;
    @FXML
    LineChart<String, Number> chart;
    @FXML
    private JFXButton butoon1;

    @FXML
    private JFXButton current;

    PreparedStatement ps = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connection conn =  Database.createConnection();
        String query1 = "Select id from keepId";
        int id = 1;
        try {
            ps = conn.prepareStatement(query1);
            ResultSet rs1 = ps.executeQuery();

            if (rs1.next()) {
                id = rs1.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        //* Δημιουργούμε πίνακα στην βάση με τον μέσο ώρο της αξίας των δανείων ανα μήνα όλων των πελατών , αλλα καί του πελάτη με τον οποίο έχουμε
        //* log in  και τους εισάγουμε στο chart \*
        String query = " create table avge as " +
                " select  month(date) as μηνας, avg(amount) as avg" +
                " from Customers as p" +
                " where p.idEmployee = '"+id+"'  AND year(date) = " + year +
                " group by month(p.date)";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        try {
            series.getData().add(new XYChart.Data<String, Number>("Jan", Avg("1", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Feb", Avg("2", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Mar", Avg("3", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Apr", Avg("4", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("May", Avg("5", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Jun", Avg("6", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Jul", Avg("7", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Aug", Avg("8", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Sep", Avg("9", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Oct", Avg("10", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Nov", Avg("11", "avge")));
            series.getData().add(new XYChart.Data<String, Number>("Dec", Avg("12", "avge")));
            series.setName("Employee Average");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query3 = " create table avg1 as " +
                " select  month(date) as μηνας, sum(amount)/count(distinct idBusiness) as avg" +
                " from Customers as p" +
                " where year(date) = " + year +
                " group by month(p.date)";
        stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        try {
            series1.getData().add(new XYChart.Data<String, Number>("Jan", Avg("1", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Feb", Avg("2", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Mar", Avg("3", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Apr", Avg("4", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("May", Avg("5", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Jun", Avg("6", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Jul", Avg("7", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Aug", Avg("8", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Sep", Avg("9", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Oct", Avg("10", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Nov", Avg("11", "avg1")));
            series1.getData().add(new XYChart.Data<String, Number>("Dec", Avg("12", "avg1")));
            series1.setName("Store Average");
            chart.getData().addAll(series, series1);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        String query2 = "drop table avge";
        String query4 = "drop table avg1";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    eff1.setText(("The chart currently displays your performance in comparison to the average employee."));
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int Avg(String s, String name) throws SQLException {
        //* μέθοδος η οποία παίρνει το όνομα πίνακα και τον μήνα σε αριθμό 1-12 και επιστρέφει την αξία ,
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        String query = " select avg" + " from " + name + " where μηνας=" + s;
        Statement stmt = null;
        int amount = 0;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
                amount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return amount;
    }

    public void Average(ActionEvent event) throws SQLException {
        //* μέθοδος για την εμφάνιση  του μέσου όρου του καταστήματος
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String query = " create table avg1 as " +
                " select  month(date) as μηνας, sum(amount)/count(distinct idBusiness) as avg" +
                " from Customers as p" +
                " where year(date) = " + year +
                " group by month(p.date)";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", Avg("1", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Feb", Avg("2", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Mar", Avg("3", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Apr", Avg("4", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("May", Avg("5", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Jun", Avg("6", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Jul", Avg("7", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Aug", Avg("8", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Sep", Avg("9", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Oct", Avg("10", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Nov", Avg("11", "avg1")));
        series.getData().add(new XYChart.Data<String, Number>("Dec", Avg("12", "avg1")));
        chart.getData().add(series);
        series.setName("Average Employee");
        String query2 = "drop table avg1";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        eff1.setText(("The chart currently displays  the average employee's performance from the beginning of the year."));
        connection.close();
    }

    public void currentemployee(ActionEvent event) throws SQLException {

        Connection conn =  Database.createConnection();
        String query1 = "Select id from keepId";
        int id = 1;
        try {
            ps = conn.prepareStatement(query1);
            ResultSet rs1 = ps.executeQuery();

            if (rs1.next()) {
                id = rs1.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //*  Μέθοδος ώστε οταν πατηθεί το κουμπί να εμφανιστεί γράφημα μόνο του υπαλληλού που έχει κανει login
        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String query = " create table avge as " +
                " select  month(date) as μηνας, avg(amount) as avg" +
                " from Customers as p" +
                " where p.idEmployee = '"+id+"'  AND year(date) = " + year +
                " group by month(p.date)";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", Avg("1", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Feb", Avg("2", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Mar", Avg("3", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Apr", Avg("4", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("May", Avg("5", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Jun", Avg("6", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Jul", Avg("7", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Aug", Avg("8", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Sep", Avg("9", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Oct", Avg("10", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Nov", Avg("11", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Dec", Avg("12", "avge")));
        chart.getData().add(series);
        series.setName("Employee");
        String query2 = "drop table avge";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        eff1.setText(("The chart currently displays  your performance from the beginning of the year."));
        conn.close();
        connection.close();
    }

    public void compare(ActionEvent event) throws SQLException {
        Connection conn =  Database.createConnection();
        String query1 = "Select id from keepId";
        int id = 1;
        try {
            ps = conn.prepareStatement(query1);
            ResultSet rs1 = ps.executeQuery();

            if (rs1.next()) {
                id = rs1.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database connectionClass = new Database();
        Connection connection = connectionClass.createConnection();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String query = " create table avge as " +
                " select  month(date) as μηνας, avg(amount) as avg" +
                " from Customers as p" +
                " where p.idEmployee = '"+id+"' AND year(date) = " + year +
                " group by month(p.date)";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", Avg("1", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Feb", Avg("2", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Mar", Avg("3", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Apr", Avg("4", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("May", Avg("5", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Jun", Avg("6", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Jul", Avg("7", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Aug", Avg("8", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Sep", Avg("9", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Oct", Avg("10", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Nov", Avg("11", "avge")));
        series.getData().add(new XYChart.Data<String, Number>("Dec", Avg("12", "avge")));
        series.setName("Employee");

        String query3 = " create table avg1 as " +
                " select  month(date) as μηνας, sum(amount)/count(distinct idBusiness) as avg" +
                " from Customers as p" +
                " where year(date) = " + year +
                " group by month(p.date)";
        stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().add(new XYChart.Data<String, Number>("Jan", Avg("1", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Feb", Avg("2", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Mar", Avg("3", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Apr", Avg("4", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("May", Avg("5", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Jun", Avg("6", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Jul", Avg("7", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Aug", Avg("8", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Sep", Avg("9", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Oct", Avg("10", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Nov", Avg("11", "avg1")));
        series1.getData().add(new XYChart.Data<String, Number>("Dec", Avg("12", "avg1")));
        series1.setName("Average Employee");
        chart.getData().addAll(series, series1);


        String query2 = "drop table avge";
        String query4 = "drop table avg1";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        eff1.setText(("The chart currently displays your performance in comparison to the average employee."));
        conn.close();
        connection.close();
    }
}

































