package pl.mational.rallyresulter.model;

public class Odcinek extends Event {
    private final String routePattern;
    private final int timeLimit;

    public Odcinek(String name, int round, String routePattern, int timeLimit) {
        super(name, "Odcinek", round);
        this.routePattern = routePattern;
        this.timeLimit = timeLimit;
    }

    @Override
    public String toString() {
        return super.toString() + ", Wzór przejazdu: " + routePattern + ", Limit czasu: " + timeLimit + " min";
    }
}
