package com.example.codex;

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AllNotesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label label;

    @FXML
    void initialize() {
        label.setText(CurrentUser.getCurrentUser().getUserName() + "' " + label.getText());
        viewAllNotes();
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
        });
    }

    void viewAllNotes() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Map<String, String> noteNames = new HashMap<>();
        dataBaseHandler.getAllNotes(noteNames);
        Accordion accordion = new Accordion();
        for (String key : noteNames.keySet()) {
            TitledPane titledPane = new TitledPane(key, new javafx.scene.control.Label(noteNames.get(key)));
            accordion.getPanes().add(titledPane);
        }
        anchorPane.getChildren().add(accordion);
    }
}