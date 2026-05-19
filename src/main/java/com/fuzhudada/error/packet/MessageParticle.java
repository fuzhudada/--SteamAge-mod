package com.fuzhudada.error.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageParticle implements IMessage {
    public double x;
    public double y;
    public double z;
    public Vec3d f;
    public int type;

    public MessageParticle(){
        this.x=0;
        this.y = 0;
        this.z = 0;
        f = new Vec3d(0,0,0);
        type=0;
    }

    public MessageParticle(double x, double y, double z, Vec3d F,int type){
        this.x=x;
        this.y = y;
        this.z = z;
        f = F;
        this.type=type;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x=buf.readDouble();
        y=buf.readDouble();
        z=buf.readDouble();
        f=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        type=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(x).writeDouble(y).writeDouble(z).writeDouble(f.x).writeDouble(f.y).writeDouble(f.z).writeInt(type);
    }
}
