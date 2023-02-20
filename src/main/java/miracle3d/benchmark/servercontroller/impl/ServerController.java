package miracle3d.benchmark.servercontroller.impl;

import miracle3d.base.util.misc.ShellCommandUtil.impl.ShellCommandUtil;
import miracle3d.base.util.platform.PlatformConstant;
import miracle3d.benchmark.servercontroller.IServerController;
import miracle3d.benchmark.servercontroller.ServerControllerParameters;
import miracle3d.benchmark.servercontroller.ServerLaunchMode;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerController implements IServerController {
    private final String rcsserver3d_rb =  "./config/rcssserver3d.rb";
    private ServerControllerParameters parameters;

    private Process server;


    public ServerController(ServerControllerParameters parameters) {
        this.parameters = parameters;
    }

    @Deprecated
    public int getPid() {
        return -1;
    }

    @Override
    public void startServer() throws IOException {
        if(parameters.getLaunchMode() == ServerLaunchMode.Fast)
        {
            ShellCommandUtil.getInstance().launchAndConsume("sed","-i","'s@\\$enableRealTimeMode = true@\\$enableRealTimeMode = false@'",rcsserver3d_rb);
        }
        else if(parameters.getLaunchMode() == ServerLaunchMode.Real)
        {
            ShellCommandUtil.getInstance().launchAndConsume("sed","-i","'s@\\$enableRealTimeMode = false@\\$enableRealTimeMode = true@'",rcsserver3d_rb);
        }

        //<agent-port> <monitor-port> <script-path>
        //String serverscript = parameters.getServerScriptPath().toString();
        String aport = String.valueOf(parameters.getServerport());
        String mport = String.valueOf(parameters.getMonitorport());
        String config = rcsserver3d_rb;
        //rcssserver3d --agent-port $1 --server-port $2 --script-path $3 > /dev/null 2>&1
        server = ShellCommandUtil.getInstance().launchAndConsume("rcssserver3d"
                                                                    ,"--agent-port",aport
                                                                    ,"--server-port",mport
                                                                    ,"--script-path",config);
    }

    @Override
    public void stopServer() {
        //ShellCommandUtil.getInstance().killProcessConditional(String.valueOf(getPid()) ,"rcssserver3d");
        stopAll();
    }

    @Override
    public void stopAll() {
        ShellCommandUtil.getInstance().killAll("rcssserver3d");
    }


    public Process getServerProcess() {
        return server;
    }
}
