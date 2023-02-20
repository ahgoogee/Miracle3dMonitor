package miracle3d.benchmark;

import miracle3d.base.util.connection.ConnectionException;

public class BenchmarkMain {
    private BenchmarkParameters parameters;
    private BenchmarkMonitorRuntime runtime;

    public BenchmarkMain(BenchmarkParameters params)
    {
        this.parameters = params;
        this.runtime = createMonitorRuntime();
    }

    private BenchmarkMonitorRuntime createMonitorRuntime(){

        if(parameters.getBenchmarkType() == BenchmarkType.RUN)
            return new BenchmarkMonitorRuntime (parameters.getMonitorParameters());
        else
            //TODO 待填写分支
            return null;

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
