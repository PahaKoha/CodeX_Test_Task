package com.example.codex;

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

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
        label.setText(CurrentUser.getCurrentUser().getUserName() + "'s " + label.getText());
        viewAllNotes();
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
        });
    }

    void viewAllNotes() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler(Configs.dbHost, Configs.dbPort, Configs.dbName, Configs.dbUser, Configs.dbPassword);
        Map<String, String> notesAndNoteNames = new HashMap<>();
        dataBaseHandler.getAllNotes(notesAndNoteNames);

        double[] countWords = wordCount(notesAndNoteNames.values());

        Accordion accordion = createAccordion(notesAndNoteNames.keySet(), notesAndNoteNames.values(), countWords);
        addToAnchorPane(accordion);
    }

    double[] wordCount(Collection<String> notes) {
        double[] array = new double[notes.size()];
        int i = 0;
        for (String note : notes) {
            String[] words = note.split(" ");
            array[i++] = words.length;
        }
        return array;
    }

    Accordion createAccordion(Set<String> noteNames, Collection<String> notes, double[] countWords) {
        Accordion accordion = new Accordion();

        Iterator<String> noteNamesIterator = noteNames.iterator();
        Iterator<String> notesIterator = notes.iterator();
        for (int i = 0; i < noteNames.size(); i++) {
            double readingTime = (countWords[i] / 120) * 60;
            String noteName = noteNamesIterator.next();
            String noteText = notesIterator.next();
            TitledPane titledPane = createTitledPane(noteName, readingTime, noteText);
            accordion.getPanes().add(titledPane);
        }

        return accordion;
    }

    TitledPane createTitledPane(String noteName, double readingTime, String noteText) {
        TitledPane titledPane = new TitledPane(noteName + "\t" + " reading time: " + readingTime + " sec", new Label(noteText));
        titledPane.setMinWidth(600);
        titledPane.setMaxWidth(600);
        return titledPane;
    }

    void addToAnchorPane(Node node) {
        anchorPane.getChildren().add(node);
    }

}