package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.entity.model.EntityCrystalShield;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemCrystalShieldCreative extends com.somebody.idlframewok.item.ItemBase {
    public ItemCrystalShieldCreative(String name) {
        super(name);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(!worldIn.isRemote){
            Vec3d F = playerIn.getLookVec();
            EntityCrystalShield g = new EntityCrystalShield(worldIn);
            g.setNoGravity(true);
            g.DAMAGE = 165;
            g.ATTACKER = playerIn;
            g.setAttackerName(playerIn.getName());
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.setVec((float) playerIn.getLookVec().x,(float) Math.max(0,playerIn.getLookVec().y),(float) playerIn.getLookVec().z);
            worldIn.spawnEntity(g);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
