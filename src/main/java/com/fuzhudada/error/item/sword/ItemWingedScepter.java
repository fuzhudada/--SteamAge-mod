package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityStormSword;
import com.fuzhudada.error.entity.weapon.EntityWingedScepter;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemWingedScepter extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemWingedScepter(String name, ToolMaterial material) {
        super(name, material);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && mana(2000,200,playerIn)) {
            EntityWingedScepter g = new EntityWingedScepter(worldIn,playerIn,600,16,16);
            g.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(itemstack.getItem(), 600);

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public int getLevel() {
        return 5;
    }
}
