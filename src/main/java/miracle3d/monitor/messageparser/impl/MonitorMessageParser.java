package miracle3d.monitor.messageparser.impl;

import miracle3d.base.util.symboltreeparser.SymbolNode;
import miracle3d.base.util.symboltreeparser.SymbolTreeParser;
import miracle3d.monitor.messageparser.IMonitorMessageParser;
import miracle3d.monitor.messageparser.ISimulationState;
import miracle3d.util.scenegraph.impl.BaseNode;
import miracle3d.util.scenegraph.impl.SceneGraph;
import miracle3d.util.scenegraph.impl.SceneGraphHeader;

import java.io.UnsupportedEncodingException;

public class MonitorMessageParser implements IMonitorMessageParser {
    private ISimulationState simulationState;
    private SymbolTreeParser treeParser = new SymbolTreeParser();
    private RSGConverter rsgConverter = new RSGConverter();
    private SceneGraph sceneGraph;

    public MonitorMessageParser() {
    }

    public ISimulationState getSimulationState() {
        return this.simulationState;
    }

    public SceneGraph getSceneGraph() {
        return this.sceneGraph;
    }

    public void update(byte[] message) {
        try {
            this.update(new String(message, 0, message.length, "UTF-8"));
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

    }

    public void update(String content) {
        this.simulationState = null;
        this.sceneGraph = null;
        try {
            SymbolNode root = this.treeParser.parse(content);
            ISimulationState currentState = this.rsgConverter.convertSimulationState((SymbolNode)root.children.get(0));
            SceneGraphHeader graphHeader = this.rsgConverter.convertSceneGraphHeader((SymbolNode)root.children.get(1));
            BaseNode graphRoot = this.rsgConverter.convertSceneGraphNode((SymbolNode)root.children.get(2));
            this.simulationState = currentState;
            this.sceneGraph = new SceneGraph(graphHeader, graphRoot);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}