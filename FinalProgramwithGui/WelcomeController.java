package sample;

import base.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private Label setname;



    PreparedStatement ps = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection conn = Database.createConnection();
            String query = "Select id from keepId";
            int id = 1;

            ps = conn.prepareStatement(query);
            ResultSet rs1 = ps.executeQuery();

            if(rs1.next()){
                id = rs1.getInt(1);
            }

            String query2 = "SELECT fullname  from Employee where idEmployee = '"+id+"' ";
            String fn = null;


            ps = conn.prepareStatement(query2);
            ResultSet rs2 = ps.executeQuery();
            if(rs2.next()) {
                fn = rs2.getString(1);

            }

            setname.setText(fn);
            conn.close();



        } catch ( SQLException e) {
            e.printStackTrace();
        }

    }
}
