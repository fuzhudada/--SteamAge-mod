package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PointManager implements IMessageHandler<Point, IMessage> {
    @Override
    public IMessage onMessage(Point message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {try{
            EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
            mc.world.spawnParticle(types,message.ignoreRange,message.pos.x,message.pos.y,message.pos.z,message.vel.x,message.vel.y,message.vel.z);
        }catch (IllegalArgumentException e){
            mc.player.sendMessage(new TextComponentTranslation("qwq"));
        }});
        return null;
    }
}
