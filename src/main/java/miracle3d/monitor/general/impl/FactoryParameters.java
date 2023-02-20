package miracle3d.monitor.general.impl;

public class FactoryParameters {

    @Deprecated
    //TODO wsl平台获取pid有困难
    private String serverPid;
    private String serverIP;

    public FactoryParameters(String serverPid, String serverIP) {
        this.serverPid = serverPid;
        this.serverIP = serverIP;
    }

    @Deprecated
    public String getServerPid() {
        return this.serverPid;
    }

    public String getServerIP() {
        return this.serverIP;
    }
}
