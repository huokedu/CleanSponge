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
package org.spongepowered.clean.init;

import java.io.IOException;

import org.spongepowered.clean.Constants;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TasksCompleteCondition;

import com.google.common.base.Throwables;

public class StartupTask extends Task {

    /* @formatter:off
     * Full startup picture: (newlines represent gates which depend on prior tasks)
     * 
     * StartupTask
     * 
     * ServerPropertiesLoad
     * SpongeConfigLoad
     * WorldConfigLoads (x # Worlds)
     * Registry initialization (x main registries)
     * 
     * Plugin loading (x # plugins)
     * 
     * LoadWorld (x # startup worlds)
     * 
     * NetworkStart
     * 
     * GameTickStart
     * 
     * @formatter:on
     */

    @Override
    public void execute() {
        SGame.getLogger().info("Loading sponge cleanroom server version {}", Constants.SERVER_VERSION);
        SGame.getLogger().info("Implementing sponge api version {}", Constants.API_VERSION);
        SGame.getLogger().info("Supporting minecraft version {}", Constants.MC_VERSION);
        SGame.game.setGameObject();

        try {
            SGame.game.initializeFiles();
        } catch (IOException e) {
            // TODO trigger crash reporter
            Throwables.propagate(e);
        }

        TasksCompleteCondition condition = new TasksCompleteCondition();
        ServerPropertiesLoadTask propsLoad = new ServerPropertiesLoadTask();
        condition.addTask(propsLoad);
        CoreScheduler.addNormalTask(propsLoad);
        SpongeConfigLoadTask configLoad = new SpongeConfigLoadTask();
        condition.addTask(configLoad);
        CoreScheduler.addNormalTask(configLoad);

        PluginInitTask pluginInit = new PluginInitTask();
        CoreScheduler.addNormalTask(pluginInit, condition);

    }

}
