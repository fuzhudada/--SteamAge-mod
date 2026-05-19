package com.fuzhudada.error.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageAssault implements IMessage {
    public double x,y,z;
    public int particleType;

    public MessageAssault(){
        this.x=0;
        this.y=0;
        this.z=0;
        this.particleType=0;
    }

    public MessageAssault(double x,double y,double z,int particleType){
        this.x=x;
        this.y=y;
        this.z=z;
        this.particleType=particleType;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x=buf.readDouble();
        y=buf.readDouble();
        z=buf.readDouble();
        particleType=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(x).writeDouble(y).writeDouble(z).writeInt(particleType);
    }
}
