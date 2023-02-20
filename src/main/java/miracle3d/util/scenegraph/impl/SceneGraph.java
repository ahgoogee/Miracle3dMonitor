package miracle3d.util.scenegraph.impl;


import miracle3d.util.scenegraph.ISceneGraph;

public class SceneGraph implements ISceneGraph {
    private final SceneGraphHeader header;
    private final BaseNode rootNode;

    public SceneGraph(ISceneGraph other) {
        this.header = new SceneGraphHeader(other.getHeader());
        this.rootNode = new BaseNode(other.getRootNode());
    }

    public SceneGraph(SceneGraphHeader header, BaseNode rootNode) {
        this.header = header;
        this.rootNode = rootNode;
    }

    public BaseNode getRootNode() {
        return this.rootNode;
    }

    public SceneGraphHeader getHeader() {
        return this.header;
    }

    public String toString() {
        String ret = "SceneGraph";
        if (this.header != null) {
            ret = ret + " " + this.header.getType() + " " + this.header.getMajorVersion() + "." + this.header.getMinorVersion();
        }

        ret = ret + ":\n RSG-Graph:";
        if (this.rootNode != null) {
            ret = ret + this.rootNode.toString();
        }

        return ret;
    }

    public void update(ISceneGraph sceneGraphUpdate) {
        this.header.update(sceneGraphUpdate.getHeader());
        this.rootNode.update(sceneGraphUpdate.getRootNode());
    }
}
