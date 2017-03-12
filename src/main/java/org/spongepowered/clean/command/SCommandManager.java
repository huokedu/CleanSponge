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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import org.spongepowered.api.Platform.Component;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.ImmutableCommandMapping;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.clean.command.builtin.HelpCommand;
import org.spongepowered.clean.command.builtin.StopCommand;
import org.spongepowered.clean.scheduler.CoreScheduler;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SCommandManager implements CommandManager {

    private final Multimap<String, CommandMapping> commands = HashMultimap.create();
    private ConsoleCommandReader console;

    public SCommandManager() {
    }

    public void registerBaseCommands() {
        register(Sponge.getGame().getPlatform().getContainer(Component.IMPLEMENTATION), new HelpCommand(), "help", "?");
        register(Sponge.getGame().getPlatform().getContainer(Component.IMPLEMENTATION), new StopCommand(), "stop");
    }

    public void startListener() {
        this.console = new ConsoleCommandReader();
        this.console.start();
    }

    public void stopListener() {
        this.console.halt();
    }

    @Override
    public Set<CommandMapping> getCommands() {
        return ImmutableSet.copyOf(this.commands.values());
    }

    @Override
    public Set<String> getPrimaryAliases() {
        return this.commands.values().stream().map((m) -> m.getPrimaryAlias()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAliases() {
        return ImmutableSet.copyOf(this.commands.keySet());
    }

    @Override
    public Optional<CommandMapping> get(String alias) {
        Iterator<CommandMapping> it = this.commands.get(alias).iterator();
        if (it.hasNext()) {
            return Optional.of(it.next());
        }
        return Optional.empty();
    }

    @Override
    public Optional<CommandMapping> get(String alias, CommandSource source) {
        Iterator<CommandMapping> it = this.commands.get(alias).iterator();
        while (it.hasNext()) {
            CommandMapping n = it.next();
            if (n.getCallable().testPermission(source)) {
                return Optional.of(n);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<CommandMapping> getAll(String alias) {
        return ImmutableSet.copyOf(this.commands.get(alias));
    }

    @Override
    public Multimap<String, CommandMapping> getAll() {
        return ImmutableMultimap.copyOf(this.commands);
    }

    @Override
    public boolean containsAlias(String alias) {
        return this.commands.containsKey(alias);
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
        if (alias.length == 0) {
            return Optional.empty();
        }
        String primary = alias[0];
        String[] aliases = Arrays.copyOfRange(alias, 1, alias.length);

        CommandMapping mapping = new ImmutableCommandMapping(callable, primary, aliases);
        for (String a : alias) {
            this.commands.put(a, mapping);
        }
        return Optional.of(mapping);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<CommandMapping> getOwnedBy(Object instance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PluginContainer> getOwner(CommandMapping mapping) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.commands.size();
    }

    public CompletableFuture<CommandResult> processAsync(CommandSource src, CommandCallable cmd, String arguments) {
        ExecuteCommandTask task = new ExecuteCommandTask(cmd, src, arguments);
        // TODO: collect required contexts and add as condition
        CoreScheduler.addNormalTask(task);
        return task.getResult();
    }

    @Override
    public CompletableFuture<CommandResult>  processAsync(CommandSource source, String arguments) {
        int first = arguments.indexOf(' ');
        String cmd = arguments.substring(0, first == -1 ? arguments.length() : first);
        String args = first == -1 ? "" : arguments.substring(first + 1);
        Optional<CommandMapping> mapping = get(cmd, source);
        if (!mapping.isPresent()) {
            source.sendMessage(Text.of(TextColors.RED, "Command " + cmd + " not found."));
            CompletableFuture<CommandResult> result = new CompletableFuture<>();
            result.complete(CommandResult.empty());
            return result;
        }
        return processAsync(source, mapping.get().getCallable(), args);
    }

    @Override
    public CommandResult process(CommandSource source, String arguments) {
        int first = arguments.indexOf(' ');
        String cmd = arguments.substring(0, first == -1 ? arguments.length() : first);
        String args = first == -1 ? "" : arguments.substring(first + 1);
        Optional<CommandMapping> mapping = get(cmd, source);
        if (!mapping.isPresent()) {
            source.sendMessage(Text.of(TextColors.RED, "Command " + cmd + " not found."));
            return CommandResult.empty();
        }
        processAsync(source, mapping.get().getCallable(), args);
        return CommandResult.empty();
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, Location<World> targetPosition) {
        int first = arguments.indexOf(' ');
        String cmd = arguments.substring(0, first == -1 ? arguments.length() : first);
        Optional<CommandMapping> mapping = get(cmd);
        if (mapping.isPresent()) {
            try {
                return mapping.get().getCallable().getSuggestions(source, arguments, targetPosition);
            } catch (CommandException e) {
                e.printStackTrace();
            }
        }
        return ImmutableList.of();
    }

}
