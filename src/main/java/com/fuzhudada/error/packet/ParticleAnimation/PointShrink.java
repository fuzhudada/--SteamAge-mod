package com.fuzhudada.error.packet.ParticleAnimation;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class PointShrink implements IMessage {
    public String types;
    public Vec3d pos;
    public Vec3d siz;
    public double speed;
    public int amount;
    public int len;
    public boolean ignoreRange;

    public PointShrink(){
        types="flame";
        pos=new Vec3d(0,0,0);
        siz=new Vec3d(0,0,0);
        speed=0;
        amount=0;
        len=5;
        ignoreRange=false;
    }

    public PointShrink(String name,double x,double y,double z,double vx,double vy,double vz,double speed,int amount){
        types=name;
        pos=new Vec3d(x,y,z);
        siz=new Vec3d(vx,vy,vz);
        this.speed=speed;
        this.amount=amount;
        len=name.length();
        ignoreRange=false;
    }

    public PointShrink(String name,double x,double y,double z,double vx,double vy,double vz,double speed,int amount,boolean ignoreRange){
        types=name;
        pos=new Vec3d(x,y,z);
        siz=new Vec3d(vx,vy,vz);
        this.speed=speed;
        this.amount=amount;
        len=name.length();
        this.ignoreRange=ignoreRange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        siz=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        speed=buf.readDouble();
        amount=buf.readInt();
        ignoreRange=buf.readBoolean();
        types=buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString().trim();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(pos.x).writeDouble(pos.y).writeDouble(pos.z).writeDouble(siz.x).writeDouble(siz.y).writeDouble(siz.z).writeDouble(speed).writeInt(amount).writeBoolean(ignoreRange).writeInt(len);
        buf.writeCharSequence(types, StandardCharsets.UTF_8);
    }
}
