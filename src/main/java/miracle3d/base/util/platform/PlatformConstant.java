package miracle3d.base.util.platform;


import miracle3d.base.util.misc.ShellCommandUtil.impl.ShellCommandUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Deprecated
public class PlatformConstant {
    private String projectroot ;
    private static PlatformConstant instance = null;

    public synchronized static PlatformConstant getInstance()
    {
        if(instance == null)
        {
            instance = new PlatformConstant();
        }
        return instance;
    }

    public String getProjectRoot()
    {
        if(projectroot == null)
        {
            if(Platform.isLinux())
            {
                //TODO 待测试正确性->待删除
                //projectroot = ClassLoader.getSystemResource("").toString()+"../";
            } else if (Platform.isWindows()) {
                try {
                    Process process = ShellCommandUtil.getInstance().launchAndConsume("pwd");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    projectroot = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return projectroot;
    }

}
