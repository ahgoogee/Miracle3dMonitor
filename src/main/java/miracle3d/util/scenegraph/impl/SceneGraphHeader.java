package miracle3d.util.scenegraph.impl;


import miracle3d.util.scenegraph.ISceneGraphHeader;

public class SceneGraphHeader implements ISceneGraphHeader {
    private String type;
    private int majorVersion;
    private int minorVersion;

    public SceneGraphHeader() {
    }

    public SceneGraphHeader(ISceneGraphHeader other) {
        this.type = other.getType();
        this.majorVersion = other.getMajorVersion();
        this.minorVersion = other.getMinorVersion();
    }

    public SceneGraphHeader(String type, int majorVersion, int minorVersion) {
        this.type = type;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    public String getType() {
        return this.type;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public void update(ISceneGraphHeader other) {
        this.setType(other.getType());
        this.setMajorVersion(other.getMajorVersion());
        this.setMinorVersion(other.getMinorVersion());
    }
}
