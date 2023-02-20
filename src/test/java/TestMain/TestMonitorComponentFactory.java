package TestMain;

import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.general.impl.FactoryParameters;
import miracle3d.monitor.general.impl.MonitorComponentFactory;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public class TestMonitorComponentFactory extends MonitorComponentFactory {
    public TestMonitorComponentFactory(FactoryParameters parameterObject) {
        super(parameterObject);
    }

    @Override
    public IReferee createReferee(IMonitorWorldModel worldModel, IServerCommander serverCommander) {
        return new TestReferee(worldModel,serverCommander, params.getServerPid());
    }


}
