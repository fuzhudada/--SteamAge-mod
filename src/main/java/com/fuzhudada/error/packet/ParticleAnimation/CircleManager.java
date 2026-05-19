package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CircleManager implements IMessageHandler<Circle, IMessage> {
    @Override
    public IMessage onMessage(Circle message , MessageContext ctx) {
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

                        mc.world.spawnParticle(types,message.ignoreRange,x,y,z,message.vel.x,message.vel.y,message.vel.z);
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
