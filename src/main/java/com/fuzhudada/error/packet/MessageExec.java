package com.fuzhudada.error.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class MessageExec implements IMessage {
    public String cmd;
    public int length;

    public MessageExec(){
        length=0;
        cmd="";
    }

    public MessageExec(String cmd,int length){
        this.length=length;
        this.cmd=cmd;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int l=buf.readInt();
        cmd=buf.readCharSequence(l,StandardCharsets.UTF_8).toString().trim();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(length);
        buf.writeCharSequence(cmd, StandardCharsets.UTF_8);
    }
}
