package TestMain;

import miracle3d.base.util.misc.ShellCommandUtil.impl.ShellCommandUtil;
import miracle3d.base.util.platform.PlatformConstant;
import miracle3d.benchmark.servercontroller.ServerControllerParameters;
import miracle3d.benchmark.servercontroller.ServerLaunchMode;
import miracle3d.benchmark.servercontroller.impl.ServerController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestMain {
    public static void main(String[] args) {

        //TestMainMonitor monitor = new TestMainMonitor();
        //monitor.startMonitor();
/*
        ServerControllerParameters parameters =
                new ServerControllerParameters(Paths.get(MavenConstant.resources+"/script/startServer.sh") ,
                        ServerLaunchMode.Real, 3100, 3200);

        ServerController SC = new ServerController(parameters);


        try{
            SC.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

        try{
            Process process = ShellCommandUtil.getInstance().launchAndConsume("./abc.sh");
            System.out.println(ShellCommandUtil.getInstance().getOutput(process));
            System.out.println(ShellCommandUtil.getInstance().getError(process));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
