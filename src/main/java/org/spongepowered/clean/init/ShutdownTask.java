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
import org.spongepowered.clean.scheduler.CoreScheduler;
import org.spongepowered.clean.scheduler.Task;

public class ShutdownTask extends Task {

    @Override
    public void execute() {
        System.out.println("Shutdown");
        SGame.game.commandManager.stopListener();
        SGame.game.updateState(GameState.SERVER_STOPPING);
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppingServerEvent(SGame.game.getImplementationCause()));

        // TODO save worlds

        SGame.game.getNetworkManager().shutdown();
        SGame.game.updateState(GameState.SERVER_STOPPED);
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppedServerEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.GAME_STOPPING);
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppingEvent(SGame.game.getImplementationCause()));
        SGame.game.updateState(GameState.GAME_STOPPED);
        Sponge.getEventManager().post(SpongeEventFactory.createGameStoppedEvent(SGame.game.getImplementationCause()));

        CoreScheduler.shutdown();
    }

}
