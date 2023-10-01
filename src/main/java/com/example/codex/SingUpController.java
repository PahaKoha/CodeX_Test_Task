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
        singUpUser();
    }
    private void singUpUser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();



        singUpButton.setOnAction(event -> {
            String firstName = nameField.getText();
            String lastName = lastNameField.getText();
            String userName = logInField.getText();
            String password = passwordField.getText();
            String location = countryField.getText();

            User user = new User(firstName, lastName, userName, password, location);
            dataBaseHandler.singUpUser(user);
        });
    }

}
