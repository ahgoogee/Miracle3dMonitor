package miracle3d.monitor.worldmodel;

import miracle3d.base.util.observer.IObserver;
import miracle3d.commons.spark.Foul;
import miracle3d.commons.spark.PlayMode;
import miracle3d.monitor.messageparser.IMonitorMessageParser;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
public interface IMonitorWorldModel extends IObserver<IMonitorMessageParser> {
    //SceneGraph getSceneGraph();

    //boolean hasSceneGraphStructureChanged();

    Vector3D getFieldDimensions();

    Vector3D getGoalDimensions();

    float getTime();

    String getLeftTeamName();

    String getRightTeamName();

    int getScoreLeft();

    int getScoreRight();

    PlayMode getPlayMode();

    int getHalf();

    ISoccerBall getBall();

    List<? extends ISoccerAgent> getSoccerAgents();

    List<Foul> getFouls();
}
