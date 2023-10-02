package com.example.codex;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label TitleField;

    @FXML
    void initialize() {
        TitleField.setText(TitleField.getText() + " " + CurrentUser.getCurrentUser().getUserName() + "!");
    }

}
