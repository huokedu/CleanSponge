package org.spongepowered.clean.network.packet.play.clientbound;

import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.netty.ByteBufUtil;
import org.spongepowered.clean.network.packet.Packet;

import java.util.UUID;

public class SpawnPlayerPacket extends Packet {

    public int entityid;
    public UUID uid;
    public double x, y, z;
    public float pitch, yaw;
    // TODO metadata

    public SpawnPlayerPacket(int id, UUID uid, double x, double y, double z, float pitch, float yaw) {
        this.id = 0x05;
        this.entityid = id;
        this.uid = uid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeVarInt(buffer, this.entityid);
        buffer.writeLong(this.uid.getMostSignificantBits());
        buffer.writeLong(this.uid.getLeastSignificantBits());
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
        buffer.writeByte((byte) Math.floor((this.pitch / 2 * Math.PI) * 256));
        buffer.writeByte((byte) Math.floor((this.yaw / 2 * Math.PI) * 256));
        buffer.writeByte(0xFF);
    }

}
