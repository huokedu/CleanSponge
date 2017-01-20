package org.spongepowered.clean.network.packet.play.clientbound;

import com.flowpowered.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import org.spongepowered.api.util.Direction;
import org.spongepowered.clean.network.netty.ByteBufUtil;
import org.spongepowered.clean.network.packet.Packet;

import java.util.UUID;

public class SpawnPaintingPacket extends Packet {

    public int entityid;
    public UUID uid;
    public String title;
    public Vector3i location;
    public Direction direction;

    public SpawnPaintingPacket(int id, UUID uid, String title, Vector3i loc, Direction dir) {
        this.id = 0x04;
        this.entityid = id;
        this.uid = uid;
        this.title = title;
        this.location = loc;
        this.direction = dir;
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
        ByteBufUtil.writeString(buffer, this.title);
        ByteBufUtil.writePosition(buffer, this.location);
        ByteBufUtil.writeDirection(buffer, this.direction);
    }

}
