package com.example.codex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField logInField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button singUpButton;

    @FXML
    void initialize() {
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'MainView.fxml'.";
        assert logInField != null : "fx:id=\"logInField\" was not injected: check your FXML file 'MainView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'MainView.fxml'.";
        assert singUpButton != null : "fx:id=\"singUpButton\" was not injected: check your FXML file 'MainView.fxml'.";

    }

}