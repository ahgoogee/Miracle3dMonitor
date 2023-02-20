package miracle3d.benchmark.RunBenchmark;

import miracle3d.benchmark.BenchmarkMonitorComponentFactory;
import miracle3d.benchmark.BenchmarkParameters;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public class RunBenchmarkMonitorComponentFactory extends BenchmarkMonitorComponentFactory {
    public RunBenchmarkMonitorComponentFactory(BenchmarkParameters benchmarkParameters) {
        super(benchmarkParameters);
    }

    @Override
    public IReferee createReferee(IMonitorWorldModel worldModel, IServerCommander serverCommander) {
        return new RunBenchmarkReferee(benchmarkParameters.getCycleTime(),benchmarkParameters.getCycleNumber(),worldModel, serverCommander);
    }
}
