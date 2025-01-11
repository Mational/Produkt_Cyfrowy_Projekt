package pl.mational.rallyresulter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputEvent;
import pl.mational.rallyresulter.model.AnswersSet;
import pl.mational.rallyresulter.util.AnswersManagingFunctions;

import java.util.ArrayList;
import java.util.List;
import static pl.mational.rallyresulter.main.RallyResulterApplication.answersPattern;
import static pl.mational.rallyresulter.util.AnswersManagingFunctions.*;

public class TabAnswerPatternController {
    @FXML
    private TextField roadCardPatternField1;
    @FXML
    private TextField roadCardPatternField2;
    @FXML
    private TextField roadCardPatternField3;
    @FXML
    private TextField roadCardPatternField4;
    @FXML
    private TextField roadCardPatternField5;
    @FXML
    private TextField roadCardPatternField6;
    @FXML
    private TextField roadCardPatternField7;
    @FXML
    private TextField roadCardPatternField8;
    @FXML
    private TextField roadCardPatternField9;
    @FXML
    private TextField roadCardPatternField10;
    @FXML
    private TextField roadCardPatternField11;
    @FXML
    private TextField roadCardPatternField12;
    @FXML
    private TextField roadCardPatternField13;
    @FXML
    private TextField roadCardPatternField14;
    @FXML
    private TextField roadCardPatternField15;
    @FXML
    private TextField roadCardPatternField16;
    @FXML
    private TextField roadCardPatternField17;
    @FXML
    private TextField roadCardPatternField18;
    @FXML
    private TextField roadCardPatternField19;
    @FXML
    private TextField roadCardPatternField20;
    @FXML
    private TextField roadCardPatternField21;
    @FXML
    private TextField roadCardPatternField22;
    @FXML
    private TextField roadCardPatternField23;
    @FXML
    private TextField roadCardPatternField24;
    @FXML
    private TextField roadCardPatternField25;
    @FXML
    private TextField roadCardPatternField26;
    @FXML
    private TextField roadCardPatternField27;
    @FXML
    private TextField roadCardPatternField28;
    @FXML
    private TextField roadCardPatternField29;
    @FXML
    private TextField roadCardPatternField30;
    @FXML
    private TextField roadCardPatternField31;
    @FXML
    private TextField roadCardPatternField32;
    private List<TextField> roadCardPatternFields;
    private static final int MAX_ROAD_CARD_FIELD_LENGTH = 2;

    @FXML
    private TextField brdPatternField1;
    @FXML
    private TextField brdPatternField2;
    @FXML
    private TextField brdPatternField3;
    @FXML
    private TextField brdPatternField4;
    @FXML
    private TextField brdPatternField5;
    @FXML
    private TextField brdPatternField6;
    @FXML
    private TextField brdPatternField7;
    @FXML
    private TextField brdPatternField8;
    @FXML
    private TextField brdPatternField9;
    @FXML
    private TextField brdPatternField10;
    private List<TextField> brdPatternFields;
    private static final int MAX_BRD_PP_FIELD_LENGTH = 3;

    @FXML
    private TextField roadAnswerPattern1;
    @FXML
    private TextField roadAnswerPattern2;
    @FXML
    private TextField roadAnswerPattern3;
    @FXML
    private TextField roadAnswerPattern4;
    @FXML
    private TextField roadAnswerPattern5;
    @FXML
    private TextField roadAnswerPattern6;
    @FXML
    private TextField roadAnswerPattern7;
    @FXML
    private TextField roadAnswerPattern8;
    @FXML
    private TextField roadAnswerPattern9;
    @FXML
    private TextField roadAnswerPattern10;
    private List<TextField> roadAnswerPatterns;

    @FXML
    private TextField touristAnswerPattern1;
    @FXML
    private TextField touristAnswerPattern2;
    @FXML
    private TextField touristAnswerPattern3;
    @FXML
    private TextField touristAnswerPattern4;
    @FXML
    private TextField touristAnswerPattern5;
    @FXML
    private TextField touristAnswerPattern6;
    @FXML
    private TextField touristAnswerPattern7;
    @FXML
    private TextField touristAnswerPattern8;
    @FXML
    private TextField touristAnswerPattern9;
    @FXML
    private TextField touristAnswerPattern10;
    private List<TextField> touristAnswerPatterns;

    private static final int MAX_ROAD_TOURIST_FIELD_LENGTH = 1;

    @FXML
    public void initialize() {
        roadCardPatternFields = List.of(
                roadCardPatternField1, roadCardPatternField2, roadCardPatternField3, roadCardPatternField4,
                roadCardPatternField5, roadCardPatternField6, roadCardPatternField7, roadCardPatternField8,
                roadCardPatternField9, roadCardPatternField10, roadCardPatternField11, roadCardPatternField12,
                roadCardPatternField13, roadCardPatternField14, roadCardPatternField15, roadCardPatternField16,
                roadCardPatternField17, roadCardPatternField18, roadCardPatternField19, roadCardPatternField20,
                roadCardPatternField21, roadCardPatternField22, roadCardPatternField23, roadCardPatternField24,
                roadCardPatternField25, roadCardPatternField26, roadCardPatternField27, roadCardPatternField28,
                roadCardPatternField29, roadCardPatternField30, roadCardPatternField31, roadCardPatternField32);

        brdPatternFields = List.of(
                brdPatternField1, brdPatternField2, brdPatternField3, brdPatternField4, brdPatternField5,
                brdPatternField6, brdPatternField7, brdPatternField8, brdPatternField9, brdPatternField10);

        roadAnswerPatterns = List.of(
                roadAnswerPattern1, roadAnswerPattern2, roadAnswerPattern3, roadAnswerPattern4, roadAnswerPattern5,
                roadAnswerPattern6, roadAnswerPattern7, roadAnswerPattern8, roadAnswerPattern9, roadAnswerPattern10);

        touristAnswerPatterns = List.of(
                touristAnswerPattern1, touristAnswerPattern2, touristAnswerPattern3, touristAnswerPattern4, touristAnswerPattern5,
                touristAnswerPattern6, touristAnswerPattern7, touristAnswerPattern8, touristAnswerPattern9, touristAnswerPattern10);

        for(TextField field : roadCardPatternFields) {
            validateField(field, "[a-zA-Z0-9]*", MAX_ROAD_CARD_FIELD_LENGTH);
        }

        for (TextField field : brdPatternFields) {
            validateField(field, "(?i)^(A|B|C|D|TA?|TAK?|N?|NI?|NIE?)?$", MAX_BRD_PP_FIELD_LENGTH);
        }

        for(TextField field : roadAnswerPatterns) {
            validateField(field, "[a-dA-D]*", MAX_ROAD_TOURIST_FIELD_LENGTH);
        }

        for(TextField field : touristAnswerPatterns) {
            validateField(field, "[a-dA-D]*", MAX_ROAD_TOURIST_FIELD_LENGTH);
        }

        fillAllFieldsInPattern();
    }

