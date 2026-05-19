package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.projectiles.EntityWuZuoBianTa;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemWuZuoBianTa extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemWuZuoBianTa(String name, ToolMaterial material) {
        super(name, material);
        this.setMaxDamage(10);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer player, @Nonnull EnumHand handIn) {

        if(!canUse(player,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(handIn));
        }

        if (com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(player.getHeldItem(handIn)) ==0
                && !player.isPotionActive(MobEffects.POISON)) {
            ItemStack stack=player.getHeldItem(handIn);
            if(stack.getItemDamage()<stack.getMaxDamage()){
                if(mana(100,200,player)){
                    Vec3d F = player.getLookVec();
                    EntityWuZuoBianTa g = new EntityWuZuoBianTa(player.getEntityWorld());
                    g.motionX=F.x+0.2*Math.random()-0.1;
                    g.motionY=F.y+0.2*Math.random()-0.1;
                    g.motionZ=F.z+0.2*Math.random()-0.1;
                    g.setNoGravity(true);
                    g.DAMAGE = 45;
                    if(stack.getItemDamage()==stack.getMaxDamage()-1) g.BLOOD=true;
                    g.setPosition(player.posX + 1.5 * F.x, player.posY + player.getEyeHeight() + 1.5 * F.y, player.posZ + 1.5 * F.z);
                    g.ATTACKER = player;
                    if (!player.getEntityWorld().isRemote) {
                        player.getEntityWorld().spawnEntity(g);
                    }
                    stack.setItemDamage(stack.getItemDamage()+1);
                    worldIn.playSound(null, player.posX, player.posY, player.posZ, ModSoundHandler.WU_ZUO_BIAN_TA, SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }else{
                stack.setItemDamage(0);
                player.getCooldownTracker().setCooldown(stack.getItem(), 200);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(handIn));
    }

    public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        return true;
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
