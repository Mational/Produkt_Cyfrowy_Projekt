package pl.mational.rallyresulter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.mational.rallyresulter.model.AnswersSet;
import pl.mational.rallyresulter.model.Crew;

import java.util.ArrayList;
import java.util.List;

import static pl.mational.rallyresulter.main.RallyResulterApplication.crewList;
import static pl.mational.rallyresulter.util.AnswersManagingFunctions.*;

public class TabCrewAnswersController {
    @FXML
    private ListView<String> crewListView;

    @FXML
    private TextField roadCardCrewAnswer1;
    @FXML
    private TextField roadCardCrewAnswer2;
    @FXML
    private TextField roadCardCrewAnswer3;
    @FXML
    private TextField roadCardCrewAnswer4;
    @FXML
    private TextField roadCardCrewAnswer5;
    @FXML
    private TextField roadCardCrewAnswer6;
    @FXML
    private TextField roadCardCrewAnswer7;
    @FXML
    private TextField roadCardCrewAnswer8;
    @FXML
    private TextField roadCardCrewAnswer9;
    @FXML
    private TextField roadCardCrewAnswer10;
    @FXML
    private TextField roadCardCrewAnswer11;
    @FXML
    private TextField roadCardCrewAnswer12;
    @FXML
    private TextField roadCardCrewAnswer13;
    @FXML
    private TextField roadCardCrewAnswer14;
    @FXML
    private TextField roadCardCrewAnswer15;
    @FXML
    private TextField roadCardCrewAnswer16;
    @FXML
    private TextField roadCardCrewAnswer17;
    @FXML
    private TextField roadCardCrewAnswer18;
    @FXML
    private TextField roadCardCrewAnswer19;
    @FXML
    private TextField roadCardCrewAnswer20;
    @FXML
    private TextField roadCardCrewAnswer21;
    @FXML
    private TextField roadCardCrewAnswer22;
    @FXML
    private TextField roadCardCrewAnswer23;
    @FXML
    private TextField roadCardCrewAnswer24;
    @FXML
    private TextField roadCardCrewAnswer25;
    @FXML
    private TextField roadCardCrewAnswer26;
    @FXML
    private TextField roadCardCrewAnswer27;
    @FXML
    private TextField roadCardCrewAnswer28;
    @FXML
    private TextField roadCardCrewAnswer29;
    @FXML
    private TextField roadCardCrewAnswer30;
    @FXML
    private TextField roadCardCrewAnswer31;
    @FXML
    private TextField roadCardCrewAnswer32;
    private List<TextField> roadCardCrewAnswers;
    private static final int MAX_ROAD_CARD_FIELD_LENGTH = 2;

    @FXML
    private TextField brdCrewAnswer1;
    @FXML
    private TextField brdCrewAnswer2;
    @FXML
    private TextField brdCrewAnswer3;
    @FXML
    private TextField brdCrewAnswer4;
    @FXML
    private TextField brdCrewAnswer5;
    @FXML
    private TextField brdCrewAnswer6;
    @FXML
    private TextField brdCrewAnswer7;
    @FXML
    private TextField brdCrewAnswer8;
    @FXML
    private TextField brdCrewAnswer9;
    @FXML
    private TextField brdCrewAnswer10;
    private List<TextField> brdCrewAnswers;
    private static final int MAX_BRD_PP_FIELD_LENGTH = 3;

    @FXML
    private TextField roadCrewAnswer1;
    @FXML
    private TextField roadCrewAnswer2;
    @FXML
    private TextField roadCrewAnswer3;
    @FXML
    private TextField roadCrewAnswer4;
    @FXML
    private TextField roadCrewAnswer5;
    @FXML
    private TextField roadCrewAnswer6;
    @FXML
    private TextField roadCrewAnswer7;
    @FXML
    private TextField roadCrewAnswer8;
    @FXML
    private TextField roadCrewAnswer9;
    @FXML
    private TextField roadCrewAnswer10;
    private List<TextField> roadCrewAnswers;

