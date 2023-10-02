package com.example.codex;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateNewNoteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private TextField giveNoteATitleField;

    @FXML
    private TextArea noteArea;

    @FXML
    private Button saveNoteButton;

    @FXML
    void initialize() {
        saveNoteButton.setOnAction(event -> {
            saveNote();
        });
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
        });
    }

    void saveNote() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        String noteName = giveNoteATitleField.getText();
        String note = noteArea.getText();
        String userId = (CurrentUser.getCurrentUser().getId());
        if (!noteName.equals("")) {
            dataBaseHandler.createNewNote(userId, noteName, note);
            System.out.println("Note saved!");
        } else {
            System.out.println("Note name is empty");
        }

    }

}
