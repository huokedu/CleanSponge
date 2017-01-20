package org.spongepowered.clean.block.tileentity;

import org.spongepowered.api.block.tileentity.CommandBlock;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
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

public class SCommandBlock extends AbstractTileEntity implements CommandBlock {

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.COMMAND_BLOCK;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendMessage(Text message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public MessageChannel getMessageChannel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMessageChannel(MessageChannel channel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<CommandSource> getCommandSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubjectCollection getContainingCollection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubjectData getSubjectData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SubjectData getTransientSubjectData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tristate getPermissionValue(Set<Context> contexts, String permission) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isChildOf(Set<Context> contexts, Subject parent) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Subject> getParents(Set<Context> contexts) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<String> getOption(Set<Context> contexts, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Context> getActiveContexts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }

}
