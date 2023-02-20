package miracle3d.base.util.connection.impl;

import miracle3d.base.util.connection.IServerConnection;
import miracle3d.base.util.observer.IObserver;
import miracle3d.base.util.observer.IPublishSubscribe;
import miracle3d.base.util.observer.Subject;

public abstract class ConnectionBase implements IServerConnection {
    protected final IPublishSubscribe<byte[]> observer = new Subject();
    protected boolean shutDown = false;
    protected boolean connected = false;

    public ConnectionBase() {
    }

    public void stopReceiveLoop() {
        this.shutDown = true;
    }

    public void attach(IObserver<byte[]> newObserver) {
        this.observer.attach(newObserver);
    }

    public boolean isConnected() {
        return this.connected;
    }
}