package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SphereManager implements IMessageHandler<Sphere, IMessage> {
    @Override
    public IMessage onMessage(Sphere message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            try{
                EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
                Vec3d u=getVector(message.normal);
                //mc.player.sendMessage(new TextComponentTranslation(""+(u==null)));
                if(u != null){
                    Vec3d v=message.normal.crossProduct(u);
                    double len=message.normal.lengthVector();

                    for(int i=0;i<message.amount_pitch;i++){
                        double k=((double) i)/message.amount_pitch;
                        double k1=Math.pow(k,message.exp);
                        double rx=message.r.x*k1;
                        double rz=message.r.z*k1;
                        double dx=message.r.y*(1-k)*message.normal.x/len;
                        double dy=message.r.y*(1-k)*message.normal.y/len;
                        double dz=message.r.y*(1-k)*message.normal.z/len;
                        for(int j=0;j<k1*message.amount_yaw;j++){
                            double a=j*Math.PI*2/k1/message.amount_yaw;
                            double x=message.pos.x+dx+rx*u.x*Math.cos(a)+rz*v.x*Math.sin(a);
                            double y=message.pos.y+dy+rx*u.y*Math.cos(a)+rz*v.y*Math.sin(a);
                            double z=message.pos.z+dz+rx*u.z*Math.cos(a)+rz*v.z*Math.sin(a);
                            //mc.player.sendMessage(new TextComponentTranslation(x+" "+y+" "+z));

                            mc.world.spawnParticle(types,message.ignoreRange,x,y,z,message.vel.x,message.vel.y,message.vel.z);
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
