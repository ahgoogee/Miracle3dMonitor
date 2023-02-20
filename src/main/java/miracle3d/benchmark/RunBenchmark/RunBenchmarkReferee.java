package miracle3d.benchmark.RunBenchmark;

import miracle3d.benchmark.BenchmarkRefereeBase;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public class RunBenchmarkReferee extends BenchmarkRefereeBase {
    public RunBenchmarkReferee(float _cycletime,int _cyclenumber ,IMonitorWorldModel mWorldModel, IServerCommander serverCommander) {
        super(_cycletime,_cyclenumber,mWorldModel, serverCommander);
    }
}
