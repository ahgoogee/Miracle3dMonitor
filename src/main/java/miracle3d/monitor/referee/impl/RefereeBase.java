package miracle3d.monitor.referee.impl;


import miracle3d.base.util.misc.ShellCommandUtil.impl.ShellCommandUtil;
import miracle3d.base.util.timing.AlarmTimer;
import miracle3d.base.util.timing.ITriggerReceiver;
import miracle3d.monitor.command.IServerCommander;
import miracle3d.monitor.referee.IReferee;
import miracle3d.monitor.worldmodel.IMonitorWorldModel;

import java.io.IOException;

public abstract class RefereeBase implements IReferee, ITriggerReceiver {
    protected IMonitorWorldModel worldModel;
    protected IServerCommander serverCommander;
    protected AlarmTimer timer;
    private int timerCounter;
    private long lastSetupTime;

    @Deprecated
    //TODO wsl平台获取pid有困难 2
    private String serverPid;
    protected IReferee.RefereeState state;

    public RefereeBase(IMonitorWorldModel mWorldModel, IServerCommander serverCommander, String serverPid) {
        this.worldModel = mWorldModel;
        this.serverCommander = serverCommander;
        this.serverPid = serverPid;
        this.state = RefereeState.CREATED;
    }


    protected void setupTimer(long timeout) {
        ++this.timerCounter;
        long time = System.currentTimeMillis();
        if (time - this.lastSetupTime > 2000L) {
            this.stopTimer();
            this.timer = new AlarmTimer("Alarm-" + timeout + " id: " + this.timerCounter, this, timeout);
            this.lastSetupTime = System.currentTimeMillis();
        }

    }

    protected void stopTimer() {
        if (this.timer != null) {
            this.timer.stopAlarm();
        }

    }

    public void trigger(String name) {
        //TODO 修复trigger理解性问题
        if (this.serverPid != null && !this.serverPid.isEmpty()) {
            System.err.println("Server hangs at time " + this.worldModel.getTime() + ", killing it... " + this.serverPid + " timer: " + name + " counter: " + this.timerCounter);
            ShellCommandUtil.getInstance().killAll( "rcssserver3d");
        } else {
            System.err.println("Server hangs, killing all..." + name + " counter: " + this.timerCounter);
            //TODO 修复platform特异性语句
            ShellCommandUtil.getInstance().killAll("rcssserver3d");
        }

    }


    public IReferee.RefereeState getState() {
        return this.state;
    }
}
