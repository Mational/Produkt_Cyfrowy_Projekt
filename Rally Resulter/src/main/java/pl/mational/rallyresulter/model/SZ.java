package pl.mational.rallyresulter.model;

public class SZ extends Event {
    private final double maxPoints;

    public SZ(String name, int round, double maxPoints) {
        super(name, "SZ", round);
        this.maxPoints = maxPoints;
    }

    @Override
    public String toString() {
        return super.toString() + ", Maksymalna liczba punktów: " + maxPoints;
    }
}
