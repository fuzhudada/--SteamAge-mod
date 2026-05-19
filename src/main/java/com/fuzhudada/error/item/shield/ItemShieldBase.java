package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.gun.IGun;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemShieldBase extends com.somebody.idlframewok.item.ItemBase {
    public SoundEvent soundEvents;

    public ItemShieldBase(String name, int damage) {
        super(name);
        this.setMaxDamage(damage);
        this.setMaxStackSize(1);
        soundEvents=null;
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    public ItemShieldBase(String name, int damage, SoundEvent soundEvent) {
        super(name);
        this.setMaxDamage(damage);
        this.setMaxStackSize(1);
        this.soundEvents=soundEvent;
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
            return EnumAction.BLOCK;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 72000;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn))==0
                && (itemstack.getItemDamage() < itemstack.getMaxDamage()-30)
                && !(playerIn.getHeldItemMainhand().getItem() instanceof IGun)){
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }

        if((itemstack.getItemDamage() >= itemstack.getMaxDamage()-30)) playerIn.sendMessage(new TextComponentTranslation("shield.broken"));
        playerIn.resetActiveHand();
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }

    public SoundEvent getSound(){
        return soundEvents;
    }
}
