package pl.mational.rallyresulter.main;

import pl.mational.rallyresulter.model.AnswersSet;
import pl.mational.rallyresulter.model.Crew;
import pl.mational.rallyresulter.model.CrewMember;

import java.util.ArrayList;
import java.util.List;

public class ApplicationState {
    private AnswersSet answersPattern;
    private List<Crew> crewList;

    public ApplicationState() {}

    public ApplicationState(AnswersSet answersPattern, List<Crew> crewList) {
        this.answersPattern = answersPattern;
        this.crewList = crewList;
    }

    // Gettery (wymagane dla Jackson do serializacji)
    public AnswersSet getAnswersPattern() {
        return answersPattern;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        boolean answersPatternEquals = this.answersPattern.equals(((ApplicationState) obj).answersPattern);
        boolean listCrewEquals = this.crewList.equals(((ApplicationState) obj).crewList);
        return answersPatternEquals && listCrewEquals;
    }
}
