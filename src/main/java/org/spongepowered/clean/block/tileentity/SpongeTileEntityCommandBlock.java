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
package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.CommandBlock;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.service.permission.SubjectCollection;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.util.Tristate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SpongeTileEntityCommandBlock extends SpongeTileEntity implements CommandBlock {

    public SpongeTileEntityCommandBlock() {

    }

    @Override
    public TileEntityType getType() {
        return SpongeTileEntityType.COMMAND_BLOCK;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(Text message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageChannel getMessageChannel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMessageChannel(MessageChannel channel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CommandSource> getCommandSource() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectCollection getContainingCollection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectData getSubjectData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectData getTransientSubjectData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tristate getPermissionValue(Set<Context> contexts, String permission) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isChildOf(Set<Context> contexts, Subject parent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Subject> getParents(Set<Context> contexts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<String> getOption(Set<Context> contexts, String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getIdentifier() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Context> getActiveContexts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException();
    }

}
