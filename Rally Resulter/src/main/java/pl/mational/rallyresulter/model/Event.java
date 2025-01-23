package pl.mational.rallyresulter.model;

public abstract class Event {
    private final String name;
    private final String type;
    private final int round;

    public Event(String name, String type, int round) {
        this.name = name;
        this.type = type;
        this.round = round;
    }

    @Override
    public String toString() {
        return name + " (" + type + "), Runda: " + round;
    }
}
