package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LineShrinkManager implements IMessageHandler<LineShrink, IMessage> {
    @Override
    public IMessage onMessage(LineShrink message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            try{
                EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
                double len=message.pos1.distanceTo(message.pos2);
                Vec3d pos3=new Vec3d((message.pos1.x+message.pos2.x)/2,(message.pos1.y+message.pos2.y)/2,(message.pos1.z+message.pos2.z)/2);
                double x1=pos3.x+(message.pos1.x-message.pos2.x)*message.siz/len;
                double y1=pos3.y+(message.pos1.y-message.pos2.y)*message.siz/len;
                double z1=pos3.z+(message.pos1.z-message.pos2.z)*message.siz/len;
                double x2=pos3.x+(message.pos2.x-message.pos1.x)*message.siz/len;
                double y2=pos3.y+(message.pos2.y-message.pos1.y)*message.siz/len;
                double z2=pos3.z+(message.pos2.z-message.pos1.z)*message.siz/len;
                for(int i=0;i<message.amount;i++){
                    double t=((double) i)/message.amount;
                    double x=(1-t)*x1+t*x2+message.thickness*2*(Math.random()-0.5);
                    double y=(1-t)*y1+t*y2+message.thickness*2*(Math.random()-0.5);
                    double z=(1-t)*z1+t*z2+message.thickness*2*(Math.random()-0.5);
                    mc.world.spawnParticle(types,message.ignoreRange,x,y,z,message.speed*(pos3.x-x),message.speed*(pos3.y-y),message.speed*(pos3.z-z));
                }
            }catch (IllegalArgumentException e){
                mc.player.sendMessage(new TextComponentTranslation("qwq"));
            }
        });
        return null;
    }
}
