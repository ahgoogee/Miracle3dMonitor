package miracle3d.monitor.general;

import miracle3d.base.util.connection.IServerConnection;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.messageparser.IMonitorMessageParser;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public interface IMonitorRuntime {
    IServerConnection getServerConnection();

    IMonitorMessageParser getMessageParser();

    IServerCommander getServerCommander();

    IMonitorWorldModel getWorldModel();

    IReferee getReferee();

}
