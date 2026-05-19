package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemSteamChain extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel,ISteamWeapon {
    public int type;

    public ItemSteamChain(String name, ToolMaterial material, int type) {
        super(name, material);
        this.setMaxDamage(100);
        this.type=type;
    }

    public int getLevel(){
        return 2;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack I = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(I) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && ISteamWeapon.shrinkSteam(I,type==0?10:30)){
            playerIn.getCooldownTracker().setCooldown(I.getItem(), 200);
            Vec3d D = playerIn.getLookVec();
            if(D.x*D.x+D.z*D.z != 0) {
                playerIn.knockBack(playerIn, 0.6F, D.x, D.z);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.STEAM , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                for(int a=0;a<150;a++){
                    playerIn.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, playerIn.posX + (Math.random() - 0.5D) * 3, playerIn.posY + (Math.random() - 0.5D) * 3, playerIn.posZ + (Math.random() - 0.5D) * 3, D.x, 0.2D, D.z);
                }
                if(!worldIn.isRemote) {
                    List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                    for (int i = 0; i <= G.size() - 1; i++) {
                        if (ShouldDamage(G.get(i),playerIn,D) && G.get(i) != playerIn && !G.get(i).isPotionActive(MobEffects.FIRE_RESISTANCE) && !(G.get(i) instanceof EntityArmorStand)) {
                            if(type==0){
                                G.get(i).attackEntityFrom(ModDamageSource.causeSteamDamage(playerIn,playerIn), 62);
                            }else if(type==1){
                                G.get(i).attackEntityFrom(ModDamageSource.causeSteamDamage(playerIn,playerIn), 330);
                            }
                            G.get(i).setFire(8);
                            G.get(i).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,10));
                        }
                    }
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    protected boolean ShouldDamage(EntityLivingBase Entity, EntityPlayer player, Vec3d Vec3d){
        double x1 = player.posX;
        double y1 = player.posY;
        double z1 = player.posZ;
        double x2 = x1 + 6* Vec3d.x;
        double z2 = z1 + 6* Vec3d.z;
        double x3 = Entity.posX;
        double y3 = Entity.posY;
        double z3 = Entity.posZ;
        double d1 = Math.abs((z1-z2)*x3+(x2-x1)*z3+(x1-x2)*z1-(z1-z2)*x1)/Math.sqrt((z1-z2)*(z1-z2)+(x1-x2)*(x1-x2));
        double x4 = (x1+x2)/2;
        double z4 = (z1+z2)/2;
        double d2 = Math.abs((x1-x2)*x3+(z1-z2)*z3+(x2-x1)*x4+(z2-z1)*z4)/Math.sqrt((x2-x1)*(x2-x1)+(z2-z1)*(z2-z1));
        double d3 = Math.abs(y1-y3);
        return (d1 <1.5 && d2 <3 && d3 <1);
    }

    @Override
    public float maxItemSteam() {
        return type==0?100:300;
    }

    public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        if(type==1){
            attacker.addPotionEffect(new PotionEffect(com.somebody.idlframewok.potion.ModPotions.KANG_KAI_JI_ANG,200,1));
        }

        return true;
    }

    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entityLiving)
    {
        return true;
    }
}
