package com.fuzhudada.error.packet;

import com.fuzhudada.error.entity.model.EntityFireHorse;
import com.fuzhudada.error.entity.model.EntityRiderElephant;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageCCBManager implements IMessageHandler<MessageCCB, IMessage> {
    @Override
    public IMessage onMessage(MessageCCB message , MessageContext ctx) {
        if(ctx.getServerHandler() == null) return null;
        EntityPlayerMP player = ctx.getServerHandler().player;
        World world=player.getEntityWorld();

        player.getServerWorld().addScheduledTask(() -> {
            if(message.type==0){
                if(player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityRiderElephant){
                    EntityRiderElephant entityRiderElephant= (EntityRiderElephant) player.getRidingEntity();
                    entityRiderElephant.startCCB();
                }
            }

            if(message.type==1){
                if(player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityFireHorse){
                    EntityFireHorse entityFireHorse= (EntityFireHorse) player.getRidingEntity();
                    entityFireHorse.startRun();
                }
            }
        });
        return null;
    }
}
