package org.spongepowered.clean.server;

import org.spongepowered.api.MinecraftVersion;

public class SMinecraftVersion extends Semver implements MinecraftVersion {

    public SMinecraftVersion(String version) {
        super(version);
    }

    @Override
    public boolean isLegacy() {
        return false;
    }

    @Override
    public int compareTo(MinecraftVersion o) {
        if(o instanceof Semver) {
            return compareTo(o);
        }
        Semver s = new Semver(o.getName());
        return compareTo(s);
    }

}
