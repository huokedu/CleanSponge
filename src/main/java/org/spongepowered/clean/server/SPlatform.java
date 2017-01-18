package org.spongepowered.clean.server;

import java.util.Map;

import org.spongepowered.api.MinecraftVersion;
import org.spongepowered.api.Platform;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.clean.plugin.SPluginContainer;

public class SPlatform implements Platform {

    private final PluginContainer apiContainer = new SPluginContainer("spongeapi");
    private final PluginContainer implContainer = new SPluginContainer("spongeclean");

    public SPlatform() {

    }

    @Override
    public Type getType() {
        return Type.SERVER;
    }

    @Override
    public Type getExecutionType() {
        return Type.SERVER;
    }

    @Override
    public PluginContainer getContainer(Component component) {
        if (component == Component.API) {
            return this.apiContainer;
        }
        return this.implContainer;
    }

    @Override
    public MinecraftVersion getMinecraftVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> asMap() {
        // TODO Auto-generated method stub
        return null;
    }

}
