package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CircleShrinkManager implements IMessageHandler<CircleShrink, IMessage> {
    @Override
    public IMessage onMessage(CircleShrink message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            try{
                EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
                Vec3d u=getVector(message.normal);
                if(u != null){
                    Vec3d v=message.normal.crossProduct(u).normalize();

                    for(int i=0;i<message.amount;i++){
                        double a=i*Math.PI*2/message.amount;
                        double x=message.pos.x+message.r.x*u.x*Math.cos(a)+message.r.z*v.x*Math.sin(a);
                        double y=message.pos.y+message.r.x*u.y*Math.cos(a)+message.r.z*v.y*Math.sin(a);
                        double z=message.pos.z+message.r.x*u.z*Math.cos(a)+message.r.z*v.z*Math.sin(a);

                        if(message.shrink){
                            mc.world.spawnParticle(types,message.ignoreRange,x,y,z,message.vel*(message.pos.x-x),message.vel*(message.pos.y-y),message.vel*(message.pos.z-z));
                        }else{
                            mc.world.spawnParticle(types,message.ignoreRange,x,y,z,message.vel*(u.x*Math.cos(a)+v.x*Math.sin(a)),message.vel*(u.y*Math.cos(a)+v.y*Math.sin(a)),message.vel*(u.z*Math.cos(a)+v.z*Math.sin(a)));
                        }
                    }
                }
            }catch (IllegalArgumentException e){
                mc.player.sendMessage(new TextComponentTranslation("qwq"));
            }
        });
        return null;
    }

    private Vec3d getVector(Vec3d n){
        Vec3d u=null;
        double len=n.y*n.y+n.z*n.z;
        if(len != 0){
            len=Math.sqrt(len);
            u=new Vec3d(0,-n.z/len,n.y/len);
        }

        return u;
    }
}
