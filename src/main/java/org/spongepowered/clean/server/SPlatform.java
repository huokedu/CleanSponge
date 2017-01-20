package org.spongepowered.clean.server;

import org.spongepowered.api.MinecraftVersion;
import org.spongepowered.api.Platform;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.clean.Constants;
import org.spongepowered.clean.plugin.SPluginContainer;

import java.util.HashMap;
import java.util.Map;

public class SPlatform implements Platform {

    private final PluginContainer apiContainer = new SPluginContainer("spongeapi");
    private final PluginContainer implContainer = new SPluginContainer("spongeclean");

    private final SMinecraftVersion mcVersion = new SMinecraftVersion(Constants.MC_VERSION);
    private final Map<String, Object> meta = new HashMap<>();

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
        return this.mcVersion;
    }

    @Override
    public Map<String, Object> asMap() {
        return this.meta;
    }

}
