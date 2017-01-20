package org.spongepowered.clean.init;

import org.spongepowered.api.GameState;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class NetworkInitTask extends Task {

    @Override
    protected void execute() {
        // TODO setup network listener
        SGame.game.getNetworkManager().startListening(25565);

        SGame.game.updateState(GameState.SERVER_STARTED);
        CoreScheduler.addHighTask(new GameTickTask());
    }

}
