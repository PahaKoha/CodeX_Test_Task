package com.example.codex;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    void goToAnotherPage(String address) {
        singUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(address));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void singUpUser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        singUpButton.setOnAction(event -> {
            String firstName = nameField.getText();
            String lastName = lastNameField.getText();
            String userName = logInField.getText();
            String password = passwordField.getText();
            String location = countryField.getText();


            dataBaseHandler.singUpUser(firstName, lastName, userName, password, location);
            goToAnotherPage("/com/example/codex/MainView.fxml");
        });
    }

}
