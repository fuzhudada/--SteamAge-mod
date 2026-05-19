package com.fuzhudada.error;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

public class ClientForgeHookHandler {
    public ClientForgeHookHandler(){
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    //隐形光源
    @SubscribeEvent
    public void E(TickEvent.ClientTickEvent event){
        if(event.side== Side.CLIENT){
            Minecraft mc=Minecraft.getMinecraft();
            if(!mc.isGamePaused() && mc.world != null){
                int posX= MathHelper.floor(mc.player.posX), posY=MathHelper.floor(mc.player.posY), posZ=MathHelper.floor(mc.player.posZ);
                Random random = new Random();
                ItemStack itemstack = mc.player.getHeldItemMainhand();
                boolean flag = mc.playerController.getCurrentGameType() == GameType.CREATIVE && !itemstack.isEmpty() && itemstack.getItem() == Item.getItemFromBlock(ModBlocks.INVISIBLE_LIGHT_SOURCE);
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int j = 0; j < 667; ++j)
                {
                    this.showBarrierParticles(mc.world,posX, posY, posZ, 16, random, flag, blockpos$mutableblockpos);
                    this.showBarrierParticles(mc.world,posX, posY, posZ, 32, random, flag, blockpos$mutableblockpos);
                }
            }
        }
    }

    public void showBarrierParticles(WorldClient world, int x, int y, int z, int offset, Random random, boolean holdingBarrier, BlockPos.MutableBlockPos pos)
    {
        int i = x + random.nextInt(offset) - random.nextInt(offset);
        int j = y + random.nextInt(offset) - random.nextInt(offset);
        int k = z + random.nextInt(offset) - random.nextInt(offset);
        pos.setPos(i, j, k);
        IBlockState iblockstate = world.getBlockState(pos);
        iblockstate.getBlock().randomDisplayTick(iblockstate, world, pos, random);

        if (holdingBarrier && iblockstate.getBlock() == ModBlocks.INVISIBLE_LIGHT_SOURCE)
        {
            world.spawnParticle(ModParticleHandler.INVISIBLE_LIGHT_SOURCE , (float)i + 0.5F , (float)j + 0.5F , (float)k + 0.5F , 0.0D, 0.0D, 0.0D);
        }
    }
}
