package com.fuzhudada.error.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageCCB implements IMessage {
    public int type;

    public MessageCCB(){
        type=0;
    }

    public MessageCCB(int type){
        this.type=type;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        type=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
    }
}
