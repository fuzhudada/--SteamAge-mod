package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityStormSword;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.somebody.idlframewok.potion.ModPotions;
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

public class ItemStormSword extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemStormSword(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 5;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON) && mana(3300,200,playerIn)) {
            EntityStormSword g = new EntityStormSword(worldIn,playerIn,200,8);
            g.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(itemstack.getItem(), 1200);

            if(playerIn.isPotionActive(ModPotions.CEN_DIAN)){
                List<Entity> G = worldIn.getEntities(Entity.class,
                        e -> playerIn.getDistance(e)<16
                                && e.isEntityAlive()
                                && !e.equals(playerIn)
                                && !(e instanceof EntityItem)
                                && !(e instanceof EntityStormSword)
                                && !(e instanceof EntityArmorStand)
                                && !(e instanceof EntityPlayer));
                for(Entity e:G){
                    e.attackEntityFrom(ModDamageSource.causeElectricalDamage(playerIn),470);
                    e.hurtResistantTime=0;

                    List<EntityPlayerMP> P = playerIn.world.getEntities(EntityPlayerMP.class, e1->e1.getDistance(playerIn)<64);
                    for (EntityPlayerMP player : P) {
                        PacketHandler.INSTANCE.sendTo(new MessageParticle(e.posX,e.posY,e.posZ,new Vec3d(e.posX,e.posY+48,e.posZ),7),player);
                    }
                }

                if(G.size()>0){
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}