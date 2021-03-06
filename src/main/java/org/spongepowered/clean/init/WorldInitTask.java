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

import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.clean.SGame;
import org.spongepowered.clean.SServer;
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;
import org.spongepowered.clean.scheduler.condition.TaskCondition;

public class WorldInitTask extends Task {

    @Override
    protected void execute() {

        SGame.game.pluginManager.doLoad();

        SGame.game.updateState(GameState.CONSTRUCTION);
        Sponge.getEventManager().post(SpongeEventFactory.createGameConstructionEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.PRE_INITIALIZATION);
        Sponge.getEventManager().post(SpongeEventFactory.createGamePreInitializationEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.INITIALIZATION);
        Sponge.getEventManager().post(SpongeEventFactory.createGameInitializationEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.POST_INITIALIZATION);
        Sponge.getEventManager().post(SpongeEventFactory.createGamePostInitializationEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.SERVER_ABOUT_TO_START);
        Sponge.getEventManager().post(SpongeEventFactory.createGameAboutToStartServerEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.SERVER_STARTING);
        Sponge.getEventManager().post(SpongeEventFactory.createGameStartingServerEvent(SGame.game.getImplementationCause()));
        SGame.game.getDimensionManager().init();
        ((SServer) Sponge.getServer()).findAllWorlds();
        SGame.getLogger().info("Located " + Sponge.getServer().getUnloadedWorlds().size() + " worlds.");
        TaskCondition condition = ((SServer) Sponge.getServer()).loadStartupWorlds();
        CoreScheduler.addNormalTask(new NetworkInitTask(), condition);
    }

}
