package org.spongepowered.clean.entity.vehicle.minecart;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.vehicle.minecart.CommandBlockMinecart;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.service.permission.SubjectCollection;
import org.spongepowered.api.service.permission.SubjectData;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.util.Tristate;
import org.spongepowered.clean.world.SWorld;

public class SCommandBlockMinecart extends SMinecart implements CommandBlockMinecart {

    public SCommandBlockMinecart(SWorld world) {
        super(world);
        // TODO Auto-generated constructor stub
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

}
