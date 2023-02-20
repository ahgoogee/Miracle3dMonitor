package miracle3d.monitor.command.impl;


import miracle3d.base.util.connection.ConnectionException;
import miracle3d.base.util.connection.IServerConnection;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.command.Team;
import miracle3d.commons.spark.PlayMode;

public class ServerCommander implements IServerCommander {
    private final IServerConnection connection;

    public ServerCommander(IServerConnection connection) {
        this.connection = connection;
    }

    public void sendMessage(String msg) {
        try {
            this.connection.sendMessage(msg.getBytes());
        } catch (ConnectionException var3) {
            var3.printStackTrace();
        }

    }

    public void beamBall(float x, float y, float z, float vx, float vy, float vz) {
        this.sendMessage("(ball (pos " + x + " " + y + " " + z + ") (vel " + vx + " " + vy + " " + vz + "))");
    }

    public void beamBall(float x, float y) {
        this.beamBall(x, y, 0.042F, 0.0F, 0.0F, 0.0F);
    }

    public void dropBall() {
        this.sendMessage("(dropBall)");
    }

    public void kickOff() {
        this.kickOff(Team.RANDOM);
    }

    public void kickOff(Team k) {
        this.sendMessage("(kickOff " + k + ")");
    }

    public void movePlayer(Team team, int playerNumber, float x, float y, float z) {
        if (team != Team.RANDOM) {
            String msg = "(agent (unum " + playerNumber + ") (team " + team + ") (pos " + x + " " + y + " " + z + "))";
            this.sendMessage(msg);
        }
    }

    public void moveRotatePlayer(Team team, int playerNumber, float x, float y, float z, float rot) {
        if (team != Team.RANDOM) {
            this.sendMessage("(agent (unum " + playerNumber + ") (team " + team + ") (move " + x + " " + y + " " + z + " " + rot + "))");
        }
    }

    public void setPlaymode(PlayMode playmode) {
        if (playmode != PlayMode.NONE) {
            this.sendMessage("(playMode " + playmode.getServerString() + ")");
        }
    }

    public void killServer() {
        this.sendMessage("(killsim)");
    }

    public void setTime(float time) {
        if (!(time < 0.0F)) {
            this.sendMessage("(time " + time + ")");
        }
    }

    public void setScore(int left, int right) {
        if (left >= 0 && right >= 0) {
            this.sendMessage("(score (left " + left + ") (right " + right + "))");
        }
    }
}
