package miracle3d.benchmark.servercontroller;

import java.io.IOException;

public interface IServerController {
    public void startServer() throws IOException;
    public void stopServer();

    public void stopAll();
}
