package pl.mational.rallyresulter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pl.mational.rallyresulter.model.Crew;
import pl.mational.rallyresulter.model.CrewResults;
import pl.mational.rallyresulter.util.CalculateResultFunctions;
import pl.mational.rallyresulter.util.PDFGenerateFunctions;

import java.util.ArrayList;
import java.util.List;

import static pl.mational.rallyresulter.main.RallyResulterApplication.answersPattern;
import static pl.mational.rallyresulter.main.RallyResulterApplication.crewList;
import static pl.mational.rallyresulter.util.CalculateResultFunctions.generateCrewResults;

public class TabGenerateReportController {
    @FXML
    private Text roadCardAnswersPatternText;
    @FXML
    private Text brdPpAnswersPatternText;
    @FXML
    private Text touristicAnswersPatternText;
    @FXML
    private Text roadAnswersPatternText;
    @FXML
    private Text numberOfCrewsText;
    @FXML
    private Text numberOfCrewsWithRunDetails;
    @FXML
    private Button generateResultsButton;

    public void initializeTabView() {
        System.out.println("Inicjalizowanie zakładki generowania raportu.");

        int numberOfFilledCrewsInt = 0;
        for (Crew crew : crewList) {
            if (crew.answers() != null) { numberOfFilledCrewsInt++; }
        }
        String numberOfFilledCrews = Integer.toString(numberOfFilledCrewsInt);

        String numberOfCrews = Integer.toString(crewList.size());
        if (answersPattern == null) {
            setAllTabTexts("", "", "",
                    "", numberOfCrews, numberOfFilledCrews);
        } else {
            setAllTabTexts(convertListToString(answersPattern.roadCardAnswers()),
                    convertListToString(answersPattern.brdPpAnswers()),
                    convertListToString(answersPattern.touristAnswers()),
                    convertListToString(answersPattern.roadAnswers()),
                    numberOfCrews, numberOfFilledCrews);
        }
    }

    public void onGenerateResultsButtonClicked(ActionEvent actionEvent) {
        int totalCrews = crewList.size();
        int filledCrews = (int) crewList.stream().filter(crew -> crew.answers() != null).count();

        if (filledCrews != totalCrews) {
            showAlert("Nie można wygenerować raportu",
                    "Liczba drużyn z wypełnionymi informacjami o przejeździe (" + filledCrews +
                            ") nie jest równa ogólnej liczbie drużyn (" + totalCrews + ").");
        } else {
            System.out.println("Generowanie raportu...");
            List<CrewResults> crewsResults = new ArrayList<>();
            for (Crew crew : crewList) { crewsResults.add(generateCrewResults(crew, answersPattern)); }

            // Sortowanie listy crewResults po polu finalResult rosnąco
            crewsResults.sort((c1, c2) -> Integer.compare(c1.finalResult(), c2.finalResult()));

            System.out.println(crewsResults);

            PDFGenerateFunctions.generatePDF(
                    "C:\\Users\\grzeg\\Documents\\RallyResulterReports\\KlasyfikacjaGeneralna20.pdf",
                    crewsResults);
        }
    }

    private void setAllTabTexts(String roadCardAnswersPattern, String brdPpAnswersPattern,
                                String touristicAnswersPattern, String roadAnswersPattern,
                                String numberOfCrews, String numberOfFilledCrews) {
        roadCardAnswersPatternText.setText(roadCardAnswersPattern);
        brdPpAnswersPatternText.setText(brdPpAnswersPattern);
        touristicAnswersPatternText.setText(touristicAnswersPattern);
        roadAnswersPatternText.setText(roadAnswersPattern);
        numberOfCrewsText.setText(numberOfCrews);
        numberOfCrewsWithRunDetails.setText(numberOfFilledCrews);
    }

    private String convertListToString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String answer : list) { result.append(answer).append(", "); }
        return trimTrailingCommasAndSpaces(result.toString());
    }

    private String trimTrailingCommasAndSpaces(String input) {
        return input.replaceAll("(,\\s*)+$", "");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
