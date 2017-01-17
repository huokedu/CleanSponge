package org.spongepowered.clean.init;

import org.spongepowered.api.GameState;
import org.spongepowered.clean.SpongeGame;
import org.spongepowered.clean.SpongeServer;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class StartupTask extends Task {

    @Override
    public void execute() {
        System.out.println("Startup");

        // Load startup worlds
        // TODO actually locate startup worlds
        SpongeServer.insn.loadWorld("world");

        SpongeGame.game.updateState(GameState.SERVER_STARTED);
        CoreScheduler.addHighTask(new GameTickTask());
    }

}
