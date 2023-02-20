package miracle3d.monitor.worldmodel.impl;

import miracle3d.monitor.worldmodel.ISoccerBall;
import miracle3d.util.scenegraph.NodeType;
import miracle3d.util.scenegraph.impl.TransformNode;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class SoccerBall extends SimulationObject implements ISoccerBall {
    private float radius;
    private float mass;

    public SoccerBall() {
    }

    public float getRadius() {
        return this.radius;
    }

    public float getMass() {
        return this.mass;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void refresh(float deltaT) {
        if (this.graphRoot != null && this.graphRoot.getNodeType() == NodeType.TRANSFORM) {
            float[] matrix = ((TransformNode)this.graphRoot).getLocalTransformation();
            this.position = new Vector3D((double)matrix[12], (double)matrix[13], (double)matrix[14]);
        }

    }
}