package org.spongepowered.clean.network.packet.play.clientbound;

import com.flowpowered.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.clean.network.netty.ByteBufUtil;
import org.spongepowered.clean.network.packet.Packet;

public class UpdateBlockEntityPacket extends Packet {

    public static enum Action {
        SET_MOB_SPAWNER,
        SET_COMMAND,
        SET_BEACON,
        SET_SKULL_DATA,
        SET_FLOWER,
        SET_BANNER,
        SET_STRUCTURE,
        SET_END_GATEWAY,
        SET_SIGN_TEXT,
        SET_SHULKER_BOX,
    }

    public Vector3i position;
    public Action action;
    public DataContainer data;

    public UpdateBlockEntityPacket(Vector3i position, Action action, DataContainer data) {
        this.id = 0x09;
        this.position = position;
        this.action = action;
        this.data = data;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writePosition(buffer, this.position);
        buffer.writeByte(this.action.ordinal());
        ByteBufUtil.writeNBT(buffer, this.data);
    }

}
