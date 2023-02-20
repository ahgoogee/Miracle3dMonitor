package miracle3d.util.scenegraph.impl;


import miracle3d.util.scenegraph.IBaseNode;
import miracle3d.util.scenegraph.IMeshNode;
import miracle3d.util.scenegraph.NodeType;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class MeshNode extends BaseNode implements IMeshNode {
    private Boolean isVisible;
    private Boolean isTransparent;
    private String objName;
    private Vector3D scale;
    private String[] materials;

    public MeshNode() {
    }

    public NodeType getNodeType() {
        return NodeType.MESH;
    }

    public Boolean isVisible() {
        return this.isVisible;
    }

    public Boolean isTransparent() {
        return this.isTransparent;
    }

    public String getObjName() {
        return this.objName;
    }

    public Vector3D getScale() {
        return this.scale;
    }

    public String[] getMaterials() {
        return this.materials;
    }

    public void setVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void setTransparent(Boolean isTransparent) {
        this.isTransparent = isTransparent;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    public void setMaterials(String[] materials) {
        this.materials = materials;
    }

    public void update(IBaseNode other) {
        if (other.getNodeType() == NodeType.MESH) {
            IMeshNode newMesh = (IMeshNode)other;
            if (newMesh.isVisible() != null) {
                this.isVisible = newMesh.isVisible();
            }

            if (newMesh.isTransparent() != null) {
                this.isTransparent = newMesh.isTransparent();
            }

            if (newMesh.getObjName() != null) {
                this.objName = newMesh.getObjName();
            }

            if (newMesh.getScale() != null) {
                this.scale = newMesh.getScale();
            }

            if (newMesh.getMaterials() != null) {
                this.materials = newMesh.getMaterials();
            }
        }

    }

    public <T extends IBaseNode> T getNode(Class<T> nodeType, String property, String value) {
        if (!nodeType.isInstance(this)) {
            return null;
        } else if (property == null) {
            return (T) nodeType.cast(this);
        } else {
            if ("objName".equals(property)) {
                if (this.objName == null) {
                    if (value == null) {
                        return (T) nodeType.cast(this);
                    }
                } else if (this.objName.matches(value)) {
                    return (T) nodeType.cast(this);
                }
            } else if ("materials".equals(property)) {
                if (this.materials == null) {
                    if (value == null) {
                        return (T) nodeType.cast(this);
                    }
                } else {
                    for(int i = 0; i < this.materials.length; ++i) {
                        if (this.materials[i].matches(value)) {
                            return (T) nodeType.cast(this);
                        }
                    }
                }
            }

            return null;
        }
    }

    public String toString() {
        String ret = "(" + this.getNodeType();
        ret = ret + " isVisible=" + this.isVisible;
        ret = ret + " isTransparent=" + this.isTransparent;
        ret = ret + " objName=" + this.objName;
        ret = ret + " scale=" + this.scale;
        ret = ret + " materials=" + this.materials;
        return ret + ")";
    }
}
