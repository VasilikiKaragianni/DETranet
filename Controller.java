package sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField pass1;

    @FXML
    private JFXPasswordField cpass;

    @FXML
    private JFXTextField depa;

    @FXML
    private JFXTextField funame;

    @FXML
    private JFXDatePicker date1;



    public void GotoLogIn(ActionEvent event) throws IOException {
        Parent p1 = FXMLLoader.load(getClass().getResource("Log in.fxml"));
        Scene c1 = new Scene(p1);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(c1);
        window.show();
    }

    public void GotoSignUp(ActionEvent event) throws IOException {
        Parent p1 = FXMLLoader.load(getClass().getResource("Sign up form.fxml"));
        Scene s1= new Scene(p1);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();


    }

    public void getdata(ActionEvent event) throws IOException {
        email.getText();
        pass1.getText();
        cpass.getText();
        date1.getValue();
       depa.getText();
        funame.getText();
        
        Parent p1 = FXMLLoader.load(getClass().getResource("Log in.fxml"));
        Scene c1 = new Scene(p1);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(c1);
        window.show();
    }

    }