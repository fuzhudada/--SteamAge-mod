package com.fuzhudada.error.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageBirdChest implements IMessage {
    public int tickExist;

    public MessageBirdChest(){
        tickExist=0;
    }

    public MessageBirdChest(int tickExist){
        this.tickExist=tickExist;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        tickExist=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(tickExist);
    }
}
