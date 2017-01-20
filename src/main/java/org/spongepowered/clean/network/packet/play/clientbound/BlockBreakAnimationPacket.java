package org.spongepowered.clean.network.packet.play.clientbound;

import com.flowpowered.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.netty.ByteBufUtil;
import org.spongepowered.clean.network.packet.Packet;

public class BlockBreakAnimationPacket extends Packet {

    public int entityid;
    public Vector3i position;
    // 0-9
    public byte stage;

    public BlockBreakAnimationPacket(int entityid, Vector3i pos, byte stage) {
        this.id = 0x08;
        this.entityid = entityid;
        this.position = pos;
        this.stage = stage;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeVarInt(buffer, this.entityid);
        ByteBufUtil.writePosition(buffer, this.position);
        buffer.writeByte(this.stage);
    }

}
