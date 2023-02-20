package miracle3d.base.util.misc.ShellCommandUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract  class IShellCommandUtil<T extends IShellCommandUtil> {
    @Deprecated
    public abstract int getPID(Process process) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException ;
    @Deprecated
    public abstract boolean killProcessConditional(String pid, String name);

    public abstract boolean killAll(String name);

    public abstract boolean checkForOutput(Process ps, String text);

    //TODO 死循环风险
    public abstract String getOutput(Process ps);

    //TODO 死循环风险
    public abstract String getError(Process ps);

    public abstract Process launch(String command, String[] params, File path) ;

    public abstract Process launchAndConsume(String... command) throws IOException ;

    public abstract  Process launchAndConsume(List<String> command) throws IOException ;
}
