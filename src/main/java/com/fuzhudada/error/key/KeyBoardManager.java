package com.fuzhudada.error.key;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.model.EntityFireHorse;
import com.fuzhudada.error.entity.model.EntityRiderElephant;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.packet.MessageAASSkill;
import com.fuzhudada.error.packet.MessageBirdChest;
import com.fuzhudada.error.packet.MessageCCB;
import com.fuzhudada.error.packet.PacketHandler;
import com.somebody.idlframewok.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class KeyBoardManager{
    public static void init() {
        for (KeyBinding key:
                com.somebody.idlframewok.proxy.ClientProxy.KEY_BINDINGS) {
            ClientRegistry.registerKeyBinding(key);
        }
        com.somebody.idlframewok.IdlFramework.Log("Registered %d keys", com.somebody.idlframewok.proxy.ClientProxy.KEY_BINDINGS.size());
    }

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        Minecraft mc=Minecraft.getMinecraft();
        EntityPlayerSP player=mc.player;
        if (ClientProxy.ARMOR_SKILL.isPressed()) {
            if(player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityRiderElephant){
                EntityRiderElephant entityRiderElephant= (EntityRiderElephant) player.getRidingEntity();
                entityRiderElephant.startCCB();
                PacketHandler.INSTANCE.sendToServer(new MessageCCB(0));
            }

            if(player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityFireHorse){
                EntityFireHorse entityFireHorse= (EntityFireHorse) player.getRidingEntity();
                entityFireHorse.startRun();
                PacketHandler.INSTANCE.sendToServer(new MessageCCB(1));
            }

            ItemStack chest=player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack feet=player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if(chest.getItem()== ModItems.AAS
                    && player.getCooldownTracker().getCooldown(chest.getItem(),0)<=0
                    && ISteamWeapon.getSteam(chest)>=20
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(chest)==0){
                player.motionY+=1;
                player.getCooldownTracker().setCooldown(chest.getItem(), 300);
                PacketHandler.INSTANCE.sendToServer(new MessageAASSkill());
                return;
            }

            if(chest.getItem()== ModItems.BIRD_CHEST
                    && player.getCooldownTracker().getCooldown(chest.getItem(),0)<=0
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(chest)==0
                    && IWand.mana1(3000,200,player)){
                player.motionX=2*player.getLookVec().x;
                player.motionY+=1;
                player.motionZ=2*player.getLookVec().z;
                player.getCooldownTracker().setCooldown(chest.getItem(), 600);
                PacketHandler.INSTANCE.sendToServer(new MessageBirdChest(player.ticksExisted));
                return;
            }

            if(feet.getItem()== ModItems.PEN_QI_XIE
                    && player.getCooldownTracker().getCooldown(feet.getItem(),0)<=0
                    && ISteamWeapon.getSteam(feet)>=10
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(feet)==0){
                player.motionY+=1;
                player.getCooldownTracker().setCooldown(feet.getItem(), 200);
                PacketHandler.INSTANCE.sendToServer(new MessageAASSkill(1));
            }

            ItemStack invisibleChest=player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if(ModEnchantmentInit.isArmorAllEquiped(ModItems.INVISIBLE_HEAD,ModItems.INVISIBLE_CHEST,ModItems.INVISIBLE_LEG,ModItems.INVISIBLE_BOOT,player)
                    && player.getCooldownTracker().getCooldown(invisibleChest.getItem(),0)<=0
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD))==0
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(invisibleChest)==0
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS))==0
                    && ModEnchantmentInit.PEI.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.FEET))==0
                    && ISteamWeapon.getSteam(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD))>=5
                    && ISteamWeapon.getSteam(invisibleChest)>=5
                    && ISteamWeapon.getSteam(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS))>=5
                    && ISteamWeapon.getSteam(player.getItemStackFromSlot(EntityEquipmentSlot.FEET))>=5){
                player.motionX=2*player.getLookVec().x;
                player.motionY+=1.5;
                player.motionZ=2*player.getLookVec().z;
                player.getCooldownTracker().setCooldown(invisibleChest.getItem(), 400);
                PacketHandler.INSTANCE.sendToServer(new MessageAASSkill(2));
            }
        }
    }
}
