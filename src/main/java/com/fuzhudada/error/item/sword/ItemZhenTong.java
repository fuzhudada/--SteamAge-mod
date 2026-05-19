package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

public class ItemZhenTong extends com.somebody.idlframewok.item.ItemSwordBase implements ISteamWeapon,skillLevel{
    public ItemZhenTong(String name , ToolMaterial material) {
        super(name , material);
        this.setMaxDamage(400);
        this.addPropertyOverride(new ResourceLocation("pull"), (stack, worldIn, entityIn) -> {
            if(entityIn != null && entityIn.getActiveItemStack().getItem() == this){
                int i=stack.getMaxItemUseDuration() - entityIn.getItemInUseCount();
                i=(int)Math.min((float)i,getTimeFromSteam(ISteamWeapon.getSteam(stack)));
                return (float)i / 20.0F;
            }else{
                int k=EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                if(k==1){
                    return 0.1F;
                }else if(k==2){
                    return 0.7F;
                }else if(k >=3){
                    return 1.5F;
                }
                return 0;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return (entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack && ISteamWeapon.getSteam(stack)>=24) || EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack)>0 ? 1.0F : 0.0F;
            }
        });
    }

    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i=(int)Math.min((float)i,getTimeFromSteam(ISteamWeapon.getSteam(stack)));
            if (i < 0) return;

            ISteamWeapon.shrinkSteam(stack,getSteamFromTime(i));
            entityplayer.getCooldownTracker().setCooldown(stack.getItem(), 200);
            stack.addEnchantment(Objects.requireNonNull(Enchantment.getEnchantmentByID(48)) ,getSteamFromTime(i)/25+1);
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, ModSoundHandler.STEAM_HAMMER, SoundCategory.PLAYERS, 2F, entityplayer.getRNG().nextFloat() * 0.1F + 0.9F);
        }
    }

    private int getTimeFromSteam(float steam){
        if(steam<24){
            return -1;
        }else if(steam<40){
            return 11;
        }else if(steam<64){
            return 27;
        }else{
            return Integer.MAX_VALUE;
        }
    }

    private int getSteamFromTime(int time){
        if(time<12){
            return 24;
        }else if(time<28){
            return 40;
        }else{
            return 64;
        }
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)) {
            if(playerIn.isSneaking() && EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER,itemstack)>0){
                if (itemstack.getTagCompound() != null && !itemstack.getTagCompound().getTagList("ench", 10).hasNoTags()) {
                    Map<Enchantment,Integer> map=EnchantmentHelper.getEnchantments(itemstack);
                    map.remove(Enchantments.POWER);
                    EnchantmentHelper.setEnchantments(map,itemstack);
                }
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            }else if(EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER,itemstack)<=0){
                playerIn.setActiveHand(handIn);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }

    @Override
    public float maxItemSteam() {
        return 400;
    }

    public boolean hitEntity(ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        if(EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.POWER)) {
            target.setFire(8);
            target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 10));
            for(int a=0;a<150;a++){
                target.world.spawnParticle(EnumParticleTypes.CRIT, target.posX + (Math.random() - 0.5D) * 3, target.posY + (Math.random() - 0.5D) * 3 + target.getEyeHeight(), target.posZ + (Math.random() - 0.5D) * 3, 0,0,0);
            }

            Map<Enchantment,Integer> map=EnchantmentHelper.getEnchantments(stack);
            map.remove(Enchantments.POWER);
            EnchantmentHelper.setEnchantments(map,stack);
        }
        return true;
    }

    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entityLiving)
    {
        return true;
    }

    @Override
    public int getLevel() {
        return 4;
    }

    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 72000;
    }

    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
