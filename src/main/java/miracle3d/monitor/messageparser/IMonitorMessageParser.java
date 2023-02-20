package miracle3d.monitor.messageparser;

import miracle3d.base.util.observer.IObserver;
import miracle3d.util.scenegraph.impl.SceneGraph;

public interface IMonitorMessageParser extends IObserver<byte[]> {
    ISimulationState getSimulationState();

    SceneGraph getSceneGraph();
}
