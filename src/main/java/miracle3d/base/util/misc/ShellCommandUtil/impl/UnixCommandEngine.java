package miracle3d.base.util.misc.ShellCommandUtil.impl;


import miracle3d.base.util.misc.ShellCommandUtil.IShellCommandUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;

public class UnixCommandEngine extends IShellCommandUtil {
    public UnixCommandEngine() {
    }

    @Override
    public int getPID(Process process) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (process.getClass().getName().equals("java.lang.UNIXProcess")) {
            Field f = process.getClass().getDeclaredField("pid");
            f.setAccessible(true);
            return f.getInt(process);
        } else {
            return -1;
        }
    }

    @Override
    public boolean killProcessConditional(String pid, String name) {
        String command = "ps " + pid;

        try {
            Process ps = Runtime.getRuntime().exec(command);
            if (checkForOutput(ps, name)) {
                Runtime.getRuntime().exec("kill -9 " + pid);
                return true;
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean killAll(String name) {
        try {
            Runtime.getRuntime().exec("killall -9 " + name);
            return true;
        } catch (IOException var2) {
            var2.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkForOutput(Process ps, String text) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        boolean found = false;

        try {
            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.contains(text)) {
                    found = true;
                    break;
                }
            }

            reader.close();
            return found;
        } catch (IOException var6) {
            var6.printStackTrace();
            return false;
        }
    }

    @Override
    public String getOutput(Process ps) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getInputStream()));;
        String line;
        StringBuffer output = new StringBuffer();
        try{
            while ((line = reader.readLine())!=null)
            {
                output.append(line) ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output.toString();
    }

    @Override
    public String getError(Process ps) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getErrorStream()));;
        String line;
        StringBuffer error = new StringBuffer();
        try{
            while ((line = reader.readLine())!=null)
            {
                error.append(line) ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return error.toString();
    }

    @Override
    public Process launch(String command, String[] params, File path) {
        try {
            return Runtime.getRuntime().exec(command, params, path);
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    @Override
    public Process launchAndConsume(String... command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectOutput(new File("/dev/null"));
        builder.redirectError(new File("/dev/null"));
        return builder.start();
    }

    @Override
    public Process launchAndConsume(List command) throws IOException {
        return launchAndConsume((String[])command.toArray(new String[0]));

    }
}
