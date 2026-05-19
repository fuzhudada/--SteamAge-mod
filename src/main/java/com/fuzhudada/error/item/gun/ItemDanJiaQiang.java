package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDanJiaQiang extends com.somebody.idlframewok.item.ItemBase implements IGun{
    public int ziDanShu;
    public float damage;
    public int coolDown;

    public ItemDanJiaQiang(String name,int ziDanShu,float damage,int coolDown) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(ziDanShu);
        this.ziDanShu=ziDanShu;
        this.damage=damage;
        this.coolDown=coolDown;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack stack=playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0 && onGunUse(worldIn,playerIn,handIn)) {
            if(stack.getItemDamage()<stack.getMaxDamage()) {
                shoot(worldIn,playerIn,handIn,getShootType(),damage);
                stack.setItemDamage(stack.getItemDamage() + 1);
                if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                }
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, getShootSound(), SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            }else{
                int j=bullet(playerIn,3,5);
                if(j != -1) {
                    shrinkBullet(playerIn,stack, j, 1);
                    stack.setItemDamage(0);
                    playerIn.getCooldownTracker().setCooldown(stack.getItem(), coolDown);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, getReloadSound(), SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public float getDamage() {
        return damage;
    }

    public SoundEvent getShootSound(){
        return ModSoundHandler.GUN1;
    }

    public SoundEvent getReloadSound(){
        return ModSoundHandler.GUN2;
    }

    public boolean onGunUse(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn){
        return true;
    }

    public int getShootType(){
        return 5;
    }
}
