package pl.mational.rallyresulter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import pl.mational.rallyresulter.main.RallyResulterApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class TabNewGameController {
    public Button createNewProjectButton;

    public void initializeTabView() {
    }

    @FXML
    public void onCreateNewProjectButtonClick(ActionEvent actionEvent) throws IOException {
        String javaBin = System.getProperty("java.home") + "/bin/java";
        String currentJar = new java.io.File(RallyResulterApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();

        // Sprawdź, czy aplikacja jest uruchamiana jako JAR
        if (!currentJar.endsWith(".jar")) {
            throw new RuntimeException("Aplikacja nie została uruchomiona jako plik JAR.");
        }

        // Tworzenie nowego procesu
        ProcessBuilder processBuilder = new ProcessBuilder(javaBin, "-jar", currentJar);
        processBuilder.start();

        // Zamknij bieżącą instancję
        System.exit(0);
    }
}
