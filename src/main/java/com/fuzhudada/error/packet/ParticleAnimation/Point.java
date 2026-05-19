package com.fuzhudada.error.packet.ParticleAnimation;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class Point implements IMessage {
    public String types;
    public Vec3d pos;
    public Vec3d vel;
    public int len;
    public boolean ignoreRange;

    public Point(){
        types="flame";
        pos=new Vec3d(0,0,0);
        vel=new Vec3d(0,0,0);
        len=5;
        ignoreRange=false;
    }

    public Point(String name,double x,double y,double z,double vx,double vy,double vz){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=new Vec3d(vx,vy,vz);
        len=name.length();
        ignoreRange=false;
    }

    public Point(String name,double x,double y,double z,double vx,double vy,double vz,boolean ignoreRange){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=new Vec3d(vx,vy,vz);
        len=name.length();
        this.ignoreRange=ignoreRange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        vel=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        ignoreRange=buf.readBoolean();
        types=buf.readCharSequence(buf.readInt(),StandardCharsets.UTF_8).toString().trim();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(pos.x).writeDouble(pos.y).writeDouble(pos.z).writeDouble(vel.x).writeDouble(vel.y).writeDouble(vel.z).writeBoolean(ignoreRange).writeInt(len);
        buf.writeCharSequence(types, StandardCharsets.UTF_8);
    }
}
