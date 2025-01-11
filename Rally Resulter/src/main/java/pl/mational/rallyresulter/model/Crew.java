package pl.mational.rallyresulter.model;

public record Crew(
        int crewId,
        CrewMember driver,
        CrewMember pilot,
        AnswersSet answers
) {
}