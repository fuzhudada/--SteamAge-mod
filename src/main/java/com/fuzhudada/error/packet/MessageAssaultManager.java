package com.fuzhudada.error.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAssaultManager implements IMessageHandler<MessageAssault, IMessage> {
    @Override
    public IMessage onMessage(MessageAssault message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {        EntityPlayerSP player=mc.player;
            player.motionX = 0.5 * (message.x - player.posX);
            player.motionZ = 0.5 * (message.z - player.posZ);

            if(message.particleType==0){
                for(int i=0;i<50;i++){
                    double a=mc.world.rand.nextDouble()*2*Math.PI;
                    double x=player.posX+3*Math.cos(a);
                    double y=player.posY+1+3*mc.world.rand.nextDouble();
                    double z=player.posZ+3*Math.sin(a);
                    mc.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,x,y,z,0.1*(player.posX-x),0.1*(player.posY+1-y),0.1*(player.posZ-z));
                }
            }else if(message.particleType==1){
                for(int i=0;i<8;i++){
                    double x=player.posX+mc.world.rand.nextDouble()-0.5;
                    double y=player.posY+0.5+mc.world.rand.nextDouble();
                    double z=player.posZ+mc.world.rand.nextDouble()-0.5;
                    mc.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,x,y,z,0.1*mc.world.rand.nextDouble()-0.05,0.1*mc.world.rand.nextDouble()-0.05,0.1*mc.world.rand.nextDouble()-0.05);
                }
            }else{
                for(int i=0;i<50;i++){
                    mc.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,player.posX,player.posY+1,player.posZ,0.3*mc.world.rand.nextDouble()-0.15,0.3*mc.world.rand.nextDouble()-0.15,0.3*mc.world.rand.nextDouble()-0.15);
                }
            }});
        return null;
    }
}
