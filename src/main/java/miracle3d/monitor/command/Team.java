package miracle3d.monitor.command;

public enum Team {
    LEFT("Left"),
    RIGHT("Right"),
    RANDOM("None");

    private String text;

    private Team(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}
