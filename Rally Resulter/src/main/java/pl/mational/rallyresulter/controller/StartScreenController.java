package pl.mational.rallyresulter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import pl.mational.rallyresulter.main.RallyResulterApplication;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartScreenController {
    private RallyResulterApplication mainApplication;

    @FXML
    private Button loadExistingProjectButton;

    public void setMainApplication(RallyResulterApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    protected void onCreateNewProjectButtonClick() {
        mainApplication.showMainView();
    }

    @FXML
    protected void onLoadExistingProjectButtonClick() {
        FileChooser fileChooser = new FileChooser();

        // Ustawienie domyślnego katalogu na Documents/RallyResulterSaves
        Path documentsPath = Paths.get(System.getProperty("user.home"), "Documents", "RallyResulterSaves");
        File defaultDirectory = documentsPath.toFile();
        if (defaultDirectory.exists()) {
            fileChooser.setInitialDirectory(defaultDirectory);
        }

        // Ustawienie filtrów plików, aby wyświetlać tylko pliki JSON
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );

        // Otwarcie okna dialogowego wyboru pliku
        File selectedFile = fileChooser.showOpenDialog(loadExistingProjectButton.getScene().getWindow());

        if (selectedFile != null) { mainApplication.loadProject(selectedFile); }
    }

    @FXML
    protected void onExitProgramButtonClick() {
        System.exit(0);
    }
}