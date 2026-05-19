package com.fuzhudada.error.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageAASSkill implements IMessage {
    public int type;

    public MessageAASSkill(){
        type=0;
    }

    public MessageAASSkill(int type){
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
