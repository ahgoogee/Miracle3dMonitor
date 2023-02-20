package miracle3d.util.scenegraph.impl;


import miracle3d.util.scenegraph.IBaseNode;
import miracle3d.util.scenegraph.ILightNode;
import miracle3d.util.scenegraph.NodeType;

public class LightNode extends BaseNode implements ILightNode {
    private float[] diffuse;
    private float[] ambient;
    private float[] specular;

    public LightNode() {
    }

    public NodeType getNodeType() {
        return NodeType.LIGHT;
    }

    public float[] getDiffuse() {
        return this.diffuse;
    }

    public float[] getAmbient() {
        return this.ambient;
    }

    public float[] getSpecular() {
        return this.specular;
    }

    public void setDiffuse(float[] diffuse) {
        this.diffuse = diffuse;
    }

    public void setAmbient(float[] ambient) {
        this.ambient = ambient;
    }

    public void setSpecular(float[] specular) {
        this.specular = specular;
    }

    public void update(IBaseNode other) {
        if (other.getNodeType() == NodeType.LIGHT) {
            ILightNode newLight = (ILightNode)other;
            if (newLight.getDiffuse() != null) {
                this.diffuse = newLight.getDiffuse();
            }

            if (newLight.getAmbient() != null) {
                this.ambient = newLight.getAmbient();
            }

            if (newLight.getSpecular() != null) {
                this.specular = newLight.getSpecular();
            }
        }

    }

    public <T extends IBaseNode> T getNode(Class<T> nodeType, String property, String value) {
        return super.getNode(nodeType, property, value);
    }

    public String toString() {
        String ret = "(" + this.getNodeType();
        ret = ret + " diffuse=" + this.diffuse;
        ret = ret + " ambient=" + this.ambient;
        ret = ret + " specular=" + this.specular;
        return ret + ")";
    }
}
