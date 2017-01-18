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
