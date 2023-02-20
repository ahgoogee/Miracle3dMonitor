package miracle3d.monitor.referee;

public interface IReferee {
    boolean decide();

    RefereeState getState();

    public static enum RefereeState {
        CREATED,
        CONNECTED,
        BEAMED,
        STARTED,
        STOPPED,
        FAILED;

        private RefereeState() {
        }
    }
}