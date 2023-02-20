package miracle3d.benchmark;

import miracle3d.commons.spark.PlayMode;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.referee.impl.RefereeBase;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public class BenchmarkRefereeBase extends RefereeBase {
    private int decideNumber;
    private boolean inited;

    private float cycletime;
    private int cyclenumber;

    private int current_cyclenumber;



    public BenchmarkRefereeBase(float _cycletime,int _cyclenumber ,IMonitorWorldModel mWorldModel, IServerCommander serverCommander) {
        super(mWorldModel, serverCommander, "-1");
        decideNumber = 0;
        this.inited = false;

        this.cycletime = _cycletime;
        this.cyclenumber = _cyclenumber;
    }

    private int validNumber = 0;
    private boolean checkplayer()
    {
        if(numberOfPlayer()<1)
            return true;

        if(validNumber>=10)
        {
            return false;
        }

        if(hasFallen())
        {
            validNumber = 0;
        }
        else{
            validNumber++;
        }
        return true;
    }
    private boolean init()
    {
        if(!inited)
        {
            if(worldModel.getPlayMode()== PlayMode.BEFORE_KICK_OFF) {
                inited = true;
                state = RefereeState.BEAMED;
                return false;
            }

            serverCommander.setTime(0.0f);
            serverCommander.setPlaymode(PlayMode.BEFORE_KICK_OFF);
            validNumber=0;
            return true;
        }
        return false;
    }

    private boolean start()
    {

        if(worldModel.getPlayMode()!=PlayMode.PLAY_ON)
        {
            serverCommander.setPlaymode(PlayMode.PLAY_ON);
            return true;
        }
        else {
            state = RefereeState.STARTED;
            return false;
        }

    }

    private boolean stop()
    {
        if(decideNumber%10==0)
            System.out.println(worldModel.getTime());

        if(worldModel.getTime() >= (float) cycletime) {
            current_cyclenumber++;
            if(current_cyclenumber>cyclenumber)
                return false;
            inited = false;
            state = RefereeState.STOPPED;
        }
        return true;
    }
    @Override
    public boolean decide() {
        decideNumber++;

        if(init()) return false;
        if(checkplayer())return false;
        if(start()) return false;
        if(stop())return false;

        return true;
    }

    boolean hasFallen()
    {
        return worldModel.getSoccerAgents().get(0).getPosition().getZ()<0.25;
    }

    int numberOfPlayer()
    {
        return worldModel.getSoccerAgents().size();
    }

}