    @FXML
    private TextField touristCrewAnswer1;
    @FXML
    private TextField touristCrewAnswer2;
    @FXML
    private TextField touristCrewAnswer3;
    @FXML
    private TextField touristCrewAnswer4;
    @FXML
    private TextField touristCrewAnswer5;
    @FXML
    private TextField touristCrewAnswer6;
    @FXML
    private TextField touristCrewAnswer7;
    @FXML
    private TextField touristCrewAnswer8;
    @FXML
    private TextField touristCrewAnswer9;
    @FXML
    private TextField touristCrewAnswer10;
    private List<TextField> touristCrewAnswers;
    private static final int MAX_ROAD_TOURIST_FIELD_LENGTH = 1;

    @FXML
    private VBox crewAnswersDetailsVBox;

    private Crew selectedCrew;

    @FXML
    public void initialize() {
        roadCardCrewAnswers = List.of(
                roadCardCrewAnswer1, roadCardCrewAnswer2, roadCardCrewAnswer3, roadCardCrewAnswer4,
                roadCardCrewAnswer5, roadCardCrewAnswer6, roadCardCrewAnswer7, roadCardCrewAnswer8,
                roadCardCrewAnswer9, roadCardCrewAnswer10, roadCardCrewAnswer11, roadCardCrewAnswer12,
                roadCardCrewAnswer13, roadCardCrewAnswer14, roadCardCrewAnswer15, roadCardCrewAnswer16,
                roadCardCrewAnswer17, roadCardCrewAnswer18, roadCardCrewAnswer19, roadCardCrewAnswer20,
                roadCardCrewAnswer21, roadCardCrewAnswer22, roadCardCrewAnswer23, roadCardCrewAnswer24,
                roadCardCrewAnswer25, roadCardCrewAnswer26, roadCardCrewAnswer27, roadCardCrewAnswer28,
                roadCardCrewAnswer29, roadCardCrewAnswer30, roadCardCrewAnswer31, roadCardCrewAnswer32);

        brdCrewAnswers = List.of(
                brdCrewAnswer1, brdCrewAnswer2, brdCrewAnswer3, brdCrewAnswer4, brdCrewAnswer5,
                brdCrewAnswer6, brdCrewAnswer7, brdCrewAnswer8, brdCrewAnswer9, brdCrewAnswer10);

        roadCrewAnswers = List.of(
                roadCrewAnswer1, roadCrewAnswer2, roadCrewAnswer3, roadCrewAnswer4, roadCrewAnswer5,
                roadCrewAnswer6, roadCrewAnswer7, roadCrewAnswer8, roadCrewAnswer9, roadCrewAnswer10);

        touristCrewAnswers = List.of(
                touristCrewAnswer1, touristCrewAnswer2, touristCrewAnswer3, touristCrewAnswer4, touristCrewAnswer5,
                touristCrewAnswer6, touristCrewAnswer7, touristCrewAnswer8, touristCrewAnswer9, touristCrewAnswer10);

        for(TextField field : roadCardCrewAnswers) {
            validateField(field, "[a-zA-Z0-9]*", MAX_ROAD_CARD_FIELD_LENGTH);
        }

        for (TextField field : brdCrewAnswers) {
            validateField(field, "(?i)^(A|B|C|D|TA?|TAK?|N?|NI?|NIE?)?$", MAX_BRD_PP_FIELD_LENGTH);
        }

        for(TextField field : roadCrewAnswers) {
            validateField(field, "[a-dA-D]*", MAX_ROAD_TOURIST_FIELD_LENGTH);
        }

        for(TextField field : touristCrewAnswers) {
            validateField(field, "[a-dA-D]*", MAX_ROAD_TOURIST_FIELD_LENGTH);
        }
    }

    public void initializeTabView() {
        System.out.println("Inicjalizowanie zakładki odpowiedzi drużyny.");
        crewListView.getItems().clear();
        crewAnswersDetailsVBox.setVisible(false);
        selectedCrew = null;

        for(Crew crew: crewList) {
            String crewDescription = formatCrewDetails(crew);
            crewListView.getItems().add(crewDescription);
        }
    }

