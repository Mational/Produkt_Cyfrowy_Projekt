package pl.mational.rallyresulter.model;

public class Inne extends Event {
    private final double penaltyPoints;

    public Inne(String name, int round, double penaltyPoints) {
        super(name, "Inne", round);
        this.penaltyPoints = penaltyPoints;
    }

    @Override
    public String toString() {
        return super.toString() + ", Punkty karne: " + penaltyPoints;
    }
}
