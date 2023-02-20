package miracle3d.monitor.referee.impl;

import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public class DummyReferee extends RefereeBase {
    public DummyReferee(IMonitorWorldModel mWorldModel, IServerCommander serverCommander) {
        super(mWorldModel, serverCommander, (String)null);
    }

    public boolean decide() {
        return false;
    }
}