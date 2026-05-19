package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.projectiles.EntityGeneralProjectiles;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemJieLvJian extends com.somebody.idlframewok.item.ItemSwordBase {
    public ItemJieLvJian(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack , @Nonnull EntityLivingBase target , @Nonnull EntityLivingBase attacker) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0
                && Math.random()<0.2
                && attacker.getHeldItemOffhand().getItem().equals(ModItems.JIE_LV_DUN)
                && !attacker.isPotionActive(MobEffects.POISON)
                && attacker.isPotionActive(com.somebody.idlframewok.potion.ModPotions.CEN_DIAN)) {
            double r=Math.PI*2*Math.random();
            float vx= (float) ((float) 0.2*Math.cos(r)),vz= (float) ((float) 0.2*Math.sin(r));
            EntityGeneralProjectiles.shootInSpecificDirection(attacker.world,target, ModDamageSource.EnumModDamageSource.Normal,0,600,"reddust",false,vx,0.2F,vz,true,false,(x,y) -> {
                EntityItem entityItem=new EntityItem(x.world);
                entityItem.setItem(new ItemStack(ModItems.BLOODY_MEET,1));
                entityItem.setPosition(x.posX,x.posY,x.posZ);
                if(! x.world.isRemote){
                    x.world.spawnEntity(entityItem);
                }
            });
        }

        return super.hitEntity(stack , target , attacker);
    }
}
