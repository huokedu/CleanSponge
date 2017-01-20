/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
