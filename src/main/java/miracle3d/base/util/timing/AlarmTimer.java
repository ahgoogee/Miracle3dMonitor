package miracle3d.base.util.timing;


public class AlarmTimer implements Runnable {
    private long timeout;
    private boolean wokeUp;
    private ITriggerReceiver sleeper;
    private String name;

    public AlarmTimer(String name, ITriggerReceiver sleeper, long timeout) {
        this.name = name;
        this.sleeper = sleeper;
        this.timeout = timeout;
        this.wokeUp = false;
        Thread t = new Thread(this, name);
        t.setDaemon(true);
        t.start();
    }

    public synchronized void stopAlarm() {
        this.wokeUp = true;
        this.notify();
    }

    public synchronized void run() {
        long alarmTime = System.currentTimeMillis() + this.timeout;

        long currentTime;
        while(!this.wokeUp && alarmTime > (currentTime = System.currentTimeMillis())) {
            try {
                this.wait(alarmTime - currentTime);
            } catch (InterruptedException var6) {
            }
        }

        if (!this.wokeUp) {
            this.sleeper.trigger(this.name);
        }

    }

    public String toString() {
        return "Alarm: " + this.name + " timeout: " + this.timeout;
    }
}
