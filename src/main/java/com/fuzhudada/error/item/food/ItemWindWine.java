package com.fuzhudada.error.item.food;

import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemWindWine extends ItemDrink{
    public ItemWindWine(Block block , PotionEffect potionEffect , int coolDown) {
        super(block , potionEffect , coolDown);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0 && !worldIn.isRemote){
            List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class , e -> e.isEntityAlive()
                    && playerIn.getDistance(e) < 12 && !(e instanceof EntityArmorStand));
            for (int i = 0; i <= G.size() - 1; i++) {
                double d=playerIn.getDistance(G.get(i));
                if(d!=0){
                    G.get(i).motionY += Math.min(3.2/d,1.2);
                    G.get(i).motionX = (G.get(i).posX-playerIn.posX) * 2 / d;
                    G.get(i).motionZ = (G.get(i).posZ-playerIn.posZ) * 2 / d;
                }
            }
            EntityHuoLong.spawnParticle(playerIn.posX , playerIn.posY+1 , playerIn.posZ , 13 , worldIn);
        }
        return super.onItemRightClick(worldIn,playerIn,handIn);
    }
}
