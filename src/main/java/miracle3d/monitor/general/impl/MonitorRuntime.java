package miracle3d.monitor.general.impl;

import miracle3d.base.util.connection.ConnectionException;
import miracle3d.base.util.connection.IServerConnection;
import miracle3d.base.util.observer.IObserver;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.general.IMonitorRuntime;
import miracle3d.monitor.messageparser.IMonitorMessageParser;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

import java.util.ArrayList;

public class MonitorRuntime implements IObserver<byte[]>, IMonitorRuntime {

    protected IServerConnection connection;
    protected IMonitorMessageParser messageParser;
    protected IServerCommander commander;
    protected IMonitorWorldModel worldModel;
    protected IReferee referee;

    public MonitorRuntime(MonitorParameters parameter) {
        MonitorComponentFactory factory = parameter.getFactory();
        this.connection = factory.createConnection(parameter.getHost(), parameter.getPort());
        this.messageParser = factory.createMessageParser();
        this.commander = factory.createServerCommander(this.connection);
        this.worldModel = factory.createWorldModel();
        this.referee = factory.createReferee(this.worldModel, this.commander);
        this.connection.attach(this);
    }

    public void update(byte[] content) {
        this.messageParser.update(content);
        this.worldModel.update(this.messageParser);
        if (this.referee.decide()) {
            this.stopMonitor();
        }
   }

    public void startMonitor() throws ConnectionException {
        this.connection.establishConnection();
        this.connection.startReceiveLoop();
    }

    public boolean isConnected() {
        return this.connection.isConnected();
    }

    public void stopMonitor() {
        this.connection.stopReceiveLoop();
    }

    public IServerConnection getServerConnection() {
        return this.connection;
    }

    public IMonitorMessageParser getMessageParser() {
        return this.messageParser;
    }

    public IServerCommander getServerCommander() {
        return this.commander;
    }

    public IMonitorWorldModel getWorldModel() {
        return this.worldModel;
    }

    public IReferee getReferee() {
        return this.referee;
    }


}
