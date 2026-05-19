package com.fuzhudada.error.packet.ParticleAnimation;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class LineShrink implements IMessage {
    public String types;
    public Vec3d pos1;
    public Vec3d pos2;
    public double siz;
    public double speed;
    public double thickness;
    public int amount;
    public boolean ignoreRange;

    public int len;

    public LineShrink(){
        types="flame";
        pos1=new Vec3d(0,0,0);
        pos2=new Vec3d(0,0,0);
        siz=0;
        speed=0;
        thickness=0;
        amount=0;
        len=5;
        ignoreRange=false;
    }

    public LineShrink(String name,double x1,double y1,double z1,double x2,double y2,double z2,double siz,double speed,double t,int n){
        types=name;
        pos1=new Vec3d(x1,y1,z1);
        pos2=new Vec3d(x2,y2,z2);
        this.siz=siz;
        this.speed=speed;
        thickness=t;
        amount=n;
        len=name.length();
        ignoreRange=false;
    }

    public LineShrink(String name,double x1,double y1,double z1,double x2,double y2,double z2,double siz,double speed,double t,int n,boolean ignoreRange){
        types=name;
        pos1=new Vec3d(x1,y1,z1);
        pos2=new Vec3d(x2,y2,z2);
        this.siz=siz;
        this.speed=speed;
        thickness=t;
        amount=n;
        len=name.length();
        this.ignoreRange=ignoreRange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos1=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        pos2=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        siz=buf.readDouble();
        speed=buf.readDouble();
        thickness=buf.readDouble();
        amount=buf.readInt();
        ignoreRange=buf.readBoolean();
        types=buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString().trim();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(pos1.x).writeDouble(pos1.y).writeDouble(pos1.z).writeDouble(pos2.x).writeDouble(pos2.y).writeDouble(pos2.z).writeDouble(siz).writeDouble(speed).writeDouble(thickness).writeInt(amount).writeBoolean(ignoreRange).writeInt(len);
        buf.writeCharSequence(types, StandardCharsets.UTF_8);
    }
}
