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

import com.google.common.collect.Multimap;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class SpongeCommandManager implements CommandManager {

    public SpongeCommandManager() {

    }

    @Override
    public Set<? extends CommandMapping> getCommands() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getPrimaryAliases() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getAliases() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<? extends CommandMapping> get(String alias) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<? extends CommandMapping> get(String alias, CommandSource source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<? extends CommandMapping> getAll(String alias) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Multimap<String, CommandMapping> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAlias(String alias) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsMapping(CommandMapping mapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean testPermission(CommandSource source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Text getUsage(CommandSource source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CommandMapping> register(Object plugin, CommandCallable callable, String... alias) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CommandMapping> register(Object plugin, CommandCallable callable, List<String> aliases) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CommandMapping> register(Object plugin, CommandCallable callable, List<String> aliases,
            Function<List<String>, List<String>> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CommandMapping> removeMapping(CommandMapping mapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<PluginContainer> getPluginContainers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<CommandMapping> getOwnedBy(Object instance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PluginContainer> getOwner(CommandMapping mapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommandResult process(CommandSource source, String arguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, Location<World> targetPosition) {
        throw new UnsupportedOperationException();
    }

}
