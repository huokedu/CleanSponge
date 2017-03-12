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
package org.spongepowered.clean.command;

import org.spongepowered.api.Sponge;
import org.spongepowered.clean.SGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommandReader extends Thread {

    private static final int ERROR_COUNT_THRESHOLD = 20;

    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private boolean running = true;
    private int errorCount = 0;

    public void halt() {
        this.running = false;
        interrupt();
    }

    @Override
    public void run() {
        SGame.getLogger().info("Listeneing for commands, type \"?\" for help");
        while (this.running) {
            try {
                if (!this.input.ready()) {
                    Thread.sleep(10);
                    continue;
                }
                String cmd = this.input.readLine();
                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), cmd);
            } catch (IOException e) {
                if (!this.running) {
                    break;
                }
                e.printStackTrace();
                this.errorCount++;
                if (this.errorCount > ERROR_COUNT_THRESHOLD) {
                    SGame.getLogger().info("Console input error threshold passed, disabling console commands!");
                    break;
                }
                try {
                    this.input.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.input = new BufferedReader(new InputStreamReader(System.in));
            } catch (InterruptedException e) {
                if (!this.running) {
                    break;
                }
                e.printStackTrace();
            }
        }
        try {
            this.input.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
