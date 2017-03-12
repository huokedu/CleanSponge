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
package org.spongepowered.clean.command.builtin;

import com.google.common.collect.ImmutableList;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Coerce;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HelpCommand extends BaseCommand {

    private static final int COMMANDS_PER_PAGE = 15;

    public HelpCommand() {
        super(Text.of("Usage: /help [page|command name]"), Text.of("Provides help for other registered commands."), null, "minecraft.commands.help");
    }

    @Override
    public CommandResult process(CommandSource source, String arguments) throws CommandException {
        List<CommandMapping> commands = new ArrayList<>(Sponge.getCommandManager().getCommands());
        Collections.sort(commands, (a, b) -> a.getPrimaryAlias().compareTo(b.getPrimaryAlias()));
        if (arguments.isEmpty()) {
            int count = Math.min(commands.size(), COMMANDS_PER_PAGE);
            source.sendMessage(Text.of("Displaying " + count + " of " + commands.size() + " commands:"));
            for (int i = 0; i < count; i++) {
                CommandMapping mapping = commands.get(i);
                Text desc = mapping.getCallable().getShortDescription(source).orElse(mapping.getCallable().getUsage(source));
                source.sendMessage(Text.of(mapping.getPrimaryAlias() + ": ", desc));
            }
            if (count < commands.size()) {
                source.sendMessage(Text.of("Type '/help 2' to see more results."));
            }
            return CommandResult.success();
        }
        String[] args = arguments.split(" ");
        Optional<Integer> page = Coerce.asInteger(args[0]);
        if (page.isPresent()) {
            int start = (page.get() - 1) * COMMANDS_PER_PAGE;
            if (start > commands.size()) {
                source.sendMessage(Text.of(TextColors.RED, "Invalid page."));
                return CommandResult.success();
            }
            int end = Math.min(commands.size(), start + COMMANDS_PER_PAGE);

            source.sendMessage(Text.of("Displaying commands " + start + " to " + (end - 1) + " of " + commands.size() + " commands:"));
            for (int i = start; i < end; i++) {
                CommandMapping mapping = commands.get(i);
                Text desc = mapping.getCallable().getShortDescription(source).orElse(mapping.getCallable().getUsage(source));
                source.sendMessage(Text.of(mapping.getPrimaryAlias() + ": ", desc));
            }
            return CommandResult.success();
        }
        Optional<? extends CommandMapping> mapping = Sponge.getCommandManager().get(args[0]);
        if (mapping.isPresent()) {
            CommandCallable cmd = mapping.get().getCallable();
            Text help = cmd.getHelp(source).orElse(cmd.getShortDescription(source).orElse(cmd.getUsage(source)));
            source.sendMessage(help);
            return CommandResult.success();
        }
        source.sendMessage(Text.of(TextColors.RED, "Unknown command " + args[0]));
        return CommandResult.success();
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, Location<World> targetPosition) throws CommandException {
        // TODO Auto-generated method stub
        return ImmutableList.of();
    }

}
