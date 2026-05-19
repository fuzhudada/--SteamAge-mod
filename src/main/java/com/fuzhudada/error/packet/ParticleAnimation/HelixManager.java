package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HelixManager implements IMessageHandler<Helix, IMessage> {
    public IMessage onMessage(Helix message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            try{
                EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
                Vec3d u=getVector(message.normal);
                if(u != null){
                    Vec3d v=message.normal.crossProduct(u);
                    double len=message.normal.lengthVector();

                    for(int i=0;i<message.n;i++){
                        double t=((double) i)/message.n;
                        double k=Math.pow(1-t,message.exp);
                        double rx=message.r.x*k;
                        double rz=message.r.z*k;
                        double dx=t*message.h*message.normal.x/len;
                        double dy=t*message.h*message.normal.y/len;
                        double dz=t*message.h*message.normal.z/len;
                        double a=2*Math.PI*t*message.h/message.d+message.phi;
                        double x=message.pos.x+dx+rx*u.x*Math.cos(a)+rz*v.x*Math.sin(a);
                        double y=message.pos.y+dy+rx*u.y*Math.cos(a)+rz*v.y*Math.sin(a);
                        double z=message.pos.z+dz+rx*u.z*Math.cos(a)+rz*v.z*Math.sin(a);

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
