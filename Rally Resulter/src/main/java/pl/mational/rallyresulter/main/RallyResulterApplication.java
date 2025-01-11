package pl.mational.rallyresulter.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.mational.rallyresulter.controller.MainViewController;
import pl.mational.rallyresulter.controller.StartScreenController;
import pl.mational.rallyresulter.model.AnswersSet;
import pl.mational.rallyresulter.model.Crew;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RallyResulterApplication extends Application {
    public static AnswersSet answersPattern;
    public static List<Crew> crewList = new ArrayList<>();

    private ApplicationState originalState; // Oryginalny stan projektu
    private boolean isNewProject = true;    // Flaga informująca, czy projekt jest nowy
    private File currentFile = null;        // Plik dla otwartego projektu
    private boolean isOnStartScreen = true;

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        primaryStage.setOnCloseRequest(this::handleApplicationClose);
        showStartScreen();
    }

    // Obsługa zamknięcia aplikacji
    private void handleApplicationClose(WindowEvent event) {
        if (isOnStartScreen) {
            primaryStage.close();
            return;
        }

        if (isProjectModified()) {
            // Jeśli projekt został zmodyfikowany, zapytaj użytkownika o zapis
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Zapisz zmiany");
            alert.setHeaderText("Czy chcesz zapisać zmiany w projekcie?");
            alert.setContentText("Twój projekt zawiera niezapisane zmiany. Czy chcesz je zapisać przed zamknięciem?");

            ButtonType saveButton = new ButtonType("Tak");
            ButtonType dontSaveButton = new ButtonType("Nie");
            ButtonType cancelButton = new ButtonType("Anuluj");

            alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == saveButton) {
                if (isNewProject) {
                    // Nowy projekt - pokaż dialog zapisu
                    saveProjectAs();
                } else {
                    // Istniejący projekt - nadpisz plik
                    saveProject(currentFile);
                }
            } else if (result.isPresent() && result.get() == dontSaveButton) {
                // Użytkownik wybrał zamknięcie bez zapisywania
                primaryStage.close();
            } else {
                // Anuluj zamknięcie
                event.consume();
            }
        }
    }

    // Metoda do zapisywania stanu aplikacji do pliku JSON
    private void saveProject(File file) {
        if (file == null) {
            saveProjectAs(); // Jeśli plik nie jest określony, wywołaj metodę zapisu jako
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Formatowanie JSON z wcięciami

        ApplicationState state = new ApplicationState(answersPattern, crewList);

        try {
            mapper.writeValue(file, state);
            System.out.println("Stan aplikacji zapisany do pliku: " + file.getAbsolutePath());
            // Ustawienie nowego oryginalnego stanu po zapisaniu
            originalState = state;
            isNewProject = false; // Już nie jest nowy projekt
            currentFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda zapisu z wyborem lokalizacji pliku
    private void saveProjectAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz projekt jako");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            saveProject(file);
        } else {
            handleApplicationClose(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST)); // Powrót do pytania
        }
    }

    // Metoda pomocnicza do wykrywania modyfikacji projektu
    private boolean isProjectModified() {
        ApplicationState currentState = new ApplicationState(answersPattern, crewList);
        return !currentState.equals(originalState);
    }

    // Wyświetlanie ekranu startowego
    public void showStartScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/mational/rallyresulter/fxml/startScene.fxml"));
            Scene scene = new Scene(loader.load());



            StartScreenController controller = loader.getController();
            controller.setMainApplication(this);

            Image icon = new Image(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/pl/mational/rallyresulter/assets/app_logo.png")));

            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Rally Resulter");
            primaryStage.setScene(scene);
            primaryStage.show();

            isOnStartScreen = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Wyświetlanie głównego ekranu aplikacji
    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/mational/rallyresulter/fxml/mainScene.fxml"));
            Scene scene = new Scene(loader.load());

            MainViewController controller = loader.getController();
            controller.setMainApplication(this);

            primaryStage.setScene(scene);
            isOnStartScreen = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadProject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ApplicationState state = mapper.readValue(file, ApplicationState.class);
            answersPattern = state.getAnswersPattern();
            crewList.addAll(state.getCrewList());
            originalState = state;  // Ustawienie oryginalnego stanu
            isNewProject = false;
            currentFile = file;
            System.out.println("Original State: " + originalState);
            System.out.println("isNewProject: " + isNewProject);
            System.out.println("CurrentFile: " + currentFile);
            showMainView(); // Jeśli wczytanie zakończyło się sukcesem, przejdź do mainScene
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Nieprawidłowy plik JSON", "Wybrany plik nie jest poprawnym plikiem JSON dla tej aplikacji.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}