package miracle3d.benchmark.playercontroller.impl;

import miracle3d.base.util.misc.ShellCommandUtil.impl.ShellCommandUtil;
import miracle3d.benchmark.playercontroller.IPlayerController;
import miracle3d.benchmark.playercontroller.PlayerControllerParameters;

import java.io.IOException;
import java.nio.file.Path;

public class PlayerController implements IPlayerController {
    private PlayerControllerParameters parameters;
    private Process player;

    public PlayerController(PlayerControllerParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public void startPlayer() {

        try{
            String decisionmaker = "--decisionmaker="+parameters.getBenchmarkType().getDecisionmaker();
            player = ShellCommandUtil.getInstance().launchAndConsume("bash",parameters.getScriptPath().toString(),decisionmaker);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public Process getPlayerProcess() {
        return player;
    }
}