    public void initializeTabView() {
        System.out.println("Inicjalizowanie zakładki szablonu odpowiedzi.");
        clearAllAnswerFields(roadCardPatternFields, brdPatternFields, roadAnswerPatterns, touristAnswerPatterns);
        fillAllFieldsInPattern();
    }

    public void onSavePatternButtonClicked(ActionEvent actionEvent) {
        if (!validateSequentialFields(roadCardPatternFields)) {
            System.out.println("Błąd: Pola w roadCardCrewAnswers muszą być wypełnione sekwencyjnie oraz każde" +
                    " z pól musi zawierać dokładnie dwa znaki");
            return;
        }

        if (validateNonEmptyFields(roadAnswerPatterns)) {
            System.out.println("Błąd: Wszystkie pola w roadAnswerPatterns muszą być wypełnione.");
            return;
        }

        if (validateNonEmptyFields(touristAnswerPatterns)) {
            System.out.println("Błąd: Wszystkie pola w touristAnswerPatterns muszą być wypełnione.");
            return;
        }

        if (!validateBrdPpFields(brdPatternFields, false)) {
            System.out.println("Błąd: Wszystkie pola w brdPatternFields muszą być wypełnione i zawierać wartości" +
                    " A, B, C, D, TAK lub NIE.");
            return;
        }


        // Tworzenie nowych list odpowiedzi
        List<String> roadCardAnswers = new ArrayList<>();
        List<String> brdPpAnswers = new ArrayList<>();
        List<String> roadAnswers = new ArrayList<>();
        List<String> touristAnswers = new ArrayList<>();



        // Dodawanie odpowiedzi z TextFieldów do odpowiednich list
        for (TextField field : roadCardPatternFields) {
            roadCardAnswers.add(field.getText());
        }
        for (TextField field : brdPatternFields) {
            brdPpAnswers.add(field.getText());
        }
        for (TextField field : roadAnswerPatterns) {
            roadAnswers.add(field.getText());
        }
        for (TextField field : touristAnswerPatterns) {
            touristAnswers.add(field.getText());
        }

        // Tworzenie nowego obiektu AnswersSet z przygotowanymi listami
        answersPattern = new AnswersSet(roadCardAnswers, brdPpAnswers, roadAnswers, touristAnswers);

        // Wyświetlenie lub zapisanie wyników
        System.out.println("Szablon odpowiedzi: " + answersPattern);
    }

    public void onInputTextChanged(InputEvent event) {
        if (event.getSource() instanceof TextField eventSource) {
            int cursorPosition = eventSource.getCaretPosition(); // Zapamiętaj pozycję kursora
            eventSource.setText(eventSource.getText().toUpperCase());
            eventSource.positionCaret(cursorPosition); // Ustaw kursor na poprzednią pozycję
        }
    }

    public void onClearPatternsButtonClicked(ActionEvent actionEvent) {
        clearAllAnswerFields(roadCardPatternFields, brdPatternFields, roadAnswerPatterns, touristAnswerPatterns);
    }

    private void fillAllFieldsInPattern() {
        // Uzupełnij pola odpowiedzi, jeśli answers nie jest null
        if (answersPattern != null) {
            // Wypełnij roadCardCrewAnswers
            List<String> roadCardAnswers = answersPattern.roadCardAnswers();
            for (int i = 0; i < roadCardPatternFields.size(); i++) {
                roadCardPatternFields.get(i).setText(i < roadCardAnswers.size() ? roadCardAnswers.get(i) : "");
            }

            // Wypełnij brdCrewAnswers
            List<String> brdPpAnswers = answersPattern.brdPpAnswers();
            for (int i = 0; i < brdPatternFields.size(); i++) {
                brdPatternFields.get(i).setText(i < brdPpAnswers.size() ? brdPpAnswers.get(i) : "");
            }

            // Wypełnij roadCrewAnswers
            List<String> roadAnswers = answersPattern.roadAnswers();
            for (int i = 0; i < roadAnswerPatterns.size(); i++) {
                roadAnswerPatterns.get(i).setText(i < roadAnswers.size() ? roadAnswers.get(i) : "");
            }

            // Wypełnij touristCrewAnswers
            List<String> touristAnswers = answersPattern.touristAnswers();
            for (int i = 0; i < touristAnswerPatterns.size(); i++) {
                touristAnswerPatterns.get(i).setText(i < touristAnswers.size() ? touristAnswers.get(i) : "");
            }
        }
    }
}
