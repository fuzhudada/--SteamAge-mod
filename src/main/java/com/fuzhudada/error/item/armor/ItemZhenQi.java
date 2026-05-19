package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ItemZhenQi extends com.somebody.idlframewok.item.ItemArmorBase implements ISteamWeapon {
    public ItemZhenQi(String name, ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(LivingEvent.LivingUpdateEvent event) {
        World world = event.getEntity().world;
        if(event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase P = (EntityLivingBase) event.getEntity();
            int i = 0;
            if (P != null) {
                ItemStack head = P.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                ItemStack chest = P.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack leg = P.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack foot = P.getItemStackFromSlot(EntityEquipmentSlot.FEET);
                if (head.getItem() == ModItems.ZHEN_QI_TOU && head.getItemDamage() < 25400) {
                    i = i + 1;
                }
                if (chest.getItem() == ModItems.ZHEN_QI_JIA && chest.getItemDamage() < 25400) {
                    i = i + 1;
                }
                if (leg.getItem() == ModItems.ZHEN_QI_TUI && leg.getItemDamage() < 25400) {
                    i = i + 1;
                }
                if ((foot.getItem() == ModItems.ZHEN_QI_XIE || foot.getItem() == ModItems.PEN_QI_XIE) && foot.getItemDamage() < 25400) {
                    i = i + 1;
                }
                if (i == 4 && P.isSprinting() && !P.isPotionActive(MobEffects.POISON)) {
                    P.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 1));
                    for (int a = 0; a < 6; a++) {
                        P.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, P.posX + Math.random() - 0.5, P.posY + 1, P.posZ + Math.random() - 0.5, 0, 0.2, 0);
                    }
                    if (!world.isRemote) {
                        Vec3d D = P.getLookVec();
                        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                        for (int b = 0; b <= G.size() - 1; b++) {
                            if (ShouldDamage(G.get(b), P, D) && G.get(b) != P && !G.get(b).isPotionActive(MobEffects.FIRE_RESISTANCE) && !(G.get(b) instanceof EntityArmorStand)) {
                                G.get(b).setFire(5);
                                G.get(b).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));
                            }
                        }
                    }
                    ISteamWeapon.shrinkSteam(head,0.00189F);
                    ISteamWeapon.shrinkSteam(chest,0.0013F);
                    ISteamWeapon.shrinkSteam(leg,0.00138F);
                    ISteamWeapon.shrinkSteam(foot,0.0016F);
                }
            }
        }
    }

    protected boolean ShouldDamage(EntityLivingBase Entity, EntityLivingBase player, Vec3d Vec3d){
        double x1 = player.posX;
        double y1 = player.posY;
        double z1 = player.posZ;
        double x2 = x1 - 4.2* Vec3d.x;
        double z2 = z1 - 4.2* Vec3d.z;
        double x3 = Entity.posX;
        double y3 = Entity.posY;
        double z3 = Entity.posZ;
        double d1 = Math.abs((z1-z2)*x3+(x2-x1)*z3+(x1-x2)*z1-(z1-z2)*x1)/Math.sqrt((z1-z2)*(z1-z2)+(x1-x2)*(x1-x2));
        double x4 = (x1+x2)/2;
        double z4 = (z1+z2)/2;
        double d2 = Math.abs((x1-x2)*x3+(z1-z2)*z3+(x2-x1)*x4+(z2-z1)*z4)/Math.sqrt((x2-x1)*(x2-x1)+(z2-z1)*(z2-z1));
        double d3 = Math.abs(y1-y3);
        return (d1 <1 && d2 <2 && d3 <1);
    }

    @Override
    public float maxItemSteam() {
        return 50;
    }
}
