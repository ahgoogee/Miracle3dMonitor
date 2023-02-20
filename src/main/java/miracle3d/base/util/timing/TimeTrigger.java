package miracle3d.base.util.timing;


public class TimeTrigger implements Runnable {
    private long timeout;
    private ITriggerReceiver receiver;
    private String name;
    private boolean stopped;

    public TimeTrigger(String name, ITriggerReceiver receiver, long timeout) {
        this.name = name;
        this.receiver = receiver;
        this.timeout = timeout;
        Thread t = new Thread(this, name);
        t.setDaemon(true);
        t.start();
    }

    public void stop() {
        this.stopped = true;
    }

    public void run() {
        this.start();
    }

    public void start() {
        while(!this.stopped) {
            try {
                Thread.sleep(this.timeout);
                if (!this.stopped) {
                    this.receiver.trigger(this.name);
                }
            } catch (InterruptedException var2) {
            }
        }

    }

    public String toString() {
        return "Timer: " + this.name + " timeout: " + this.timeout;
    }
}
