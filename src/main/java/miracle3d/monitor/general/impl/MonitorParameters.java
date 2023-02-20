package miracle3d.monitor.general.impl;

public class MonitorParameters {
    private String host;
    private int port;
    private MonitorComponentFactory factory;

    public MonitorParameters(MonitorComponentFactory factory) {
        this("localhost", 3200, factory);
    }

    public MonitorParameters(String host, int port, MonitorComponentFactory factory) {
        this.host = host;
        this.port = port;
        this.factory = factory;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public MonitorComponentFactory getFactory() {
        return this.factory;
    }
}
