package pl.mational.rallyresulter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.mational.rallyresulter.main.RallyResulterApplication;
import pl.mational.rallyresulter.model.Crew;
import pl.mational.rallyresulter.model.CrewMember;

import java.util.List;

import static pl.mational.rallyresulter.main.RallyResulterApplication.crewList;

public class TabManageCrewsController {
    @FXML
    private ListView<String> crewsListView;

    @FXML
    private TextField crewIdTextField;

    @FXML
    private TextField driverFirstNameTextField;
    @FXML
    private TextField driverLastNameTextField;
    @FXML
    private ChoiceBox<String> driverGenderChoiceBox;
    @FXML
    private ChoiceBox<String> driverClubChoiceBox;

    @FXML
    private TextField pilotFirstNameTextField;
    @FXML
    private TextField pilotLastNameTextField;
    @FXML
    private ChoiceBox<String> pilotGenderChoiceBox;
    @FXML
    private ChoiceBox<String> pilotClubChoiceBox;

    @FXML
    private VBox crewDetailsVBox;
    @FXML
    private Button deleteCrewButton;

    private Crew currentEditingCrew; // Aktualnie edytowana drużyna

    @FXML
    public void initialize() {
        driverGenderChoiceBox.getItems().addAll("MĘŻCZYZNA", "KOBIETA");
        pilotGenderChoiceBox.getItems().addAll("MĘŻCZYZNA", "KOBIETA");

        driverClubChoiceBox.getItems().addAll("N/Z", "AUTOMOBIL KLUB GRUDZIĄDZKI", "AUTOMOBIL KLUB INOWROCŁAWSKI");
        pilotClubChoiceBox.getItems().addAll("N/Z", "AUTOMOBIL KLUB GRUDZIĄDZKI", "AUTOMOBIL KLUB INOWROCŁAWSKI");

        crewDetailsVBox.visibleProperty().set(false);
        deleteCrewButton.setDisable(true); // Początkowo wyłączony przycisk usuń
    }

    public void initializeTabView() {
        System.out.println("Inicjalizowanie zakładki zarządzania drużynami.");
        crewsListView.getItems().clear();
        crewDetailsVBox.visibleProperty().set(false);
        currentEditingCrew = null;
        deleteCrewButton.setDisable(true);

        for(Crew crew: crewList) {
            String crewDescription = formatCrewDetails(crew);
            crewsListView.getItems().add(crewDescription);
        }
    }

