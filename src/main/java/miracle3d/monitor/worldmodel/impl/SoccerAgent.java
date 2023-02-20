package miracle3d.monitor.worldmodel.impl;

import miracle3d.monitor.worldmodel.ISoccerAgent;
import miracle3d.util.scenegraph.IBaseNode;
import miracle3d.util.scenegraph.IMeshNode;
import miracle3d.util.scenegraph.ITransformNode;
import miracle3d.util.scenegraph.NodeType;

public class SoccerAgent extends SimulationObject implements ISoccerAgent {
    private String teamName;
    private int playerID;
    private String robotModel;

    public SoccerAgent(IBaseNode graphRoot, String teamName) {
        super(graphRoot);
        this.teamName = teamName;
        if (graphRoot != null) {
            IMeshNode meshNode = (IMeshNode)graphRoot.getNode(IMeshNode.class, "objName", "models/naobody[0-9]*[.]obj");
            if (meshNode != null && meshNode.getMaterials() != null) {
                String[] materials = meshNode.getMaterials();
                String[] var5 = materials;
                int var6 = materials.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String material = var5[var7];
                    if (material.startsWith("matNum") && material.length() > 6) {
                        String numberStr = material.substring(6, material.length());

                        try {
                            this.playerID = Integer.parseInt(numberStr);
                        } catch (NumberFormatException var11) {
                            System.out.println("Error parsing playerID in: " + numberStr);
                            var11.printStackTrace();
                        }
                    }
                }

                this.robotModel = meshNode.getObjName();
            }

            this.refresh(0.0F);
        }

    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void refresh(float deltaT) {
        if (this.graphRoot != null && this.graphRoot.getChildren() != null) {
            IBaseNode child = (IBaseNode)this.graphRoot.getChildren().get(0);
            if (child.getNodeType() == NodeType.TRANSFORM) {
                this.position = ((ITransformNode)child).getPosition();
            }

        }
    }


    public String getRobotModel() {
        return this.robotModel;
    }
}
