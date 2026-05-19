package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.PointShrink;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemRocketGloves extends ItemZhenTong{
    public ItemRocketGloves(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public void serverUseTick(ItemStack stack, EntityLivingBase living, int count) {
        if(ISteamWeapon.getSteam(stack)>1 && living.isElytraFlying()
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0) {
            List<EntityPlayerMP> P = living.world.getEntities(EntityPlayerMP.class, e->living.getDistance(e)<64);
            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new PointShrink("LAVA",living.posX,living.posY,living.posZ,0.1,0.1,0.1,0.1,2),player);
            }

            if(count%8==1) ISteamWeapon.shrinkSteam(stack,1);
        }
        super.serverUseTick(stack, living, count);
    }

    @Override
    public void clientUseTick(ItemStack stack, EntityLivingBase living, int count) {
        if(ISteamWeapon.getSteam(stack)>1
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0) {
            if(living.isElytraFlying()){
                living.motionX=1.5*living.getLookVec().x;
                living.motionY=1.5*living.getLookVec().y;
                living.motionZ=1.5*living.getLookVec().z;
            }
        }
        super.clientUseTick(stack, living, count);
    }
}
