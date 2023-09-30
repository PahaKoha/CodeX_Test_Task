package com.example.codex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField logInField;

    @FXML
    private TextField logInField1;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordField1;

    @FXML
    private TextField passwordField11;

    @FXML
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'SingUpView.fxml'.";
        assert logInField != null : "fx:id=\"logInField\" was not injected: check your FXML file 'SingUpView.fxml'.";
        assert logInField1 != null : "fx:id=\"logInField1\" was not injected: check your FXML file 'SingUpView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'SingUpView.fxml'.";
        assert passwordField1 != null : "fx:id=\"passwordField1\" was not injected: check your FXML file 'SingUpView.fxml'.";
        assert passwordField11 != null : "fx:id=\"passwordField11\" was not injected: check your FXML file 'SingUpView.fxml'.";

    }

}
