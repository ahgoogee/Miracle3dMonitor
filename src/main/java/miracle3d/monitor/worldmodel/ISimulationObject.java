package miracle3d.monitor.worldmodel;

import miracle3d.util.scenegraph.IBaseNode;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public interface ISimulationObject {
    Vector3D getPosition();

    //IBaseNode getGraphRoot();
}
