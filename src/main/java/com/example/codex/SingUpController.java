package com.example.codex;

import java.net.URL;
import java.sql.SQLException;
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
    private TextField countryField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField logInField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button singUpButton;

    @FXML
    void initialize() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        singUpButton.setOnAction(event -> {
            dataBaseHandler.singUpUser(nameField.getText(), lastNameField.getText(), logInField.getText(),
                    passwordField.getText(), countryField.getText());
        });
    }

}
