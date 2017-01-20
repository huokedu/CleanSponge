package org.spongepowered.clean.network.packet.play.clientbound;

import io.netty.buffer.ByteBuf;

import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

public class SpawnGlobalEntityPacket extends Packet {

    public int entityid;
    public double x, y, z;
    // only global entity is lightning which has a type of 1
    public byte type;

    public SpawnGlobalEntityPacket(int id, double x, double y, double z, byte type) {
        this.id = 0x02;
        this.entityid = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeVarInt(buffer, this.entityid);
        buffer.writeByte(this.type);
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
    }

}
