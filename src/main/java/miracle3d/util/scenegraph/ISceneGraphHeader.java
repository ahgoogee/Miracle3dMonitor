package miracle3d.util.scenegraph;

public interface ISceneGraphHeader {
    String getType();

    int getMajorVersion();

    int getMinorVersion();

    void update(ISceneGraphHeader var1);
}
