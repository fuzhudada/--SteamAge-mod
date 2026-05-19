package com.fuzhudada.error.item.wand;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class MoLiJuJiQi extends com.somebody.idlframewok.item.ItemBase {
    public int RS;
    public MoLiJuJiQi(String name,int recoverspeed,int total) {
        super(name);
        this.setMaxDamage(total);
        this.setMaxStackSize(1);
        this.RS = recoverspeed;
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        if(entityIn instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entityIn;

            boolean flag=false;
            if(!player.isPotionActive(com.somebody.idlframewok.potion.ModPotions.MO_LI_PIN_JI)){
                for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                    ItemStack itemstack = player.inventory.getStackInSlot(i);
                    if (itemstack.getItem() instanceof MoLiJuJiQi) {
                        if(itemstack==stack){
                            flag=true;
                            int RS0=RS;
                            int RS1=player.getCooldownTracker().getCooldown(itemstack.getItem(), 0) == 0?RS0:0;
                            if(player.isPotionActive(com.somebody.idlframewok.potion.ModPotions.MIN_XIANG_XIANG) && Math.random()<0.5){
                                RS1=RS1+RS0*(Objects.requireNonNull(player.getActivePotionEffect(com.somebody.idlframewok.potion.ModPotions.MIN_XIANG_XIANG)).getAmplifier()+1);
                            }

                            if (RS1 <= itemstack.getItemDamage()) {
                                itemstack.setItemDamage(itemstack.getItemDamage() - RS1);
                            } else {
                                itemstack.setItemDamage(0);
                            }
                        }

                        if(!flag){
                            stack.setItemDamage(stack.getMaxDamage());
                        }
                        break;
                    }
                }
            }else{
                stack.setItemDamage(stack.getMaxDamage());
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public boolean showDurabilityBar(@Nonnull ItemStack stack) {
        return true;
    }
}
