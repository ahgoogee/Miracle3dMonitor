package miracle3d.util.scenegraph;

public interface ILightNode extends IBaseNode {
    float[] getDiffuse();

    float[] getAmbient();

    float[] getSpecular();
}
