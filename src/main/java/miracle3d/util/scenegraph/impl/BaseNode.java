package miracle3d.util.scenegraph.impl;


import miracle3d.util.scenegraph.IBaseNode;
import miracle3d.util.scenegraph.NodeType;

import java.util.ArrayList;
import java.util.Iterator;

public class BaseNode implements IBaseNode {
    private IBaseNode parent;
    private ArrayList<IBaseNode> children;

    public BaseNode() {
    }

    public BaseNode(IBaseNode other) {
        ArrayList<IBaseNode> otherChildren = other.getChildren();
        if (otherChildren != null) {
            Iterator var3 = otherChildren.iterator();

            while(var3.hasNext()) {
                IBaseNode child = (IBaseNode)var3.next();
                this.addChildNode(child.clone());
            }
        }

    }

    public NodeType getNodeType() {
        return NodeType.BASE;
    }

    public void setParent(IBaseNode parent) {
        this.parent = parent;
    }

    public IBaseNode getParent() {
        return this.parent;
    }

    public ArrayList<IBaseNode> getChildren() {
        return this.children;
    }

    public boolean addChildNode(IBaseNode childNode) {
        if (this.children == null) {
            this.children = new ArrayList();
        }

        childNode.setParent(this);
        return this.children.add(childNode);
    }

    public IBaseNode clone() {
        BaseNode copy = null;

        try {
            copy = (BaseNode)super.clone();
            if (this.children != null) {
                copy.children = new ArrayList();
                Iterator var2 = this.children.iterator();

                while(var2.hasNext()) {
                    IBaseNode child = (IBaseNode)var2.next();
                    copy.addChildNode(child.clone());
                }
            }
        } catch (CloneNotSupportedException var4) {
            var4.printStackTrace();
        }

        return copy;
    }

    public boolean structurallyEquals(IBaseNode other) {
        if (this.children == null && other.getChildren() == null) {
            return true;
        } else if (this.children != null && other.getChildren() != null) {
            return this.children.size() == other.getChildren().size();
        } else {
            return false;
        }
    }

    public void update(IBaseNode other) {
        ArrayList<IBaseNode> otherChildren = other.getChildren();
        if (this.children != null || otherChildren != null) {
            if (this.children != null && otherChildren != null && this.children.size() == otherChildren.size()) {
                for(int i = 0; i < this.children.size(); ++i) {
                    ((IBaseNode)this.children.get(i)).update((IBaseNode)otherChildren.get(i));
                }
            }

        }
    }

    public <T extends IBaseNode> T getNode(Class<T> nodeType, String property, String value) {
        if (nodeType.isInstance(this)) {
            return (T) nodeType.cast(this);
        } else {
            if (this.children != null) {
                Iterator var5 = this.children.iterator();

                while(var5.hasNext()) {
                    IBaseNode child = (IBaseNode)var5.next();
                    T tempNode = child.getNode(nodeType, property, value);
                    if (tempNode != null) {
                        return tempNode;
                    }
                }
            }

            return null;
        }
    }

    public String toString() {
        if (this.children == null) {
            return "(" + this.getNodeType() + ")";
        } else {
            String ret = "";

            IBaseNode child;
            for(Iterator var2 = this.children.iterator(); var2.hasNext(); ret = ret + child.toString()) {
                child = (IBaseNode)var2.next();
            }

            return "(" + ret + ")";
        }
    }
}
