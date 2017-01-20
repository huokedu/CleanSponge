package org.spongepowered.clean.network.packet.play.clientbound;

import io.netty.buffer.ByteBuf;
import org.spongepowered.clean.network.packet.Packet;

public class StatisticsPacket extends Packet {

    public StatisticsPacket() {
        this.id = 0x07;
    }

    @Override
    public void read(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(ByteBuf buffer) {
        // TODO Auto-generated method stub
    }

}
