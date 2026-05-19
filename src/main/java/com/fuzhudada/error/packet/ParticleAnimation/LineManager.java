package com.fuzhudada.error.packet.ParticleAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LineManager implements IMessageHandler<Line, IMessage> {
    @Override
    public IMessage onMessage(Line message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            try{
                EnumParticleTypes types=EnumParticleTypes.valueOf(message.types);
                for(int i=0;i<message.amount;i++){
                    double t=((double) i)/message.amount;
                    mc.world.spawnParticle(types,message.ignoreRange,(1-t)*message.pos1.x+t*message.pos2.x+message.thickness*2*(Math.random()-0.5),(1-t)*message.pos1.y+t*message.pos2.y+message.thickness*2*(Math.random()-0.5),(1-t)*message.pos1.z+t*message.pos2.z+message.thickness*2*(Math.random()-0.5),message.vel.x,message.vel.y,message.vel.z);
                }
            }catch (IllegalArgumentException e){
                mc.player.sendMessage(new TextComponentTranslation("qwq"));
            }
        });
        return null;
    }
}
