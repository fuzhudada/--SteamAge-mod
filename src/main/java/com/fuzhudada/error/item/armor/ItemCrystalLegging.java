package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.PointShrink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemCrystalLegging extends com.somebody.idlframewok.item.ItemArmorBase implements IWand {
    public ItemCrystalLegging(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull ItemStack itemStack) {
        if(ModEnchantmentInit.PEI.getEnchantmentLevel(itemStack)==0
                && !player.isPotionActive(MobEffects.POISON)
                && player.getHealth()<0.2*player.getMaxHealth()
                && player.getCooldownTracker().getCooldown(itemStack.getItem(),0)==0
                && mana(1500,200,player)){
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,160,3));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,400,1));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,400,1));
            player.sendMessage(new TextComponentTranslation("legging.crystal"));
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
            if(!world.isRemote){
                PacketHandler.INSTANCE.sendTo(new PointShrink("FIREWORKS_SPARK",player.posX,player.posY+1,player.posZ,3,3,3,-0.1,100),(EntityPlayerMP) player);
            }

            player.getCooldownTracker().setCooldown(itemStack.getItem(),1200);
        }
        super.onArmorTick(world, player, itemStack);
    }
}
