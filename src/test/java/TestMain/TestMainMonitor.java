package TestMain;

import miracle3d.base.util.connection.ConnectionException;
import miracle3d.monitor.general.impl.FactoryParameters;
import miracle3d.monitor.general.impl.MonitorComponentFactory;
import miracle3d.monitor.general.impl.MonitorParameters;

public class TestMainMonitor  {
    private TestMonitorRuntime runtime;

    public TestMainMonitor() {
        this.runtime = createMonitorRuntime();
    }

    private TestMonitorRuntime createMonitorRuntime(){
        FactoryParameters factoryParameters = new FactoryParameters("-1","localhost");
        MonitorComponentFactory monitorComponentFactory = new TestMonitorComponentFactory(factoryParameters);
        MonitorParameters monitorParameters = new MonitorParameters(monitorComponentFactory);
        TestMonitorRuntime monitorRuntime = new TestMonitorRuntime(monitorParameters);
        return monitorRuntime;
    }

    public void startMonitor()
    {
        try {
            runtime.startMonitor();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

}