    public void onCrewSelected(MouseEvent event) {
        // Pobierz indeks wybranego elementu w ListView
        int selectedIndex = crewListView.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + selectedIndex);
        if (selectedIndex >= 0 && selectedIndex < crewList.size()) {
            selectedCrew = crewList.get(selectedIndex);
            crewAnswersDetailsVBox.setVisible(true);

            // Pobierz answers z wybranej drużyny
            AnswersSet answers = selectedCrew.answers();

            // Uzupełnij pola odpowiedzi, jeśli answers nie jest null
            if (answers != null) {
                // Wypełnij roadCardCrewAnswers
                List<String> roadCardAnswers = answers.roadCardAnswers();
                for (int i = 0; i < roadCardCrewAnswers.size(); i++) {
                    roadCardCrewAnswers.get(i).setText(i < roadCardAnswers.size() ? roadCardAnswers.get(i) : "");
                }

                // Wypełnij brdCrewAnswers
                List<String> brdPpAnswers = answers.brdPpAnswers();
                for (int i = 0; i < brdCrewAnswers.size(); i++) {
                    brdCrewAnswers.get(i).setText(i < brdPpAnswers.size() ? brdPpAnswers.get(i) : "");
                }

                // Wypełnij roadCrewAnswers
                List<String> roadAnswers = answers.roadAnswers();
                for (int i = 0; i < roadCrewAnswers.size(); i++) {
                    roadCrewAnswers.get(i).setText(i < roadAnswers.size() ? roadAnswers.get(i) : "");
                }

                // Wypełnij touristCrewAnswers
                List<String> touristAnswers = answers.touristAnswers();
                for (int i = 0; i < touristCrewAnswers.size(); i++) {
                    touristCrewAnswers.get(i).setText(i < touristAnswers.size() ? touristAnswers.get(i) : "");
                }
            } else {
                // Jeśli answers jest null, wyczyść wszystkie pola
                clearAllAnswerFields(roadCardCrewAnswers, brdCrewAnswers, roadCrewAnswers, touristCrewAnswers);
            }
        }
    }

    public void onInputTextChanged(InputEvent event) {
        if (event.getSource() instanceof TextField eventSource) {
            int cursorPosition = eventSource.getCaretPosition(); // Zapamiętaj pozycję kursora
            eventSource.setText(eventSource.getText().toUpperCase());
            eventSource.positionCaret(cursorPosition); // Ustaw kursor na poprzednią pozycję
        }
    }

    private String formatCrewDetails(Crew crew) {
        return String.format("Załoga nr %d\n    %s %s\n    %s %s",
                crew.crewId(),
                crew.driver().firstName(), crew.driver().lastName(),
                crew.pilot().firstName(), crew.pilot().lastName());
    }

    public void onSaveCrewAnswersButtonClicked(ActionEvent event) {
        if (!validateSequentialFields(roadCardCrewAnswers)) {
            System.out.println("Błąd: Pola w roadCardCrewAnswers muszą być wypełnione sekwencyjnie oraz każde" +
                    " z pól musi zawierać dokładnie dwa znaki");
            return;
        }

        if (!validateBrdPpFields(brdCrewAnswers, true)) {
            System.out.println("Błąd: Każde wypełnione pole w BRD i PP musi zawierać wartości" +
                    " A, B, C, D, TAK lub NIE.");
            return;
        }

        // Tworzenie nowych list odpowiedzi
        List<String> roadCardAnswers = new ArrayList<>();
        List<String> brdPpAnswers = new ArrayList<>();
        List<String> roadAnswers = new ArrayList<>();
        List<String> touristAnswers = new ArrayList<>();

        // Dodawanie odpowiedzi z TextFieldów do odpowiednich list
        for (TextField field : roadCardCrewAnswers) {
            roadCardAnswers.add(field.getText());
        }
        for (TextField field : brdCrewAnswers) {
            brdPpAnswers.add(field.getText());
        }
        for (TextField field : roadCrewAnswers) {
            roadAnswers.add(field.getText());
        }
        for (TextField field : touristCrewAnswers) {
            touristAnswers.add(field.getText());
        }

        AnswersSet crewAnswers = new AnswersSet(roadCardAnswers, brdPpAnswers, roadAnswers, touristAnswers);

        crewList.set(crewList.indexOf(selectedCrew), new Crew(
                selectedCrew.crewId(),
                selectedCrew.driver(),
                selectedCrew.pilot(),
                crewAnswers));

        // Wyświetlenie lub zapisanie wyników
        System.out.println("Odpowiedzi drużyny: " + crewAnswers);
    }

    public void onClearCrewAnswersButtonClicked(ActionEvent event) {
        clearAllAnswerFields(roadCardCrewAnswers, brdCrewAnswers, roadCrewAnswers, touristCrewAnswers);
    }
}
