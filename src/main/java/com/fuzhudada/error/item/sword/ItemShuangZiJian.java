package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.projectiles.EntityShuangZi;
import com.fuzhudada.error.item.gun.IGun;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemShuangZiJian extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemShuangZiJian(String name, ToolMaterial material) {
        super(name, material);
        this.setMaxDamage(3000);
        this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    public int getLevel(){
        return 2;
    }

    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 72000;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag=false;
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            if(itemstack.getItemDamage() < itemstack.getMaxDamage()-30 && !(playerIn.getHeldItemMainhand().getItem() instanceof IGun)){
                playerIn.setActiveHand(handIn);
                flag=true;
            }else{
                if((itemstack.getItemDamage() >= itemstack.getMaxDamage()-30) && !playerIn.isPotionActive(ModPotions.CEN_DIAN)) playerIn.sendMessage(new TextComponentTranslation("shield.broken"));
                playerIn.resetActiveHand();
            }

            if(!playerIn.isPotionActive(MobEffects.POISON) && playerIn.isPotionActive(ModPotions.CEN_DIAN)){
                if(!canUse(playerIn,handIn)){
                    return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                }

                Vec3d V = playerIn.getLookVec();
                EntityShuangZi g = new EntityShuangZi(worldIn);
                g.motionX = V.x;
                g.motionY = 0;
                g.motionZ = V.z;
                g.setPosition(playerIn.posX + V.x, playerIn.posY+0.1, playerIn.posZ + V.z);
                g.ATTACKER = playerIn;
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(g);
                }
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.HUANG_JIN_BAN_HU , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                playerIn.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
                flag=true;
            }
        }

        if(flag){
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }else{
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }
    }
}
