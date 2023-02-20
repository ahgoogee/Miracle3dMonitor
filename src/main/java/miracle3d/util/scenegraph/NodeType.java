package miracle3d.util.scenegraph;

public enum NodeType {
    BASE,
    TRANSFORM,
    LIGHT,
    MESH;

    private NodeType() {
    }

    public static NodeType determineNodeType(String type) {
        switch (type) {
            case "SMN":
            case "StaticMesh":
                return MESH;
            case "Light":
                return LIGHT;
            case "TRF":
                return TRANSFORM;
            default:
                return BASE;
        }
    }
}