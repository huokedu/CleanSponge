package org.spongepowered.clean.network.packet.play.clientbound;

import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.netty.ByteBufUtil;
import org.spongepowered.clean.network.packet.Packet;

public class AnimationPacket extends Packet {

    public static enum Animation {
        SWING_MAIN_HAND,
        TAKE_DAMAGE,
        LEAVE_BED,
        SWING_OFF_HAND,
        CRITICAL_EFFECT,
        MAGIC_CRITICAL_EFFECT,
    }

    public int entityid;
    public Animation animation;

    public AnimationPacket(int entityid, Animation anim) {
        this.id = 0x06;
        this.entityid = entityid;
        this.animation = anim;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        ByteBufUtil.writeVarInt(buffer, this.entityid);
        buffer.writeByte(this.animation.ordinal());
    }

}
