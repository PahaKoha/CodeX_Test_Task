package com.example.codex;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        loginUser();
        singUpButton.setOnAction(event -> {
            goToAnotherPage("/com/example/codex/SingUpView.fxml");
        });
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

    private void loginUser(/*String loginText, String loginPassword*/) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        logInButton.setOnAction(event -> {
            String loginText = logInField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            User user = new User();
            CurrentUser.setCurrentUser(user);
            if (!loginText.equals("") && !loginPassword.equals("")) {
                dataBaseHandler.logInUser(CurrentUser.getCurrentUser(), loginText, loginPassword);
            } else {
                System.out.println("Login or password is empty");
            }
        });
    }

}