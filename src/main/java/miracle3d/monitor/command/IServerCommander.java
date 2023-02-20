package miracle3d.monitor.command;

import miracle3d.commons.spark.PlayMode;

public interface IServerCommander {
    void beamBall(float var1, float var2, float var3, float var4, float var5, float var6);

    void beamBall(float var1, float var2);

    void dropBall();

    void kickOff();

    void kickOff(Team var1);

    void movePlayer(Team var1, int var2, float var3, float var4, float var5);

    void moveRotatePlayer(Team var1, int var2, float var3, float var4, float var5, float var6);

    void setPlaymode(PlayMode var1);

    void killServer();

    void sendMessage(String var1);

    void setTime(float var1);

    void setScore(int var1, int var2);
}
