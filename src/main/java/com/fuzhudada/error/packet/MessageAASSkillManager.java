package com.fuzhudada.error.packet;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class MessageAASSkillManager implements IMessageHandler<MessageAASSkill, IMessage> {
    @Override
    public IMessage onMessage(MessageAASSkill message , MessageContext ctx) {
        if(ctx.getServerHandler() == null) return null;
        EntityPlayerMP player = ctx.getServerHandler().player;
        World world=player.getEntityWorld();

        player.getServerWorld().addScheduledTask(() -> {
            ItemStack chest=player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if(message.type==0 && chest.getItem()== ModItems.AAS && ISteamWeapon.shrinkSteam(chest,20)){
                EntityHuoLong.spawnParticle(player.posX,player.posY,player.posZ,11,world);
                player.getCooldownTracker().setCooldown(chest.getItem(), 300);
                world.playSound(null, player.posX, player.posY, player.posZ, ModSoundHandler.AAS , SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.getDistance(player)<8
                        && !e.isPotionActive(MobEffects.FIRE_RESISTANCE)
                        && !(e instanceof EntityArmorStand)
                        && !(e instanceof EntityPlayer)
                        && e.posY-player.posY<1 && e.posY-player.posY>-8);
                for (int i = 0; i <= G.size() - 1; i++) {
                    G.get(i).attackEntityFrom(ModDamageSource.causeSteamDamage(player,player),360);
                    G.get(i).setFire(3);
                    if((player.posX - G.get(i).posX != 0) || (player.posZ - G.get(i).posZ != 0)) {
                        G.get(i).knockBack(player, 0.8f, player.posX - G.get(i).posX, player.posZ - G.get(i).posZ);
                    }
                }

                if(chest.getTagCompound() == null){
                    chest.setTagCompound(new NBTTagCompound());
                }
                NBTTagCompound nbt = chest.getTagCompound();
                if(nbt != null) {
                    nbt.setTag("fall", new NBTTagInt(1));
                }
                chest.setTagCompound(nbt);
            }

            ItemStack feet=player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if(message.type==1 && feet.getItem()== ModItems.PEN_QI_XIE && ISteamWeapon.shrinkSteam(feet,10)){
                EntityHuoLong.spawnParticle(player.posX,player.posY,player.posZ,12,world);
                player.getCooldownTracker().setCooldown(feet.getItem(), 200);
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                        && e != player
                        && player.getDistance(e)<3
                        && !e.isPotionActive(MobEffects.FIRE_RESISTANCE)
                        && !(e instanceof EntityArmorStand));
                for (int b = 0; b <= G.size() - 1; b++) {
                    G.get(b).setFire(8);
                    G.get(b).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 10));
                    G.get(b).attackEntityFrom(ModDamageSource.causeSteamDamage(player,player), 62);
                }

                if(feet.getTagCompound() == null){
                    feet.setTagCompound(new NBTTagCompound());
                }
                NBTTagCompound nbt = feet.getTagCompound();
                if(nbt != null) {
                    nbt.setTag("fall", new NBTTagInt(1));
                }
                feet.setTagCompound(nbt);
            }
        });
        return null;
    }
}
