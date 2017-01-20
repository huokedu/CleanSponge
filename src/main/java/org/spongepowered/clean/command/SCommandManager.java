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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.google.common.collect.Multimap;

public class SCommandManager implements CommandManager {

    public SCommandManager() {

    }

    @Override
    public Set<? extends CommandMapping> getCommands() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> getPrimaryAliases() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> getAliases() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<? extends CommandMapping> get(String alias) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<? extends CommandMapping> get(String alias, CommandSource source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<? extends CommandMapping> getAll(String alias) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Multimap<String, CommandMapping> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean containsAlias(String alias) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsMapping(CommandMapping mapping) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean testPermission(CommandSource source) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Text getUsage(CommandSource source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CommandMapping> register(Object plugin, CommandCallable callable, String... alias) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CommandMapping> register(Object plugin, CommandCallable callable, List<String> aliases) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CommandMapping> register(Object plugin, CommandCallable callable, List<String> aliases,
            Function<List<String>, List<String>> callback) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CommandMapping> removeMapping(CommandMapping mapping) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<PluginContainer> getPluginContainers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<CommandMapping> getOwnedBy(Object instance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<PluginContainer> getOwner(CommandMapping mapping) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public CommandResult process(CommandSource source, String arguments) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, Location<World> targetPosition) {
        // TODO Auto-generated method stub
        return null;
    }

}
