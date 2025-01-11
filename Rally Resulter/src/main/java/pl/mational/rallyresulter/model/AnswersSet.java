package pl.mational.rallyresulter.model;

import java.util.List;

public record AnswersSet(
        List<String> roadCardAnswers,
        List<String> brdPpAnswers,
        List<String> roadAnswers,
        List<String> touristAnswers
) {
}
