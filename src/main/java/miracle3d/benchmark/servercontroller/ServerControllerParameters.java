package miracle3d.benchmark.servercontroller;

import java.nio.file.Path;

public class ServerControllerParameters {
    private ServerLaunchMode launchMode;

    private int serverport;

    private int monitorport;

    public ServerControllerParameters( ServerLaunchMode launchMode, int serverport, int monitorport) {

        this.launchMode = launchMode;
        this.serverport = serverport;
        this.monitorport = monitorport;
    }



    public ServerLaunchMode getLaunchMode() {
        return launchMode;
    }
    public int getServerport() {
        return serverport;
    }

    public int getMonitorport() {
        return monitorport;
    }
}
