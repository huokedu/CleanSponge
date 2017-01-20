package org.spongepowered.clean.network.packet.play.clientbound;

import io.netty.buffer.ByteBuf;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.clean.entity.SEntityType;
import org.spongepowered.clean.network.packet.Packet;
import org.spongepowered.clean.util.ByteBufUtil;

import java.util.UUID;

public class SpawnMobPacket extends Packet {

    public int entityid;
    public UUID uid;
    public EntityType type;
    public double x, y, z;
    public float pitch, yaw, head_pitch;
    public double vx, vy, vz;
    // TODO metadata

    public SpawnMobPacket(int id, UUID uid, EntityType type, double x, double y, double z, float pitch, float yaw, float head_pitch, double vx,
            double vy, double vz) {
        this.id = 0x03;
        this.entityid = id;
        this.uid = uid;
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.head_pitch = head_pitch;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
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
        buffer.writeByte(((SEntityType) this.type).getEntityId());
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
        buffer.writeByte((byte) Math.floor((this.pitch / 2 * Math.PI) * 256));
        buffer.writeByte((byte) Math.floor((this.yaw / 2 * Math.PI) * 256));
        buffer.writeByte((byte) Math.floor((this.head_pitch / 2 * Math.PI) * 256));
        buffer.writeShort((short) Math.floor(this.vx * 8000));
        buffer.writeShort((short) Math.floor(this.vy * 8000));
        buffer.writeShort((short) Math.floor(this.vz * 8000));
        buffer.writeByte(0xFF);
    }

}
