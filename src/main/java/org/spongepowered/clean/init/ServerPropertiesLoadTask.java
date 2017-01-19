package org.spongepowered.clean.init;

import org.spongepowered.api.Sponge;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.config.ServerProperties;
import org.spongepowered.clean.scheduler.Task;

public class ServerPropertiesLoadTask extends Task {

    @Override
    protected void execute() {
        // TODO actually load these properties from disk
        ServerProperties properties = new ServerProperties();
        ((SServer) Sponge.getServer()).setServerProperties(properties);
    }

}
