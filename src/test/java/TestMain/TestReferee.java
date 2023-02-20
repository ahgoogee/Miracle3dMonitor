package TestMain;

import miracle3d.commons.spark.PlayMode;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.referee.impl.RefereeBase;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

public class TestReferee extends RefereeBase {

    private int decideNumber;
    private boolean inited;

    private float targetTime ;


    public TestReferee(IMonitorWorldModel mWorldModel, IServerCommander serverCommander, String serverPid) {
        super(mWorldModel, serverCommander, serverPid);
        decideNumber = 0;
        this.inited = false;
        targetTime = 10.0f;
    }

    private int validNumber = 0;
    private boolean startPlayer()
    {
        if(worldModel.getSoccerAgents().size()<1)
            return true;

        if(validNumber>=10)
        {
            return false;
        }

        if(worldModel.getSoccerAgents().get(0).getPosition().getZ()<0.25)
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
            if(worldModel.getPlayMode()==PlayMode.BEFORE_KICK_OFF) {
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

    private void stop()
    {
        if(decideNumber%10==0)
            System.out.println(worldModel.getTime());

        if(worldModel.getTime() >= 10.f) {
            inited = false;
            state = RefereeState.STOPPED;
        }
    }
    @Override
    public boolean decide() {
        decideNumber++;

        if(init()) return false;
        if(startPlayer())return false;
        if(start()) return false;
        stop();

        return false;
    }


}
