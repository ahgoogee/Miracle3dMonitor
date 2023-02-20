package miracle3d.monitor.general.impl;

import miracle3d.base.util.connection.IServerConnection;
import miracle3d.base.util.connection.impl.ServerConnection;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.command.impl.ServerCommander;
import miracle3d.monitor.messageparser.IMonitorMessageParser;
import miracle3d.monitor.messageparser.impl.MonitorMessageParser;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.referee.impl.DummyReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;
import miracle3d.monitor.worldmodel.impl.MonitorWorldModel;

public class MonitorComponentFactory {
    protected FactoryParameters params;

    public MonitorComponentFactory(FactoryParameters parameterObject) {
        this.params = parameterObject;
    }

    public IServerConnection createConnection(String host, int port) {
        return new ServerConnection(host, port);
    }

    public IMonitorMessageParser createMessageParser() {
        return new MonitorMessageParser();
    }

    public IServerCommander createServerCommander(IServerConnection connection) {
        return new ServerCommander(connection);
    }

    public IMonitorWorldModel createWorldModel() {
        return new MonitorWorldModel();
    }

    public IReferee createReferee(IMonitorWorldModel worldModel, IServerCommander serverCommander) {
        return new DummyReferee(worldModel, serverCommander);
    }
}
