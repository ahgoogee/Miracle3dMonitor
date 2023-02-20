package miracle3d.benchmark;

import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.general.impl.FactoryParameters;
import miracle3d.monitor.general.impl.MonitorComponentFactory;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public abstract class BenchmarkMonitorComponentFactory extends MonitorComponentFactory {
    protected BenchmarkParameters benchmarkParameters;
    public BenchmarkMonitorComponentFactory(BenchmarkParameters parameters) {
        super(parameters.getFactoryParameters());
        this.benchmarkParameters = parameters;
    }

    @Override
    public IReferee createReferee(IMonitorWorldModel worldModel, IServerCommander serverCommander) {

        return new BenchmarkRefereeBase(benchmarkParameters.getCycleTime(),benchmarkParameters.getCycleNumber(),worldModel,serverCommander);
    }
}