    public void onCrewSelected(MouseEvent event) {
        // Pobierz indeks wybranego elementu w ListView
        int selectedIndex = crewsListView.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + selectedIndex);
        if (selectedIndex >= 0 && selectedIndex < crewList.size()) {
            System.out.println("OK");
            // Pobierz wybraną drużynę z listy crewList
            currentEditingCrew = crewList.get(selectedIndex);

            // Ustawienie wartości w polach VBox na podstawie danych wybranej drużyny
            crewIdTextField.setText(String.valueOf(currentEditingCrew.crewId()));
            driverFirstNameTextField.setText(currentEditingCrew.driver().firstName());
            driverLastNameTextField.setText(currentEditingCrew.driver().lastName());
            driverGenderChoiceBox.setValue(currentEditingCrew.driver().gender());
            driverClubChoiceBox.setValue(currentEditingCrew.driver().club());

            pilotFirstNameTextField.setText(currentEditingCrew.pilot().firstName());
            pilotLastNameTextField.setText(currentEditingCrew.pilot().lastName());
            pilotGenderChoiceBox.setValue(currentEditingCrew.pilot().gender());
            pilotClubChoiceBox.setValue(currentEditingCrew.pilot().club());

            // Ustawienie widoczności VBox na true
            crewDetailsVBox.setVisible(true);
            deleteCrewButton.setDisable(false); // Aktywacja przycisku usuń
        } else {
            deleteCrewButton.setDisable(true); // Dezaktywacja przycisku usuń, jeśli nie ma wybranej drużyny
        }
    }

    public void onAddCrewButtonClicked(ActionEvent event) {
        onClearCrewDetailsButtonClicked(event);
        currentEditingCrew = null;
        deleteCrewButton.setDisable(true);

        int crewNumber = generateSmallestAvailableCrewId();
        crewIdTextField.setText(String.valueOf(crewNumber));
        crewDetailsVBox.setVisible(true);
    }

    public void onDeleteCrewButtonClicked(ActionEvent event) {
        // Sprawdzenie, czy istnieje wybrana drużyna do usunięcia
        if (currentEditingCrew != null) {
            int index = crewList.indexOf(currentEditingCrew);

            // Usunięcie drużyny z listy crewList i ListView
            crewList.remove(currentEditingCrew);
            crewsListView.getItems().remove(index);

            // Resetowanie edytowanej drużyny i ukrycie VBox
            currentEditingCrew = null;
            crewDetailsVBox.setVisible(false);

            // Dezaktywacja przycisku usuń
            deleteCrewButton.setDisable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Brak wybranej drużyny");
            alert.setContentText("Proszę wybrać drużynę przed próbą usunięcia.");
            alert.showAndWait();
        }

        System.out.println(crewList.toString());
    }

    public void onClearCrewDetailsButtonClicked(ActionEvent event) {
        crewIdTextField.clear();
        driverFirstNameTextField.clear();
        driverLastNameTextField.clear();
        driverGenderChoiceBox.getSelectionModel().clearSelection();
        driverClubChoiceBox.getSelectionModel().clearSelection();
        pilotFirstNameTextField.clear();
        pilotLastNameTextField.clear();
        pilotGenderChoiceBox.getSelectionModel().clearSelection();
        pilotClubChoiceBox.getSelectionModel().clearSelection();
    }

    public void onSaveCrewDetailsButtonClicked(ActionEvent event) {
        // Sprawdzenie, czy wszystkie pola są wypełnione
        if (areFieldsFilled()) {
            int crewId = Integer.parseInt(crewIdTextField.getText());

            // Sprawdzanie unikalności crewId, jeśli tworzymy nową drużynę lub zmieniamy ID istniejącej
            boolean isCrewIdUnique = crewList.stream()
                    .noneMatch(crew -> crew.crewId() == crewId && crew != currentEditingCrew);

            if (!isCrewIdUnique) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Numer załogi już istnieje");
                alert.setContentText("Proszę podać unikalny numer załogi.");
                alert.showAndWait();
                return;
            }

            // Tworzenie obiektu CrewMember dla kierowcy
            CrewMember driver = new CrewMember(
                    driverFirstNameTextField.getText(),
                    driverLastNameTextField.getText(),
                    driverGenderChoiceBox.getSelectionModel().getSelectedItem(),
                    driverClubChoiceBox.getSelectionModel().getSelectedItem()
            );

            // Tworzenie obiektu CrewMember dla pilota
            CrewMember pilot = new CrewMember(
                    pilotFirstNameTextField.getText(),
                    pilotLastNameTextField.getText(),
                    pilotGenderChoiceBox.getSelectionModel().getSelectedItem(),
                    pilotClubChoiceBox.getSelectionModel().getSelectedItem()
            );

            if (currentEditingCrew == null) {
                // Tworzenie nowego obiektu Crew
                Crew newCrew = new Crew(crewId, driver, pilot, null);

                // Dodanie nowej drużyny do listy crewList
                crewList.add(newCrew);

                // Dodanie nowego wpisu do ListView
                String crewDetails = formatCrewDetails(newCrew);
                crewsListView.getItems().add(crewDetails);
            } else {
                // Aktualizacja istniejącego obiektu Crew
                crewList.set(crewList.indexOf(currentEditingCrew), new Crew(crewId, driver, pilot, currentEditingCrew.answers()));

                // Aktualizacja wpisu w ListView
                int index = crewsListView.getSelectionModel().getSelectedIndex();
                crewsListView.getItems().set(index, formatCrewDetails(new Crew(crewId, driver, pilot, null)));
            }

            // Ukrycie VBox po zapisaniu i wyczyszczenie pól
            crewDetailsVBox.setVisible(false);
            onClearCrewDetailsButtonClicked(event);
        } else {
            // Wyświetlenie komunikatu o błędzie, jeśli któreś pole jest puste
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wszystkie pola muszą być wypełnione");
            alert.setContentText("Proszę uzupełnić wszystkie pola przed zapisem.");
            alert.showAndWait();
        }
    }

    private String formatCrewDetails(Crew crew) {
        return String.format("Załoga nr %d\n    %s %s\n    %s %s",
                crew.crewId(),
                crew.driver().firstName(), crew.driver().lastName(),
                crew.pilot().firstName(), crew.pilot().lastName());
    }

    // Metoda do znalezienia najmniejszego dostępnego numeru drużyny
    private int generateSmallestAvailableCrewId() {
        // Pobieramy wszystkie istniejące crewId do listy
        List<Integer> existingIds = crewList.stream()
                .map(Crew::crewId)
                .sorted()
                .toList();

        // Szukamy najmniejszego dostępnego crewId
        int newId = 1;
        for (int id : existingIds) {
            if (id == newId) {
                newId++;
            } else if (id > newId) {
                break;
            }
        }
        return newId;
    }

    private boolean areFieldsFilled() {
        return !crewIdTextField.getText().isEmpty() &&
                !driverFirstNameTextField.getText().isEmpty() &&
                !driverLastNameTextField.getText().isEmpty() &&
                driverGenderChoiceBox.getSelectionModel().getSelectedItem() != null &&
                driverClubChoiceBox.getSelectionModel().getSelectedItem() != null &&
                !pilotFirstNameTextField.getText().isEmpty() &&
                !pilotLastNameTextField.getText().isEmpty() &&
                pilotGenderChoiceBox.getSelectionModel().getSelectedItem() != null &&
                pilotClubChoiceBox.getSelectionModel().getSelectedItem() != null;
    }
}
