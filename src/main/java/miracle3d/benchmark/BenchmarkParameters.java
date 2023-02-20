package miracle3d.benchmark;

import miracle3d.benchmark.RunBenchmark.RunBenchmarkMonitorComponentFactory;
import miracle3d.benchmark.playercontroller.PlayerControllerParameters;
import miracle3d.benchmark.servercontroller.ServerControllerParameters;
import miracle3d.monitor.general.impl.FactoryParameters;
import miracle3d.monitor.general.impl.MonitorComponentFactory;
import miracle3d.monitor.general.impl.MonitorParameters;

public class BenchmarkParameters {

    private float cycletime;
    private int cyclenumber;

    private BenchmarkType benchmarktype;

    private FactoryParameters factoryParameters;

    public MonitorParameters getMonitorParameters() {
        return monitorParameters;
    }

    private MonitorParameters monitorParameters;

    private ServerControllerParameters serverParameters;

    private PlayerControllerParameters playerParameters;

    public BenchmarkParameters( String serverhost, int serverport,int cycletime, int cyclenumber,ServerControllerParameters serverParameters, PlayerControllerParameters playerParameters) {
        this.cycletime = cycletime;
        this.cyclenumber = cyclenumber;
        this.benchmarktype = playerParameters.getBenchmarkType();
        this.factoryParameters = createFactoryParameters(serverhost,serverport);
        this.monitorParameters = createMonitorParameters(serverhost,serverport);
        this.serverParameters = serverParameters;
        this.playerParameters = playerParameters;
    }

    private FactoryParameters createFactoryParameters(String host,int port)
    {
        return new FactoryParameters("-1",host);
    }

    private MonitorParameters createMonitorParameters(String host,int port)
    {
        return  new MonitorParameters(host,port,new MonitorComponentFactory(getFactoryParameters()));
    }

    public float getCycleTime() {
        return cycletime;
    }

    public int getCycleNumber() {
        return cyclenumber;
    }
    public BenchmarkType getBenchmarkType() {
        return benchmarktype;
    }

    public FactoryParameters getFactoryParameters() {
        return factoryParameters;
    }

    public ServerControllerParameters getServerParameters() {
        return serverParameters;
    }

    public PlayerControllerParameters getPlayerParameters() {
        return playerParameters;
    }
}
