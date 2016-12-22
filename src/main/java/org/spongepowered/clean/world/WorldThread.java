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
package org.spongepowered.clean.world;

public class WorldThread implements Runnable {

    private final SpongeWorld world;
    private boolean           unloaded;

    public WorldThread(SpongeWorld world) {
        this.world = world;
    }

    // TODO expose per-world scheduler
    // TODO expose per-world teleport helper
    // TODO expose per-world chunkticket manager

    @Override
    public void run() {
        this.unloaded = false;
        long start, delta;
        while (this.world.isLoaded()) {
            start = System.currentTimeMillis();

            this.world.tick();

            delta = System.currentTimeMillis() - start;
            if (50 - delta > 0) {
                try {
                    Thread.sleep(50 - delta);
                } catch (InterruptedException e) {
                    this.world.triggerUnload();
                }
            }
        }
        // TODO unload world and save all data to disk
        this.unloaded = true;
    }

    public void unload() {
        this.world.triggerUnload();
    }

    public boolean isUnloaded() {
        return this.unloaded;
    }

    public SpongeWorld getWorld() {
        return this.world;
    }

}
