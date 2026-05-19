package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.weapon.EntityLieFengQiQiang;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

import static com.fuzhudada.error.item.sword.ItemTuCi.ShouldDamage;

public class ItemLieFengQiQiang extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemLieFengQiQiang(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 2;
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
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
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

    @Override
    public void serverUseTick(ItemStack stack, EntityLivingBase living, int count) {
        if(count == 1
                && living instanceof EntityPlayer
                && living.isPotionActive(ModPotions.CEN_DIAN)
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0) {
            EntityLieFengQiQiang g = new EntityLieFengQiQiang(living.getEntityWorld());
            g.setAttackerName(living.getName());
            g.setLocationAndAngles(living.posX, living.posY, living.posZ, living.rotationYaw, living.rotationPitch);
            if (!living.getEntityWorld().isRemote) {
                living.getEntityWorld().spawnEntity(g);
            }
        }
        super.serverUseTick(stack, living, count);
    }

    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer playerIn = (EntityPlayer)entityLiving;
            Vec3d D = playerIn.getLookVec();
            if(D.x*D.x+D.z*D.z != 0) {
                playerIn.knockBack(playerIn, 2, -D.x, -D.z);
                playerIn.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20, 29));
                boolean flag=false;
                if(!worldIn.isRemote) {
                    List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                            && !(e instanceof EntityPlayer)
                            && !(e instanceof EntityArmorStand)
                            && ShouldDamage(e,playerIn,10,0.5,1));
                    for (int i = 0; i <= G.size() - 1; i++) {
                        G.get(i).attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 280);
                        flag=true;
                    }
                }

                if(flag)  worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.ASSASSIN, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
             }
            playerIn.getCooldownTracker().setCooldown(stack.getItem(), 100);
        }
    }
}
