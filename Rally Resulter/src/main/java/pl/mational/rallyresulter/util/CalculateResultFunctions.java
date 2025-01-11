package pl.mational.rallyresulter.util;

import pl.mational.rallyresulter.main.RallyResulterApplication;
import pl.mational.rallyresulter.model.AnswersSet;
import pl.mational.rallyresulter.model.Crew;
import pl.mational.rallyresulter.model.CrewResults;

import java.util.List;
import java.util.Objects;

import static pl.mational.rallyresulter.main.RallyResulterApplication.answersPattern;

public class CalculateResultFunctions {
    public static CrewResults generateCrewResults(Crew crew, AnswersSet correctAnswers) {
        int crewId = crew.crewId();
        String crewNames = formatCrewNames(crew);
        String crewClubs = formatCrewClubs(crew);
        int brdPpTestPenaltyPoints = calculateTestPenaltyPoints(
                crew.answers().brdPpAnswers(), correctAnswers.brdPpAnswers());
        int touristicTestPenaltyPoints = calculateTestPenaltyPoints(
                crew.answers().touristAnswers(), correctAnswers.touristAnswers());
        int roadTestPenaltyPoints = calculateTestPenaltyPoints(
                crew.answers().roadAnswers(), correctAnswers.roadAnswers());
        int roadCardPenaltyPoints = calculateTestPenaltyPoints(
                crew.answers().roadCardAnswers(), correctAnswers.roadCardAnswers());
        int finalResult = brdPpTestPenaltyPoints + touristicTestPenaltyPoints
                + roadTestPenaltyPoints + roadCardPenaltyPoints;
        return new CrewResults(
                crewId,
                crewNames,
                crewClubs,
                brdPpTestPenaltyPoints,
                touristicTestPenaltyPoints,
                roadTestPenaltyPoints,
                roadCardPenaltyPoints,
                finalResult);
    }

    private static String formatCrewNames(Crew crew) {
        return String.format("%s %s\n%s %s",
                crew.driver().firstName().toUpperCase(), crew.driver().lastName().toUpperCase(),
                crew.pilot().firstName().toUpperCase(), crew.pilot().lastName().toUpperCase());
    }

    private static String formatCrewClubs(Crew crew) {
        return String.format("%s\n%s",
                crew.driver().club().toUpperCase(), crew.pilot().club().toUpperCase());
    }

    private static int calculateTestPenaltyPoints(List<String> correctAnswers, List<String> crewAnswers) {
        if (correctAnswers == null || crewAnswers == null) {
            throw new IllegalArgumentException("Listy odpowiedzi nie mogą być null.");
        }

        int penaltyPoints = 0;
        int size = correctAnswers.size();

        for (int i = 0; i < size; i++) {
            if (!correctAnswers.get(i).equals(crewAnswers.get(i))) {
                penaltyPoints+=10;
            }
        }
        return penaltyPoints;
    }
}
