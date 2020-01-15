package sample;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;

import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class EmailController implements  Initializable {
    @FXML
    private JFXButton out;

    @FXML
    private JFXButton yah;

    @FXML
    private JFXButton g;

    @FXML
    private JFXButton m;

    @FXML
    private WebView web;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final WebEngine webeng = web.getEngine();
        String url = "https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&ct=1577741380&rver=7.0.6737.0&wp=MBI_SSL&wreply=" +
                "https%3a%2f%2foutlook.live.com%2fowa%2f%3fnlp%3d1%26RpsCsrfState%3daa016305-d7fa-6b9c-eb9b-f96cf2a1db54&id=292841&aadredir=1&CBCXT=out&lw=1&fl=dob%2cflname%2cwld&cobrandid=90015 ";
        webeng.load(url);
    }


    public void outlook(ActionEvent actionEvent) {
        final WebEngine webeng = web.getEngine();
        String url = "https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&ct=1577741380&rver=7.0.6737.0&wp=MBI_SSL&wreply=" +
                "https%3a%2f%2foutlook.live.com%2fowa%2f%3fnlp%3d1%26RpsCsrfState%3daa016305-d7fa-6b9c-eb9b-f96cf2a1db54&id=292841&aadredir=1&CBCXT=out&lw=1&fl=dob%2cflname%2cwld&cobrandid=90015 ";
        webeng.load(url);
    }


    public void yahoo(ActionEvent actionEvent) {
        final WebEngine webeng = web.getEngine();
        String url ="https://login.yahoo.com/config/login?.src=fpctx&.intl=gr&.lang=el-GR&.done=https%3A%2F%2Fgr.yahoo.com" ;
        webeng.load(url);
    }


    public void gmail(ActionEvent actionEvent) {
        final WebEngine webeng = web.getEngine();
        String url ="https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession" ;
        webeng.load(url);

    }


    public void mail(ActionEvent actionEvent) {
        final WebEngine webeng = web.getEngine();
        String url = "https://www.mail.com/int/" ;
        webeng.load(url);
    }
}
