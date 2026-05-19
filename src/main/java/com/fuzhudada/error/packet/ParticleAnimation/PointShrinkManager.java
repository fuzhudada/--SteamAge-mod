package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PointShrinkManager implements IMessageHandler<PointShrink, IMessage> {
    @Override
    public IMessage onMessage(PointShrink message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {try{
            EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
            for(int i=0;i<message.amount;i++){
                double x=message.pos.x+2*Math.random()*message.siz.x-message.siz.x;
                double y=message.pos.y+2*Math.random()*message.siz.y-message.siz.y;
                double z=message.pos.z+2*Math.random()*message.siz.z-message.siz.z;
                mc.world.spawnParticle(types,message.ignoreRange,x,y,z, message.speed*(message.pos.x-x),message.speed*(message.pos.y-y),message.speed*(message.pos.z-z));
            }
        }catch (IllegalArgumentException e){
            mc.player.sendMessage(new TextComponentTranslation("qwq"));
        }});
        return null;
    }
}
