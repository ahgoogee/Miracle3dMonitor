package miracle3d.base.util.misc.ShellCommandUtil.impl;

import miracle3d.base.util.misc.ShellCommandUtil.IShellCommandUtil;
import miracle3d.base.util.platform.Platform;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShellCommandUtil extends IShellCommandUtil {
    private IShellCommandUtil engine = null;

    private static ShellCommandUtil instance = null;

    public synchronized static IShellCommandUtil getInstance()
    {
        if(instance == null)
        {
            instance = new ShellCommandUtil();
        }
        return instance;
    }


    private ShellCommandUtil()
    {
        if(Platform.isWindows())
            engine = new WSLCommandEngine();
        else if (Platform.isLinux()) {
            engine = new UnixCommandEngine();

        }
    }

    @Override
    public int getPID(Process process) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        return engine.getPID(process);
    }

    @Override
    public boolean killProcessConditional(String pid, String name) {
        return engine.killProcessConditional(pid, name);
    }

    @Override
    public boolean killAll(String name) {
        return engine.killAll(name);
    }

    @Override
    public boolean checkForOutput(Process ps, String text) {
        return engine.checkForOutput(ps,text);
    }

    @Override
    public String getOutput(Process ps) {
        return engine.getOutput(ps);
    }

    @Override
    public String getError(Process ps) {
        return engine.getError(ps);
    }

    @Override
    public Process launch(String command, String[] params, File path) {
        return engine.launch(command,params,path);
    }

    @Override
    public Process launchAndConsume(String... command) throws IOException {
        return engine.launchAndConsume(command);
    }

    @Override
    public Process launchAndConsume(List command) throws IOException {
        return engine.launchAndConsume(command);
    }
}
