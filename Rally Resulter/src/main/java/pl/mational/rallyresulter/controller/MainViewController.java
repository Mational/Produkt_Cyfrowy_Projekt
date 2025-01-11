package pl.mational.rallyresulter.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import pl.mational.rallyresulter.main.RallyResulterApplication;

import java.io.IOException;
import java.util.List;

public class MainViewController {
    private RallyResulterApplication mainApplication;

    @FXML private TabPane tabPane;
    @FXML private Tab tabAnswerPattern;
    @FXML private Tab tabManageCrews;
    @FXML private Tab tabCrewAnswers;
    @FXML private Tab tabGenerateReport;

    private TabAnswerPatternController tabAnswerPatternController;
    private TabManageCrewsController tabManageCrewsController;
    private TabCrewAnswersController tabCrewAnswersController;
    private TabGenerateReportController tabGenerateReportController;

    @FXML
    public void initialize() {
        loadTabContent(tabAnswerPattern, "/pl/mational/rallyresulter/fxml/tabAnswerPattern.fxml",
                controller -> tabAnswerPatternController = (TabAnswerPatternController) controller);
        loadTabContent(tabManageCrews, "/pl/mational/rallyresulter/fxml/tabManageCrews.fxml",
                controller -> tabManageCrewsController = (TabManageCrewsController) controller);
        loadTabContent(tabCrewAnswers, "/pl/mational/rallyresulter/fxml/tabCrewAnswers.fxml",
                controller -> tabCrewAnswersController = (TabCrewAnswersController) controller);
        loadTabContent(tabGenerateReport, "/pl/mational/rallyresulter/fxml/tabGenerateReport.fxml",
                controller -> tabGenerateReportController = (TabGenerateReportController) controller);

        tabAnswerPattern.setOnSelectionChanged(this::handleTabSelection);
    }

    private void loadTabContent(Tab tab, String fxmlFile, ControllerInitializer initializer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane content = loader.load();
            tab.setContent(content);
            initializer.initController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTabSelection(Event event) {
        Tab selectedTab = (Tab) event.getSource();

        if (selectedTab.isSelected()) {
            if (selectedTab == tabAnswerPattern && tabAnswerPatternController != null) {
                tabAnswerPatternController.initializeTabView();
            } else if (selectedTab == tabManageCrews && tabManageCrewsController != null) {
                tabManageCrewsController.initializeTabView();
            } else if (selectedTab == tabCrewAnswers && tabCrewAnswersController != null) {
                tabCrewAnswersController.initializeTabView();
            } else if (selectedTab == tabGenerateReport && tabGenerateReportController != null) {
                tabGenerateReportController.initializeTabView();
            }
        }
    }

    public void setMainApplication(RallyResulterApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FunctionalInterface
    private interface ControllerInitializer {
        void initController(Object controller);
    }
}
