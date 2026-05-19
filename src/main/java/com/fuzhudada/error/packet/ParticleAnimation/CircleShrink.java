package com.fuzhudada.error.packet.ParticleAnimation;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class CircleShrink implements IMessage {
    public String types;
    public Vec3d pos;
    public double vel;
    public Vec3d normal;
    public Vec3d r;
    public int amount;
    public boolean shrink;
    public boolean ignoreRange;

    public int len;

    public CircleShrink(){
        types="flame";
        pos=new Vec3d(0,0,0);
        vel=0;
        normal=new Vec3d(0,0,0);
        r=new Vec3d(0,0,0);
        amount=0;
        shrink=false;
        len=5;
        ignoreRange=false;
    }

    public CircleShrink(String name,double x,double y,double z,double v,double nx,double ny,double nz,double r,int n,boolean shrink){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=v;
        normal=new Vec3d(nx,ny,nz);
        this.r=new Vec3d(r,r,r);
        amount=n;
        this.shrink=shrink;
        len=name.length();
        ignoreRange=false;
    }

    public CircleShrink(String name,double x,double y,double z,double v,double nx,double ny,double nz,double rx,double ry,double rz,int n,boolean shrink){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=v;
        normal=new Vec3d(nx,ny,nz);
        this.r=new Vec3d(rx,ry,rz);
        amount=n;
        this.shrink=shrink;
        len=name.length();
        ignoreRange=false;
    }

    public CircleShrink(String name,double x,double y,double z,double v,double nx,double ny,double nz,double rx,double ry,double rz,int n,boolean shrink,boolean ignoreRange){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=v;
        normal=new Vec3d(nx,ny,nz);
        this.r=new Vec3d(rx,ry,rz);
        amount=n;
        this.shrink=shrink;
        len=name.length();
        this.ignoreRange=ignoreRange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        vel=buf.readDouble();
        normal=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        r=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        amount=buf.readInt();
        shrink=buf.readBoolean();
        ignoreRange=buf.readBoolean();
        types=buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString().trim();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(pos.x).writeDouble(pos.y).writeDouble(pos.z).writeDouble(vel).writeDouble(normal.x).writeDouble(normal.y).writeDouble(normal.z).writeDouble(r.x).writeDouble(r.y).writeDouble(r.z).writeInt(amount).writeBoolean(shrink).writeBoolean(ignoreRange).writeInt(len);
        buf.writeCharSequence(types, StandardCharsets.UTF_8);
    }
}
