package miracle3d.util.scenegraph;

import java.util.ArrayList;

public interface IBaseNode extends Cloneable {
    NodeType getNodeType();

    void setParent(IBaseNode var1);

    IBaseNode getParent();

    ArrayList<IBaseNode> getChildren();

    IBaseNode clone();

    boolean structurallyEquals(IBaseNode var1);

    void update(IBaseNode var1);

    <T extends IBaseNode> T getNode(Class<T> var1, String var2, String var3);
}
