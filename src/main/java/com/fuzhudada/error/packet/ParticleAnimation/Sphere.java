package com.fuzhudada.error.packet.ParticleAnimation;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class Sphere implements IMessage {
    public String types;
    public Vec3d pos;
    public Vec3d vel;
    public Vec3d normal;
    public double rotate;
    public Vec3d r;
    public double exp;
    public int amount_pitch;
    public int amount_yaw;
    public boolean ignoreRange;

    public int len;

    public Sphere(){
        types="flame";
        pos=new Vec3d(0,0,0);
        vel=new Vec3d(0,0,0);
        normal=new Vec3d(0,0,0);
        rotate=0;
        r=new Vec3d(0,0,0);
        exp=0.5;
        amount_pitch=0;
        amount_yaw=0;
        len=5;
        ignoreRange=false;
    }

    public Sphere(String name,double x,double y,double z,double vx,double vy,double vz,double nx,double ny,double nz,double rot,double r,double exp,int np,int nyaw){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=new Vec3d(vx,vy,vz);
        normal=new Vec3d(nx,ny,nz);
        rotate=rot;
        this.r=new Vec3d(r,r,r);
        this.exp=exp;
        amount_pitch=np;
        amount_yaw=nyaw;
        len=name.length();
        ignoreRange=false;
    }

    public Sphere(String name,double x,double y,double z,double vx,double vy,double vz,double nx,double ny,double nz,double rot,double rx,double ry,double rz,double exp,int np,int nyaw){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=new Vec3d(vx,vy,vz);
        normal=new Vec3d(nx,ny,nz);
        rotate=rot;
        this.r=new Vec3d(rx,ry,rz);
        this.exp=exp;
        amount_pitch=np;
        amount_yaw=nyaw;
        len=name.length();
        ignoreRange=false;
    }

    public Sphere(String name,double x,double y,double z,double vx,double vy,double vz,double nx,double ny,double nz,double rot,double rx,double ry,double rz,double exp,int np,int nyaw,boolean ignoreRange){
        types=name;
        pos=new Vec3d(x,y,z);
        vel=new Vec3d(vx,vy,vz);
        normal=new Vec3d(nx,ny,nz);
        rotate=rot;
        this.r=new Vec3d(rx,ry,rz);
        this.exp=exp;
        amount_pitch=np;
        amount_yaw=nyaw;
        len=name.length();
        this.ignoreRange=ignoreRange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        vel=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        normal=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        rotate=buf.readDouble();
        r=new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
        exp=buf.readDouble();
        amount_pitch=buf.readInt();
        amount_yaw=buf.readInt();
        ignoreRange=buf.readBoolean();
        types=buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8).toString().trim();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(pos.x).writeDouble(pos.y).writeDouble(pos.z).writeDouble(vel.x).writeDouble(vel.y).writeDouble(vel.z).writeDouble(normal.x).writeDouble(normal.y).writeDouble(normal.z).writeDouble(rotate).writeDouble(r.x).writeDouble(r.y).writeDouble(r.z).writeDouble(exp).writeInt(amount_pitch).writeInt(amount_yaw).writeBoolean(ignoreRange).writeInt(len);
        buf.writeCharSequence(types, StandardCharsets.UTF_8);
    }
}
