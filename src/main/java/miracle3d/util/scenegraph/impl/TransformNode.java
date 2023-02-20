package miracle3d.util.scenegraph.impl;


import miracle3d.util.scenegraph.IBaseNode;
import miracle3d.util.scenegraph.ITransformNode;
import miracle3d.util.scenegraph.NodeType;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class TransformNode extends BaseNode implements ITransformNode {
    private float[] localTransform;
    private Vector3D position;
    private Rotation orientation;

    public TransformNode() {
    }

    public TransformNode(TransformNode other) {
        this.setLocalTransform(other.getLocalTransformation());
    }

    public NodeType getNodeType() {
        return NodeType.TRANSFORM;
    }

    public float[] getLocalTransformation() {
        return this.localTransform;
    }

    public Rotation getOrientation() {
        return this.orientation;
    }

    public Vector3D getPosition() {
        return this.position;
    }

    public void setLocalTransform(float[] localTransform) {
        this.localTransform = localTransform;
        this.updatePosition();
        this.updateOrientation();
    }

    public void update(IBaseNode other) {
        if (other.getNodeType() == NodeType.TRANSFORM) {
            ITransformNode newTransform = (ITransformNode)other;
            if (newTransform.getLocalTransformation() != null) {
                this.setLocalTransform(newTransform.getLocalTransformation());
            }
        }

        super.update(other);
    }

    private void updatePosition() {
        if (this.localTransform != null) {
            float[] f = this.localTransform;
            this.position = new Vector3D((double)f[12], (double)f[13], (double)f[14]);
        }

    }

    private void updateOrientation() {
        if (this.localTransform != null) {
            float[] f = this.localTransform;
            double[][] rotation = new double[][]{{(double)f[0], (double)f[4], (double)f[8]}, {(double)f[1], (double)f[5], (double)f[9]}, {(double)f[2], (double)f[6], (double)f[10]}};
            this.orientation = new Rotation(rotation, 0.1);
        }

    }
}
