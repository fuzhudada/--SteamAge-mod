package com.fuzhudada.error.assassin;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class assaultManager {
    public static HashMap<UUID,assault> map;

    public assaultManager(){
        map=new HashMap<>();
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    public static boolean isPlayerAssaulting(EntityPlayer player){
        map.entrySet().removeIf(e -> !e.getValue().statue);
        return map.containsKey(player.getUniqueID());
    }

    public static void addPlayer(EntityPlayerMP player){
        if(!isPlayerAssaulting(player)){
            assault ass=new assault(player);
            map.put(player.getUniqueID(),ass);
        }
    }

    @SubscribeEvent
    public void E(LivingEvent.LivingUpdateEvent event) {
        if(!event.getEntity().world.isRemote){
            for (Map.Entry<UUID, assault> entry : map.entrySet()) {
                if(entry.getValue().player.getUniqueID().equals(event.getEntity().getUniqueID())){
                    if (entry.getValue().statue) {
                        entry.getValue().onUpdate();
                    }else{
                        map.remove(entry.getKey());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void E(PlayerInteractEvent.RightClickItem event) {
        if(!event.getWorld().isRemote){
            EntityPlayerMP player=(EntityPlayerMP) event.getEntityPlayer();
            if(player != null && ModEnchantmentInit.HasCiTiao(player,ModEnchantmentInit.QIN_JIA_CI_KE)
                    && ModEnchantmentInit.QIN_JIA.getEnchantmentLevel(event.getItemStack())>0
                    && !player.isPotionActive(com.somebody.idlframewok.potion.ModPotions.XIAN_XIN)){
                if(!map.containsKey(player.getUniqueID())){
                    addPlayer(player);
                }
                map.get(event.getEntityPlayer().getUniqueID()).findTarget();
            }
        }
    }
}
