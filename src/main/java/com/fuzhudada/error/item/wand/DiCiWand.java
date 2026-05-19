package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.entity.weapon.EntityDiCi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DiCiWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public DiCiWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0) {
            if(mana(400,44,playerIn)) {
                Vec3d F = playerIn.getLookVec();
                Vec3d F1=new Vec3d(F.x,0,F.z).normalize().scale(2);
                for(int i=1;i<=5;i++) {
                    double x=playerIn.posX+i*F1.x;
                    double z=playerIn.posZ+i*F1.z;
                    EntityDiCi g = new EntityDiCi(worldIn, x,playerIn.posY,z,playerIn.rotationYaw,0,playerIn,90);
                    if(!worldIn.isRemote){
                        worldIn.spawnEntity(g);
                    }
                }
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 44);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.EVOCATION_FANGS_ATTACK, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
