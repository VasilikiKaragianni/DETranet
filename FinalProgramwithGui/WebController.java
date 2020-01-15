package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class WebController implements Initializable {
    @FXML
    private JFXButton forbes;

    @FXML
    private JFXButton bloom;

    @FXML
    private JFXButton financiall;

    @FXML
    private JFXButton cnn;

    @FXML
    private JFXButton reuters;

    @FXML
    private WebView web;


    //Για το κάθε site που θέλουμε να εμφανιστεί επιλέγουμε το url του

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final WebEngine webeng = web.getEngine();
        String url = "https://www.ft.com/ ";
        webeng.load(url);
    }


    @FXML
    private void finatimes(ActionEvent event) {
        final WebEngine webeng = web.getEngine();
        String url = "https://www.ft.com/ ";
        webeng.load(url);
    }

    @FXML
    private void cnn(ActionEvent event) {
        final WebEngine webeng = web.getEngine();
        String url = "https://edition.cnn.com/ ";
        webeng.load(url);
    }

    @FXML
    private void reuters(ActionEvent event) {
        final WebEngine webeng = web.getEngine();
        String url = "https://www.reuters.com/ ";
        webeng.load(url);
    }

    public void bloom(ActionEvent actionEvent) {
        final WebEngine webeng = web.getEngine();
        String url = "https://www.bloomberg.com/europe ";
        webeng.load(url);
    }

    public void forbes1(ActionEvent actionEvent) {
        final WebEngine webeng = web.getEngine();
        String url = "https://www.forbes.com/ ";
        webeng.load(url);
    }



}