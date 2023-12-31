package com.example.codex;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createNewNoteButton;

    @FXML
    private Label titleField;

    @FXML
    private Button viewNotesButton;

    @FXML
    private Button logOutButton;

    @FXML
    void initialize() {
        titleField.setText(titleField.getText() + " " + CurrentUser.getCurrentUser().getUserName() + "!");
        createNewNoteButton.setOnAction(event -> {
            goToAnotherPage("/com/example/codex/CreateNewNote.fxml");
        });
        viewNotesButton.setOnAction(event -> {
            goToAnotherPage("/com/example/codex/AllNotes.fxml");
        });
        logOutButton.setOnAction(event -> {
            CurrentUser.setCurrentUser(null);
            logOutButton.getScene().getWindow().hide();
            goToAnotherPage("/com/example/codex/MainView.fxml");
            System.out.println(CurrentUser.getCurrentUser());
        });
    }

    void goToAnotherPage(String address) {

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



}
