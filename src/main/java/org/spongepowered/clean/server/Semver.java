package org.spongepowered.clean.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Semver {

    private static final Pattern SEMVER = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)");

    private final String version;
    private final int major;
    private final int minor;
    private final int patch;

    // TODO parse out extra information off end of version

    public Semver(String version) {
        this.version = version;
        Matcher m = SEMVER.matcher(version);
        if (!m.find()) {
            throw new IllegalArgumentException("Version is not semver: " + version);
        }
        this.major = Integer.parseInt(m.group(1));
        this.minor = Integer.parseInt(m.group(2));
        this.patch = Integer.parseInt(m.group(3));
    }

    public String getName() {
        return this.version;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getPatch() {
        return this.patch;
    }

    public int compareTo(Semver o) {
        if (this.major != o.major) {
            return Integer.signum(this.major - o.major);
        }
        if (this.minor != o.minor) {
            return Integer.signum(this.minor - o.minor);
        }
        return Integer.signum(this.patch - o.patch);
    }

}
