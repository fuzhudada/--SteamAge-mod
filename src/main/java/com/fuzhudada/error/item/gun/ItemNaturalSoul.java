package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.entity.projectiles.EntityNaturalSoul;
import com.fuzhudada.error.item.bow.ItemShiZiNu;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class ItemNaturalSoul extends ItemGun implements IWand {
    public ItemNaturalSoul(String name , int level , int[] type , float[] damage , int[] cooldown) {
        super(name , level , type , damage , cooldown);
        setMaxDamage(6);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            if(playerIn.isSneaking() && playerIn.getHeldItem(handIn).getItemDamage()==0 && mana(800,50,playerIn)){
                Vec3d F = playerIn.getLookVec().normalize();
                EntityNaturalSoul g = new EntityNaturalSoul(worldIn);
                g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0);
                g.setNoGravity(true);
                g.Damage=480;
                g.ATTACKER=playerIn;
                g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + 1.625+1.5*F.y, playerIn.posZ + 1.5 * F.z);
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(g);
                }
                if (F.x * F.x + F.z * F.z != 0) {
                    playerIn.knockBack(playerIn, 0.5f, F.x, F.z);
                }
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.NATURAL_SOUL , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
                if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                }
                playerIn.getHeldItem(handIn).setItemDamage(playerIn.getHeldItem(handIn).getMaxDamage());
            }else{
                int j=bullet(playerIn,level,type);
                if(j != -1) {
                    int type = ItemBullet.GetBulletType(getItemStackFromIndex(playerIn,j).getItem());
                    int index= Arrays.binarySearch(this.type,type);
                    if(index != -1) {
                        shoot(worldIn, playerIn,handIn, type, damage[index]);
                        shrinkBullet(playerIn,playerIn.getHeldItem(handIn), j, 1);
                        playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), cooldown[index]);
                        if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                        }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                        }
                        if(playerIn.getHeldItem(handIn).getItemDamage()>0) playerIn.getHeldItem(handIn).setItemDamage(playerIn.getHeldItem(handIn).getItemDamage()-1);
                        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                    }
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
