package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.projectiles.EntityShuangZi;
import com.fuzhudada.error.item.shield.ItemShieldBase;
import com.fuzhudada.error.item.shield.NPCmodShield;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class ItemShuangZiFu extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemShuangZiFu(String name, ToolMaterial material) {
        super(name, material);
        this.setMaxDamage(2000);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    public int getLevel(){
        return 2;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack I = playerIn.getHeldItem(handIn);
        ItemStack shield = handIn.equals(EnumHand.MAIN_HAND) ? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand();
        if (I.getItemDamage() < 1900
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) == 0
                && !playerIn.isPotionActive(MobEffects.POISON)) {
            if(IsShieldII(shield, playerIn) && !playerIn.isSneaking()){
                playerIn.getCooldownTracker().setCooldown(I.getItem(), 100);
                Vec3d D = playerIn.getLookVec();
                if (D.x * D.x + D.z * D.z != 0) {
                    playerIn.knockBack(playerIn, 2, -D.x, -D.z);
                    playerIn.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20, 9));

                    if (!worldIn.isRemote) {
                        List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                        for (int i = 0; i <= G.size() - 1; i++) {
                            if (ShouldDamage(G.get(i), playerIn, D) && G.get(i) != playerIn && !(G.get(i) instanceof EntityArmorStand)) {
                                G.get(i).attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 50);
                                G.get(i).knockBack(playerIn, 2, -D.x, -D.z);
                            }
                        }
                    }
                }
            }

            if(playerIn.isPotionActive(com.somebody.idlframewok.potion.ModPotions.CEN_DIAN)){
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
                playerIn.getCooldownTracker().setCooldown(I.getItem(), 100);
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, I);
        }
        return new ActionResult<>(EnumActionResult.FAIL, I);
    }

    protected boolean ShouldDamage(EntityLivingBase Entity,EntityPlayer player,Vec3d Vec3d){
        double x1 = player.posX;
        double y1 = player.posY;
        double z1 = player.posZ;
        double x2 = x1 + 11* Vec3d.x;
        double z2 = z1 + 11* Vec3d.z;
        double x3 = Entity.posX;
        double y3 = Entity.posY;
        double z3 = Entity.posZ;
        double d1 = Math.abs((z1-z2)*x3+(x2-x1)*z3+(x1-x2)*z1-(z1-z2)*x1)/Math.sqrt((z1-z2)*(z1-z2)+(x1-x2)*(x1-x2));
        double x4 = (x1+x2)/2;
        double z4 = (z1+z2)/2;
        double d2 = Math.abs((x1-x2)*x3+(z1-z2)*z3+(x2-x1)*x4+(z2-z1)*z4)/Math.sqrt((x2-x1)*(x2-x1)+(z2-z1)*(z2-z1));
        double d3 = Math.abs(y1-y3);
        return (d1 <0.5 && d2 <5.5 && d3 <1);
    }

    protected boolean IsShieldII(ItemStack Item, EntityLivingBase entity) {
        return (isShield(Item, entity) || Item.getItem() instanceof ItemShieldBase || Arrays.asList(NPCmodShield.NPCShields).contains(Item.getUnlocalizedName()) || Item.getItem() instanceof ItemShuangZiJian);
    }
}
