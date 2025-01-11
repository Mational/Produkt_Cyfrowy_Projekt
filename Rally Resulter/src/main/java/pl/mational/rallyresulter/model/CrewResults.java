package pl.mational.rallyresulter.model;

public record CrewResults(
        int crewId,
        String crewNames,
        String crewClubs,
        int brdPpTestPoints,
        int touristicTestPoints,
        int roadTestPoints,
        int roadCardPoints,
        int finalResult
) {
}
