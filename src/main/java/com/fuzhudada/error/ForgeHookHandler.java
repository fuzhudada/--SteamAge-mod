package com.fuzhudada.error;

import com.fuzhudada.error.assassin.assaultManager;
import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.model.*;
import com.fuzhudada.error.entity.projectiles.*;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.shield.ItemShieldBase;
import com.fuzhudada.error.item.shield.NPCmodShield;
import com.fuzhudada.error.item.sword.*;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

public class ForgeHookHandler{
    public ForgeHookHandler(){
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(LivingEvent.LivingUpdateEvent event) {
        World world = event.getEntity().world;
        if (!world.isRemote && event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase P = (EntityLivingBase) event.getEntity();
            //急性中毒
            if (P.isPotionActive(ModPotions.JI_XING_ZHONG_DU)) {
                if(Objects.requireNonNull(P.getActivePotionEffect(ModPotions.JI_XING_ZHONG_DU)).getDuration() % 10 == 0){
                    P.attackEntityFrom(DamageSource.MAGIC, (float) java.lang.Math.pow(Objects.requireNonNull(P.getActivePotionEffect(ModPotions.JI_XING_ZHONG_DU)).getAmplifier() + 1, 2));
                    P.hurtResistantTime = 0;
                }
            }

            //潮涌之护
            if (P.isBurning() && P.isPotionActive(ModPotions.CHAO_YONG_ZHI_HU)) {
                if(P.getHealth()<P.getMaxHealth()) P.heal((float) java.lang.Math.pow(Objects.requireNonNull(P.getActivePotionEffect(ModPotions.CHAO_YONG_ZHI_HU)).getAmplifier(), 2.5));
                P.extinguish();
                P.removePotionEffect(ModPotions.CHAO_YONG_ZHI_HU);
            }

            //剧毒空气
            if (P.isPotionActive(ModPotions.JU_DU_KONG_QI) && !P.isPotionActive(ModPotions.CEN_DIAN)) {
                P.attackEntityFrom(DamageSource.MAGIC, (float) java.lang.Math.pow(Objects.requireNonNull(P.getActivePotionEffect(ModPotions.JU_DU_KONG_QI)).getAmplifier() + 1, 2));
            }

            //燃烧空气
            if (P.isPotionActive(ModPotions.RAN_SHAO_KONG_QI)) {
                P.setFire(2);
            }

            //游侠轻甲加速度和负抗性
            if (ModEnchantmentInit.HasCiTiao(P,ModEnchantmentInit.QIN_JIA)) {
                P.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, Math.max(Math.max(ModEnchantmentInit.QIN_JIA.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.FEET)), ModEnchantmentInit.QIN_JIA.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.LEGS))), Math.max(ModEnchantmentInit.QIN_JIA.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.CHEST)), ModEnchantmentInit.QIN_JIA.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.HEAD)))) - 1));
                P.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, -2));
                P.addPotionEffect(new PotionEffect(ModPotions.JIN_SHI, 60, 4));
            }

            //刺客轻甲加速度和负抗性
            if (ModEnchantmentInit.HasCiTiao(P,ModEnchantmentInit.QIN_JIA_CI_KE)) {
                P.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, Math.max(Math.max(ModEnchantmentInit.QIN_JIA_CI_KE.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.FEET)), ModEnchantmentInit.QIN_JIA_CI_KE.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.LEGS))), Math.max(ModEnchantmentInit.QIN_JIA_CI_KE.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.CHEST)), ModEnchantmentInit.QIN_JIA_CI_KE.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.HEAD)))) - 1));
                P.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, -2));
                if(!P.isPotionActive(ModPotions.XIAN_XIN)){
                    P.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 60, 0));
                }
            }

            //中甲加抗性提升
            if (ModEnchantmentInit.HasCiTiao(P,ModEnchantmentInit.MIDRESIS)) {
                P.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 0));
            }

            //重甲加抗性提升和缓慢
            if (ModEnchantmentInit.HasCiTiao(P,ModEnchantmentInit.HEAVYRESIS)) {
                int Amp = Math.max(Math.max(ModEnchantmentInit.HEAVYRESIS.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.FEET)), ModEnchantmentInit.HEAVYRESIS.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.LEGS))), Math.max(ModEnchantmentInit.HEAVYRESIS.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.CHEST)), ModEnchantmentInit.HEAVYRESIS.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.HEAD)))) - 1;
                if (Amp < 3) {
                    P.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, Amp+1));
                    P.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, Amp));
                } else {
                    P.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 3));
                    P.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 3));
                }
            }

            //沉淀移除急性中毒和中毒
            if (P.isPotionActive(ModPotions.CEN_DIAN)) {
                P.removePotionEffect(MobEffects.POISON);
                P.removePotionEffect(ModPotions.JI_XING_ZHONG_DU);
            }

            //古代加沉淀
            if (ModEnchantmentInit.HasCiTiao(P,ModEnchantmentInit.GU_DAI)) {
                P.addPotionEffect(new PotionEffect(ModPotions.CEN_DIAN, 60, 0));
            }

            //剑刃风暴
            if(P.isPotionActive(ModPotions.JIAN_REN_FENG_BAO)){
                if (!P.getIsInvulnerable()) {
                    P.setEntityInvulnerable(true);
                }
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (entityLivingBase.getDistance(P) < 2 && entityLivingBase != P && !(entityLivingBase instanceof EntityArmorStand)) {
                        entityLivingBase.attackEntityFrom(DamageSource.causeMobDamage(P), (Objects.requireNonNull(P.getActivePotionEffect(ModPotions.JIAN_REN_FENG_BAO)).getAmplifier() + 1) * 230);
                        if((P.posX - entityLivingBase.posX)*(P.posX - entityLivingBase.posX)+(P.posZ - entityLivingBase.posZ)*(P.posZ - entityLivingBase.posZ) != 0) {
                            entityLivingBase.knockBack(P, 2, P.posX - entityLivingBase.posX, P.posZ - entityLivingBase.posZ);
                        }
                    }
                }
            }else {
                if (P.getIsInvulnerable()) {
                    P.setEntityInvulnerable(false);
                }
            }

            //驱散香给野兽缓慢
            if(P.isPotionActive(ModPotions.QU_SAN_XIANG) && P.ticksExisted % 80 == 0){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (entityLivingBase.getDistance(P) < 16
                            && entityLivingBase != P
                            && !(entityLivingBase instanceof EntityArmorStand)
                            && entityLivingBase.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
                        entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,90,1));
                    }
                }
            }

            //冥想香给人类虚弱
            if(P.isPotionActive(ModPotions.MIN_XIANG_XIANG) && P.ticksExisted % 80==0){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (entityLivingBase.getDistance(P) < 16 && !(entityLivingBase instanceof EntityArmorStand)
                            && isHuman(entityLivingBase)) {
                        entityLivingBase.addPotionEffect(new PotionEffect(ModPotions.XU_RUO,90,2));
                    }
                }
            }

            //朽败香给周围生物负抗性
            if(P.isPotionActive(ModPotions.XIU_BAI_XIANG) && P.ticksExisted % 80==0){
                P.addPotionEffect(new PotionEffect(ModPotions.ZAI_SHENG_TING_ZHI, 90,0));
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (entityLivingBase.getDistance(P) < 16 && !(entityLivingBase instanceof EntityArmorStand)) {
                        entityLivingBase.addPotionEffect(new PotionEffect(ModPotions.PO_ZHAN, 90, 0));
                    }
                }
            }

            //洁盐香伤害亡灵
            if(P.isPotionActive(ModPotions.JIE_YAN_XIANG) && P.ticksExisted % 80==0){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (entityLivingBase.getDistance(P) < 16 && !(entityLivingBase instanceof EntityArmorStand)) {
                        if(entityLivingBase.isEntityUndead()){
                            if(!entityLivingBase.isPotionActive(ModPotions.JI_XING_ZHONG_DU)) {
                                entityLivingBase.addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU, 79, 2));
                            }
                        }else if(P.getHealth()<P.getMaxHealth()){
                            entityLivingBase.heal(0.8F);
                        }
                    }
                }
            }

            //副手有东西时拿蒸汽锤和蒸汽拳套
            if(P instanceof EntityPlayer
                    && (P.getHeldItemMainhand().getItem() == ModItems.ZHENG_QI_CHUI || P.getHeldItemMainhand().getItem() == ModItems.ZHEN_TONG || P.getHeldItemMainhand().getItem() == ModItems.ROCKET_GLOVES)
                    && P.getHeldItemOffhand().getItem() != Items.AIR){
                P.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10));
                P.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,20,300));
            }

            //束缚给缓慢
            if(P.isPotionActive(ModPotions.SU_FU)){
                P.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20, Objects.requireNonNull(P.getActivePotionEffect(ModPotions.SU_FU)).getAmplifier()));
            }

            //活力回血
            if(P.getHealth()<P.getMaxHealth() && ModEnchantmentInit.HasCiTiao(P,ModEnchantmentInit.HUO_LI)){
                P.heal(0.02f);
            }

            //飞行结束
            if(P.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA){
                ItemStack itemStack=P.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if(itemStack.hasTagCompound()
                        && itemStack.getTagCompound().hasKey("bird")
                        && (!P.isPotionActive(ModPotions.FEI_XING) || (Objects.requireNonNull(P.getActivePotionEffect(ModPotions.FEI_XING)).getDuration()<200 && !P.isElytraFlying()))){
                    ItemStack chest=new ItemStack(ModItems.BIRD_CHEST);
                    NBTTagCompound nbt=itemStack.getTagCompound();
                    nbt.removeTag("bird");
                    chest.setTagCompound(nbt);

                    Map<Enchantment,Integer> map= EnchantmentHelper.getEnchantments(chest);
                    map.remove(Enchantments.BINDING_CURSE);
                    EnchantmentHelper.setEnchantments(map,chest);

                    P.setItemStackToSlot(EntityEquipmentSlot.CHEST,chest);
                    P.removePotionEffect(ModPotions.FEI_XING);
                }
            }
        }
    }

    @SubscribeEvent
    public void E(LivingHurtEvent event) {
        World world = event.getEntity().world;
        Entity B = event.getSource().getTrueSource();
        EntityLivingBase H = (EntityLivingBase) event.getEntity();

        //重甲免伤
        if (ModEnchantmentInit.HasCiTiao(H,ModEnchantmentInit.HEAVYRESIS) && event.getAmount()>=H.getMaxHealth()/2 && event.getAmount()<=H.getMaxHealth()) {
            event.setAmount(event.getAmount()/2);
        }

        if (!(B instanceof EntityFireball) && B != null && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase A = (EntityLivingBase) event.getSource().getTrueSource();
            assert A != null;
            //防火免疫蒸汽伤害
            if(event.getSource().getDamageType().equals("steam") && H.isPotionActive(MobEffects.FIRE_RESISTANCE)){
                event.setCanceled(true);
            }

            //慷慨激昂加攻击力，肢体破坏减攻击力
            if (A.isPotionActive(ModPotions.KANG_KAI_JI_ANG) || A.isPotionActive(ModPotions.ZHI_TI_PO_HUAI)) {
                int Amp;
                if (A.isPotionActive(ModPotions.KANG_KAI_JI_ANG)) {
                    Amp = Objects.requireNonNull(A.getActivePotionEffect(ModPotions.KANG_KAI_JI_ANG)).getAmplifier();
                } else {
                    Amp = -Objects.requireNonNull(A.getActivePotionEffect(ModPotions.ZHI_TI_PO_HUAI)).getAmplifier();
                }
                event.setAmount((float) (event.getAmount() * (1 + 0.2 * Amp)));
            }

            //慷慨激昂和肢体破坏加受到的伤害
            if (H.isPotionActive(ModPotions.KANG_KAI_JI_ANG) || H.isPotionActive(ModPotions.ZHI_TI_PO_HUAI)) {
                int Amp;
                if (H.isPotionActive(ModPotions.KANG_KAI_JI_ANG)) {
                    Amp = Objects.requireNonNull(H.getActivePotionEffect(ModPotions.KANG_KAI_JI_ANG)).getAmplifier();
                } else {
                    Amp = Objects.requireNonNull(H.getActivePotionEffect(ModPotions.ZHI_TI_PO_HUAI)).getAmplifier();
                }
                event.setAmount((float) (event.getAmount() * (1 + 0.2 * Amp)));
            }

            //剧毒空气减攻击力
            if (A.isPotionActive(ModPotions.JU_DU_KONG_QI)) {
                event.setAmount((float) (event.getAmount() * 0.7));
            }

            //金属侵蚀
            if (H.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.BIAO_JI) {
                int level = ModEnchantmentInit.JIN_SHU_QIN_SHI.getEnchantmentLevel(A.getHeldItemMainhand());
                event.setAmount(event.getAmount()*(1+0.1F*level));
            }

            //渔叉
            if (H.isInWater() | A.isInWater()) {
                int level = ModEnchantmentInit.YU_CHA.getEnchantmentLevel(A.getHeldItemMainhand());
                if (A.isInWater() & H.isInWater()) {
                    event.setAmount(event.getAmount() + 6 * level);
                } else {
                    event.setAmount(event.getAmount() + 2.5f * level);
                }
            }

            //争锋相对
            if (ModEnchantmentInit.ZHEN_FENG_XIANG_DUI.getEnchantmentLevel(H.getHeldItemMainhand()) > 0) {
                int level = ModEnchantmentInit.ZHEN_FENG_XIANG_DUI.getEnchantmentLevel(H.getHeldItemMainhand());
                if (event.getAmount() - level * 5 > 0) {
                    event.setAmount(event.getAmount() - level * 5);
                } else {
                    event.setAmount(0);
                }
            }

            //反射
            int i = ModEnchantmentInit.GetCountOfCiTiaoArmor(H,ModEnchantmentInit.FAN_SHE);
            if (H.isInWater()) {
                if (i > 0 && ModEnchantmentInit.FAN_SHE.getEnchantmentLevel(A.getHeldItemMainhand()) > 0) {
                    Fan_She(A, H, event.getAmount());
                }
            } else {
                if (A.getRNG().nextFloat() < 0.25 * i && ModEnchantmentInit.FAN_SHE.getEnchantmentLevel(A.getHeldItemMainhand()) > 0) {
                    Fan_She(A, H, event.getAmount());
                }
            }

            //嗜血
            if (ModEnchantmentInit.SHI_XUE.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0 && A.getHealth()<A.getMaxHealth()) {
                int l=ModEnchantmentInit.SHI_XUE.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
                A.heal((float) Math.max(1.01F,0.02*l*event.getAmount()));
            }

            //致残打击
            if (ModEnchantmentInit.ZHI_CAN_DA_JI.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0) {
                H.addPotionEffect(new PotionEffect(ModPotions.ZHI_TI_PO_HUAI, 80, ModEnchantmentInit.ZHI_CAN_DA_JI.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND))));
            }

            //沉重
            if (ModEnchantmentInit.CEN_ZHONG.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0) {
                int level=ModEnchantmentInit.CEN_ZHONG.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
                if(Math.random()<level*0.1) {
                    H.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, level-1));
                }
            }

            //中甲加近战伤害
            if (ModEnchantmentInit.HasCiTiao(A,ModEnchantmentInit.MIDRESIS) && event.getSource().getDamageType().equals("player")) {
                event.setAmount(event.getAmount() * 1.1F);
            }

            //破绽BUFF
            if (H.isPotionActive(ModPotions.PO_ZHAN)) {
                int Amp = Objects.requireNonNull(H.getActivePotionEffect(ModPotions.PO_ZHAN)).getAmplifier();
                event.setAmount(event.getAmount() * (2 + Amp));
            }

            //劲矢加弓箭伤害
            if (A.isPotionActive(ModPotions.JIN_SHI) && (event.getSource().getDamageType().equals("arrow")
                    || event.getSource().getImmediateSource() instanceof EntityNuMu
                    || (event.getSource().getImmediateSource() instanceof EntityArrowPig && ((EntityArrowPig)event.getSource().getImmediateSource()).getState()==0))) {
                event.setAmount(event.getAmount() * (0.2F*(Objects.requireNonNull(A.getActivePotionEffect(ModPotions.JIN_SHI))).getAmplifier() +1.2F));
            }

            //古代吸血
            if (ModEnchantmentInit.GU_DAI.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0) {
                if (A.isPotionActive(ModPotions.CEN_DIAN) && A.getRNG().nextFloat() < 0.08) {
                    A.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, ModEnchantmentInit.GU_DAI.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) - 1));
                }
                if (!A.isPotionActive(ModPotions.CEN_DIAN)) {
                    if (A.getRNG().nextFloat() < 0.08) {
                        A.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 2));
                    }
                    if (A.getRNG().nextFloat() < 0.02) {
                        A.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, ModEnchantmentInit.GU_DAI.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) - 1));
                    }
                }
            }

            //力大势沉破盾
            ItemStack shield = H.isHandActive() ? H.getActiveItemStack() : ItemStack.EMPTY;
            if (!shield.isEmpty() && ModEnchantmentInit.LI_DA_SHI_CHEN.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0
                    && IsShieldII(shield, H) && H instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) H;
                player.getCooldownTracker().setCooldown(shield.getItem(), ModEnchantmentInit.LI_DA_SHI_CHEN.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) * 10);
                world.setEntityState(player, (byte) 30);
                H.resetActiveHand();
            }

            //坚守阵线反破盾
            if (!shield.isEmpty() && ModEnchantmentInit.JIAN_SHOU_ZHEN_XIAN.getEnchantmentLevel(shield) * 0.2 > A.getRNG().nextFloat() && IsShieldII(shield, H) && H instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) H;
                player.getCooldownTracker().removeCooldown(shield.getItem());
            }

            //兴奋加攻击力
            if (A.isPotionActive(ModPotions.XIN_FEN)) {
                event.setAmount(event.getAmount() + (float) Math.pow(Objects.requireNonNull(A.getActivePotionEffect(ModPotions.XIN_FEN)).getAmplifier(), 2));
            }

            //骑士刀反弹弹射物
            if(shield.getItem() instanceof ItemQiShiDao && H instanceof EntityPlayer) {
                EntityPlayer player=(EntityPlayer) H;

                if (!shield.isEmpty()
                        && event.getSource().isProjectile()
                        && !Objects.equals(event.getSource().getImmediateSource() , event.getSource().getTrueSource())
                        && !H.isPotionActive(MobEffects.POISON)) {
                    int Amp;
                    if (H.isPotionActive(ModPotions.WU_ZHI)) {
                        Amp = Objects.requireNonNull(H.getActivePotionEffect(ModPotions.WU_ZHI)).getAmplifier();
                    } else {
                        Amp = -1;
                    }

                    if(Amp==-1 || Objects.requireNonNull(H.getActivePotionEffect(ModPotions.WU_ZHI)).getDuration()<156){
                        H.addPotionEffect(new PotionEffect(ModPotions.WU_ZHI, 160, Amp + 1));
                        if (Amp > 0) {
                            event.setAmount(event.getAmount());
                            player.getCooldownTracker().setCooldown(shield.getItem(), 600);
                            H.resetActiveHand();
                        } else {
                            Vec3d F=new Vec3d(A.posX-H.posX,A.posY+A.getEyeHeight()-H.posY-H.getEyeHeight(),A.posZ-H.posZ).normalize();
                            //EntityGeneralProjectiles.shootInSpecificDirection(world,H, ModDamageSource.EnumModDamageSource.ManaDamage,event.getAmount(),shield.getItem()==ModItems.QI_SHI_DAO?"flame":"endRod",(float)F.x,(float)F.y,(float)F.z);
                            EntityQiShiDao g=new EntityQiShiDao(world);
                            g.motionX=F.x;
                            g.motionY=F.y;
                            g.motionZ=F.z;
                            g.DAMAGE=event.getAmount();
                            g.ATTACKER=H;
                            if(shield.getItem()==ModItems.TIAN_SHI_DAO){
                                g.setType(1);
                            }
                            g.setNoGravity(true);
                            g.setPosition(H.posX+1.5*F.x,H.posY+H.getEyeHeight()+1.5*F.y,H.posZ+1.5*F.z);
                            if(!world.isRemote){
                                world.spawnEntity(g);
                            }
                            world.playSound(null, H.posX, H.posY, H.posZ,ModSoundHandler.SWORD_BLOCK, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);

                            if(shield.getItem()==ModItems.TIAN_SHI_DAO && IWand.mana1(750,100,player)){
                                EntityHuoLong huoLong=new EntityHuoLong(world);
                                huoLong.setPositionAndRotation(player.posX,player.posY,player.posZ,player.rotationYaw,player.rotationPitch);
                                huoLong.setType(10);
                                if(!world.isRemote){
                                    world.spawnEntity(huoLong);
                                }
                                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                                        && e != player && !(e instanceof EntityArmorStand) && ItemTuCi.ShouldDamage(e,player,20,1,1));
                                for (EntityLivingBase entityLivingBase : G) {
                                    entityLivingBase.attackEntityFrom(ModDamageSource.causeManaDamage(player,player),310);
                                    entityLivingBase.hurtResistantTime=0;
                                }
                            }
                        }
                    }
                    if(Amp<=0) event.setAmount(0);
                }
            }

            //镰刀范围伤害
            if(A.getHeldItemMainhand().getItem() instanceof ItemJiaoTan && event.getSource().getDamageType().equals("player")){
                if(A.getHeldItemMainhand().getItem() == ModItems.JIAO_TAN_LIAN_DAO) {
                    H.setFire(3);
                }
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (entityLivingBase.getDistance(H) < 2.5 && entityLivingBase != A && entityLivingBase != H && !(entityLivingBase instanceof EntityArmorStand)) {
                        if(A.getHeldItemMainhand().getItem() == ModItems.JIAO_TAN_LIAN_DAO) {
                            entityLivingBase.setFire(3);
                        }
                        entityLivingBase.attackEntityFrom(DamageSource.causeMobDamage(A),event.getAmount());
                    }
                }
            }

            //盔甲架保护
            if(H instanceof EntityArmorStand && !(event.getSource().getDamageType().equals("player"))){
                event.setCanceled(true);
            }

            //虚弱
            if (A.isPotionActive(ModPotions.XU_RUO)) {
                int level = Objects.requireNonNull(A.getActivePotionEffect(ModPotions.XU_RUO)).getAmplifier()+1;
                if(event.getAmount()-4*level < 0){
                    event.setAmount(0);
                }else{
                    event.setAmount(event.getAmount()-4*level);
                }
            }

            //毒液附加
            if(ModEnchantmentInit.DU_YE_FU_JIA.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0){
                if(Math.random()<ModEnchantmentInit.DU_YE_FU_JIA.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND))*0.1){
                    H.addPotionEffect(new PotionEffect(MobEffects.POISON,100,1));
                }
            }

            //神经毒素
            if(ModEnchantmentInit.SHEN_JING_DU_SU.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0){
                H.addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU,99,ModEnchantmentInit.SHEN_JING_DU_SU.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND))-1));
            }

            //蒸汽锤子技能
            if(A.getHeldItemMainhand().getItem() == ModItems.ZHENG_QI_CHUI
                    && event.getSource().getDamageType().equals("player")
                    && A.getHeldItemMainhand().isItemEnchanted()){
                event.setAmount(event.getAmount()*3);
            }

            //炸弹无敌
            if(H instanceof EntityBomb || H instanceof EntityZhongDanPao || H instanceof EntityAntiTankLandmine){
                event.setAmount(0);
            }

            //古树盾技能
            if(shield.getItem() == ModItems.TREE_SHIELD
                    && H.isPotionActive(ModPotions.CEN_DIAN)
                    && (!shield.hasTagCompound() || Math.abs(H.ticksExisted-shield.getTagCompound().getInteger("time"))>20)){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                        && e.getDistanceSq(H)<256
                        && !e.equals(H)
                        && !(e instanceof EntityPlayer));
                if(H.getHealth()<H.getMaxHealth()) H.heal(G.size()*5);
                Vec3d F;
                for (EntityLivingBase entityLivingBase : G) {
                    F=new Vec3d(entityLivingBase.posX-H.posX,entityLivingBase.posY+entityLivingBase.getEyeHeight()-H.posY-H.getEyeHeight(),entityLivingBase.posZ-H.posZ).normalize();
                    EntityGeneralProjectiles.shootInSpecificDirection(world,H, ModDamageSource.EnumModDamageSource.ManaDamage,26,600,"happyVillager",true,(float)F.x,(float)F.y,(float)F.z,true,true, null);
                }

                if(!H.world.isRemote){
                    if(!shield.hasTagCompound()){
                        shield.setTagCompound(new NBTTagCompound());
                    }

                    NBTTagCompound nbt = shield.getTagCompound();
                    if(nbt == null) {
                        nbt=new NBTTagCompound();
                    }
                    nbt.setTag("time", new NBTTagInt(H.ticksExisted));
                    shield.setTagCompound(nbt);
                }
            }

            //荆棘
            int k=ModEnchantmentInit.GetCiTiaoLevel(H,ModEnchantmentInit.JING_JI_ZHI_DUN);
            if(k > 0 && (event.getSource().getDamageType().equals("mob") || event.getSource().getDamageType().equals("player"))){
                A.attackEntityFrom(DamageSource.causeThornsDamage(H),Math.max(2.5f*k,event.getAmount()*0.05f*k));
            }

            //火焰附加/火矢爆炸
            if (H.getHealth()<event.getAmount() && !event.getSource().getDamageType().equals("mob") && ((event.getSource().getDamageType().equals("arrow") && EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, (A).getHeldItemMainhand())>0)
                    || (EnchantmentHelper.getFireAspectModifier(A)>0))) {
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (EntityLivingBase entityLivingBase : G) {
                    if (Math.sqrt((entityLivingBase.posX - H.posX) * (entityLivingBase.posX - H.posX) + (entityLivingBase.posY - H.posY) * (entityLivingBase.posY - H.posY) + (entityLivingBase.posZ - H.posZ) * (entityLivingBase.posZ - H.posZ)) < 3 && entityLivingBase != event.getSource().getTrueSource() && entityLivingBase != H && !(entityLivingBase instanceof EntityArmorStand)) {
                        entityLivingBase.attackEntityFrom(DamageSource.causeMobDamage(A),event.getAmount());
                        entityLivingBase.setFire(5);
                    }
                }
                EntityHuoLong.spawnParticle(H.posX,H.posY,H.posZ,3,world);
            }

            //动量转移附魔
            if((ModEnchantmentInit.CHONG_JI.getEnchantmentLevel(A.getHeldItemMainhand())>0 || (A.getHeldItemMainhand().getItem() instanceof ItemSpear && EnchantmentHelper.getEnchantments(A.getHeldItemMainhand()).containsKey(Enchantments.POWER)))
                    && event.getSource().getDamageType().equals("player")){
                int l=ModEnchantmentInit.CHONG_JI.getEnchantmentLevel(A.getHeldItemMainhand());
                if(l==0){
                    l=2;
                }

                float f= (float) A.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();

                if(A.isRiding() && A.getRidingEntity() instanceof EntityLivingBase){
                    EntityLivingBase riding=(EntityLivingBase) A.getRidingEntity();

                    if(riding.lastTickPosX != riding.posX || riding.lastTickPosZ != riding.posZ){
                        f += riding.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                    }
                }

                float f1=Math.max(1,Math.min(l*f*10+1-l,3));

                if (A.fallDistance > 0.0F && !A.onGround && !A.isOnLadder() && !A.isInWater() && !A.isPotionActive(MobEffects.BLINDNESS) && !A.isRiding()) {
                    f1 *= 1.5;
                }

                if(A.isElytraFlying()){
                    f1 *= 3;
                }

                event.setAmount(event.getAmount()*f1);
            }

            //冲击锤
            if(A.getHeldItemMainhand().getItem() == ModItems.CHONG_JI_CHUI
                    && event.getSource().getDamageType().equals("player")){
                if(A.isSprinting() && ISteamWeapon.shrinkSteam(A.getHeldItemMainhand(),15)){
                    H.setFire(5);
                    EntityHuoLong.spawnParticle(H.posX,H.posY,H.posZ,3,world);
                    List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                    for (EntityLivingBase e : G) {
                        if (e.getDistanceSq(H) < 3*3 && e != A && e != H && !(e instanceof EntityArmorStand)) {
                            e.attackEntityFrom(DamageSource.causeMobDamage(A),event.getAmount());
                            e.setFire(5);
                            e.knockBack(A, 2, A.posX - e.posX, A.posZ - e.posZ);
                        }
                    }
                }
            }

            //隐身消失
            if(A instanceof EntityPlayer
                    && ModEnchantmentInit.HasCiTiao(A,ModEnchantmentInit.QIN_JIA_CI_KE)
                    && !assaultManager.isPlayerAssaulting((EntityPlayer) A)){
                A.removePotionEffect(MobEffects.INVISIBILITY);
                A.addPotionEffect(new PotionEffect(ModPotions.XIAN_XIN , 100 , 0));
            }

            //SA原始型
            if(event.getSource().isProjectile()
                    && (ModEnchantmentInit.isArmorAllEquiped(ModItems.SA_HEAD,ModItems.SA_CHEST,ModItems.SA_LEG,ModItems.SA_BOOT,H) || ModEnchantmentInit.isArmorAllEquiped(ModItems.SA_HEAD,ModItems.AAS,ModItems.SA_LEG,ModItems.SA_BOOT,H))
                    && H.getDistance(A) > 8
                    && H.getDistance(A) < 32){
                ItemStack Chest=H.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack Legs=H.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                ItemStack Rpg=ItemStack.EMPTY;
                boolean flag1=false;
                if(H instanceof EntityPlayer){
                    EntityPlayer player=(EntityPlayer) H;
                    for (int j = 9; j < 40; ++j) {
                        ItemStack itemstack = player.inventory.getStackInSlot(j);
                        if(itemstack.getItem() == ModItems.ZHONG_PAO_DAN){
                            Rpg=itemstack;
                            flag1=true;
                            break;
                        }
                    }

                }

                if(!(H instanceof EntityPlayer) ||
                        (((EntityPlayer)H).getCooldownTracker().getCooldown(Legs.getItem(),0)<=0
                                && flag1
                                && ISteamWeapon.shrinkSteam(Chest,1.5625F))){
                    Vec3d F=H.getLookVec();

                    EntityZhongDanPao bomb=new EntityZhongDanPao(A.getEntityWorld(),H,A,320+6*ModEnchantmentInit.PAO_HUO.getEnchantmentLevel(Chest));
                    bomb.setPosition(H.posX-0.5*F.x,H.posY+0.7*H.getEyeHeight(),H.posZ-0.5*F.z);
                    bomb.motionX = 0;
                    bomb.motionY = 2;
                    bomb.motionZ = 0;
                    bomb.setNoGravity(true);
                    world.spawnEntity(bomb);

                    EntityHuoLong.spawnParticle(A.posX, A.posY, A.posZ, 4, world);
                    world.playSound(null, H.posX, H.posY, H.posZ, ModSoundHandler.GUN3, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);

                    if(H instanceof EntityPlayer){
                        EntityPlayer player=(EntityPlayer) H;
                        player.getCooldownTracker().setCooldown(Legs.getItem(), 300);
                        int level1=ModEnchantmentInit.BU_JI.getEnchantmentLevel(Chest);
                        if(player.getRNG().nextFloat()>level1*0.1){
                            Rpg.shrink(1);
                        }
                    }
                }
            }

            //蛮荒大弓特效
            if(event.getSource().getDamageType().equals("arrow") && event.getSource().getImmediateSource() != null && event.getSource().getImmediateSource().getTags().contains("bone")){
                EntityHuoLong.spawnParticle(H.posX,H.posY+(H.getEyeHeight()/2),H.posZ,7,world);

                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                        && e.getDistance(H)<3
                        && e != A && e != H
                        && !(e instanceof EntityArmorStand));
                for (EntityLivingBase entityLivingBase : G) {
                    entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,1));
                    if(!entityLivingBase.isPotionActive(ModPotions.JI_XING_ZHONG_DU)) {
                        entityLivingBase.addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU, 49, 3));
                    }
                }
            }

            //怒目古代黄金箭爆炸
            if(event.getSource().getImmediateSource() != null
                    && event.getSource().getImmediateSource().getTags().contains("numu")
                    && event.getSource().getImmediateSource() instanceof EntityHuangJinJian){
                EntityHuangJinJian huangJinJian= (EntityHuangJinJian) event.getSource().getImmediateSource();
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (int j = 0; j <= G.size() - 1; j++) {
                    if (huangJinJian.getDistanceSq(G.get(j)) < 9
                            && !huangJinJian.set.contains(G.get(j))
                            && G.get(j) != A
                            && !(G.get(j) instanceof EntityArmorStand)) {
                        huangJinJian.set.add(G.get(j));
                        G.get(j).attackEntityFrom(DamageSource.causeThrownDamage(huangJinJian,A),event.getAmount());
                    }
                }
                EntityHuoLong.spawnParticle(H.posX,H.posY+1,H.posZ,5,world);
            }

            //蒸汽拳套技能
            if((A.getHeldItemMainhand().getItem() == ModItems.ZHEN_TONG || A.getHeldItemMainhand().getItem() == ModItems.ROCKET_GLOVES)
                    && event.getSource().getDamageType().equals("player")
                    && A.getHeldItemMainhand().isItemEnchanted()){
                int level=EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, A.getHeldItemMainhand());
                if(level>0){
                    event.setAmount(event.getAmount()*(level+1));
                }
            }

            //电场
            k=ModEnchantmentInit.GetCiTiaoLevel(H,ModEnchantmentInit.DIAN_CHANG);
            if(k > 0 && !event.getSource().getDamageType().equals("electrical") && H.getDistanceSq(A)<256){
                List<EntityLivingBase> G = world.getEntities(EntityLightningCowHorn.class, e -> e.isEntityAlive()
                        && e.getDistanceSq(H)<256);

                if(G.size()>0){
                    G.get(0).attackEntityFrom(ModDamageSource.causeElectricalDamage(H),Math.max(2.5f*k,event.getAmount()*0.05f*k));
                    List<EntityPlayerMP> P = H.world.getEntities(EntityPlayerMP.class, e->e.getDistance(H)<64);
                    for (EntityPlayerMP player : P) {
                        PacketHandler.INSTANCE.sendTo(new MessageParticle(H.posX,H.posY+H.getEyeHeight(),H.posZ,new Vec3d(G.get(0).posX-0.8125,G.get(0).posY+G.get(0).getEyeHeight(),G.get(0).posZ),8),player);
                    }
                }else{
                    A.attackEntityFrom(ModDamageSource.causeElectricalDamage(H),Math.max(2.5f*k,event.getAmount()*0.05f*k));
                    A.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10,false,false));
                    List<EntityPlayerMP> P = H.world.getEntities(EntityPlayerMP.class, e->e.getDistance(H)<64);
                    for (EntityPlayerMP player : P) {
                        PacketHandler.INSTANCE.sendTo(new MessageParticle(H.posX,H.posY+H.getEyeHeight(),H.posZ,new Vec3d(A.posX,A.posY+A.getEyeHeight(),A.posZ),8),player);
                    }
                }
                world.playSound(null, H.posX, H.posY, H.posZ, ModSoundHandler.ELECTRICITY, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);
            }

            //漏电保护
            k=ModEnchantmentInit.GetCiTiaoLevel(H,ModEnchantmentInit.LOU_DIAN_BAO_HU);
            if(k > 0 && event.getSource().getDamageType().equals("electrical")){
                event.setAmount(event.getAmount()*(1-0.025F*k));
            }

            //法术保护
            k=ModEnchantmentInit.GetCiTiaoLevel(H,ModEnchantmentInit.FA_SHU_BAO_HU);
            if(k > 0 && event.getSource().getDamageType().equals("mana")){
                event.setAmount(event.getAmount()*(1-0.025F*k));
            }

            //霹雳箭
            if(event.getSource().getDamageType().equals("arrow") && event.getSource().getImmediateSource() != null && event.getSource().getImmediateSource().getTags().contains("lightning")){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                        && e.getDistanceSq(H)<1024 && !(e instanceof EntityArmorStand) && !(e instanceof EntityPlayer) && !e.equals(H) && !e.equals(A));

                EntityLivingBase xnj=null;
                for(EntityLivingBase e:G){
                    if(e.getDistanceSq(H)<256 && e instanceof EntityLightningCowHorn){
                        xnj=e;
                        break;
                    }
                }

                if(xnj != null){
                    xnj.attackEntityFrom(ModDamageSource.causeElectricalDamage(A),event.getAmount());
                    List<EntityPlayerMP> P = H.world.getEntities(EntityPlayerMP.class, e->e.getDistanceSq(H)<4096);
                    for (EntityPlayerMP player : P) {
                        PacketHandler.INSTANCE.sendTo(new MessageParticle(H.posX,H.posY+H.getEyeHeight(),H.posZ,new Vec3d(xnj.posX-0.8125,xnj.posY+xnj.getEyeHeight(),xnj.posZ),8),player);
                    }
                    world.playSound(null, H.posX, H.posY, H.posZ, ModSoundHandler.ELECTRICITY, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);
                }else{
                    EntityLivingBase e1=null;
                    for(EntityLivingBase e:G){
                        if(e.getDistanceSq(H)<256 && (e1==null || e.getDistanceSq(H)<e1.getDistanceSq(H))){
                            e1=e;
                        }
                    }

                    if(e1 != null){
                        world.playSound(null, H.posX, H.posY, H.posZ, ModSoundHandler.ELECTRICITY, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);
                        e1.attackEntityFrom(ModDamageSource.causeElectricalDamage(A),event.getAmount());
                        e1.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10,false,false));
                        List<EntityPlayerMP> P = H.world.getEntities(EntityPlayerMP.class, e->e.getDistanceSq(H)<64*64);
                        for (EntityPlayerMP player : P) {
                            PacketHandler.INSTANCE.sendTo(new MessageParticle(H.posX,H.posY+H.getEyeHeight(),H.posZ,new Vec3d(e1.posX,e1.posY+e1.getEyeHeight(),e1.posZ),8),player);
                        }

                        for(EntityLivingBase e:G){
                            if(e.getDistanceSq(e1)<256 && e instanceof EntityLightningCowHorn){
                                xnj=e;
                                break;
                            }
                        }

                        if(xnj != null) {
                            xnj.attackEntityFrom(ModDamageSource.causeElectricalDamage(A) , event.getAmount());
                            for (EntityPlayerMP player : P) {
                                PacketHandler.INSTANCE.sendTo(new MessageParticle(e1.posX , e1.posY + e1.getEyeHeight() , e1.posZ , new Vec3d(xnj.posX - 0.8125 , xnj.posY + xnj.getEyeHeight() , xnj.posZ) , 8) , player);
                            }
                        }else{
                            EntityLivingBase e2=null;
                            for(EntityLivingBase e:G){
                                if(!e.equals(e1) && e.getDistanceSq(e1)<256 && (e2==null || e.getDistanceSq(e1)<e2.getDistanceSq(e1))){
                                    e2=e;
                                }
                            }

                            if(e2 != null){
                                e2.attackEntityFrom(ModDamageSource.causeElectricalDamage(A),event.getAmount());
                                e2.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10,false,false));
                                for (EntityPlayerMP player : P) {
                                    PacketHandler.INSTANCE.sendTo(new MessageParticle(e1.posX,e1.posY+e1.getEyeHeight(),e1.posZ,new Vec3d(e2.posX,e2.posY+e2.getEyeHeight(),e2.posZ),8),player);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (B == null) {
            //溺水百分比伤害
            if (H.getLastDamageSource() == DamageSource.DROWN) {
                event.setAmount(H.getMaxHealth() * 0.05f);
            }
        }
    }

    @SubscribeEvent
    public void E(LivingFallEvent event) {
        World world = event.getEntity().world;
        EntityLivingBase H=event.getEntityLiving();
        if(!world.isRemote){
            ItemStack chest=H.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack feet=H.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            //喷气靴子免疫摔落伤害
            if (feet.getItem() == ModItems.PEN_QI_XIE
                    && feet.getTagCompound() != null
                    && Objects.requireNonNull(feet.getTagCompound()).getByte("fall")==1) {
                event.setCanceled(true);
                NBTTagCompound nbt = feet.getTagCompound();
                nbt.setTag("fall", new NBTTagInt(0));
                feet.setTagCompound(nbt);
            }

            //奥安萨落地范围伤害
            if (chest.getItem() == ModItems.AAS
                    && chest.getTagCompound() != null
                    && Objects.requireNonNull(chest.getTagCompound()).getByte("fall")==1) {
                event.setCanceled(true);
                EntityHuoLong.spawnParticle(H.posX , H.posY , H.posZ , 3 , world);
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class , e -> e.getDistanceSq(H) < 144
                        && !e.isPotionActive(MobEffects.FIRE_RESISTANCE)
                        && !(e instanceof EntityArmorStand)
                        && !(e instanceof EntityPlayer)
                        && e != H
                        && Math.abs(H.posY - e.posY) < 1);
                for (int i = 0; i <= G.size() - 1; i++) {
                    G.get(i).attackEntityFrom(ModDamageSource.causeSteamDamage(H,H) , 90);
                    if ((H.posX - G.get(i).posX != 0) || (H.posZ - G.get(i).posZ != 0)) {
                        G.get(i).knockBack(H , 0.8f , H.posX - G.get(i).posX , H.posZ - G.get(i).posZ);
                        G.get(i).motionY += 0.5;
                    }
                }

                NBTTagCompound nbt = chest.getTagCompound();
                nbt.setTag("fall", new NBTTagInt(0));
                chest.setTagCompound(nbt);
            }
        }
    }

    //反射
    protected void Fan_She(EntityLivingBase Attacker, EntityLivingBase entity, float Damage) {
        Attacker.attackEntityFrom(DamageSource.causeMobDamage(entity), Damage);
    }


    @SubscribeEvent
    public void E(LivingKnockBackEvent event) {
        World world = event.getEntity().world;
        if (!world.isRemote && event.getEntity() instanceof EntityLivingBase
                && event.getOriginalAttacker() instanceof EntityLivingBase) {
            EntityLivingBase H = (EntityLivingBase) event.getEntity();
            EntityLivingBase A = (EntityLivingBase) event.getOriginalAttacker();

            //骑士刀格挡无击退
            ItemStack shield = H.isHandActive() ? H.getActiveItemStack() : ItemStack.EMPTY;
            if(!shield.isEmpty() && shield.getItem() instanceof ItemQiShiDao){
                event.setCanceled(true);
            }

            //震慑反击退敌人
            int j = ModEnchantmentInit.GetCiTiaoLevel(H,ModEnchantmentInit.ZHEN_SHE);
            if (ModEnchantmentInit.HasCiTiao(H,ModEnchantmentInit.ZHEN_SHE)) {
                if (H.getRNG().nextFloat() < j * 0.05) {
                    event.setCanceled(true);
                }
                if (j > 20 && H.getRNG().nextFloat() < 0.5) {
                    float f = (float) (0.1 * j - 2) * event.getStrength();
                    double x = -event.getRatioX();
                    double z = -event.getRatioZ();
                    if(x*x+z*z != 0) {
                        A.knockBack(H, f, x, z);
                    }
                }
            }

            //蒸汽锤子技能
            if(A.getHeldItemMainhand().getItem() == ModItems.ZHENG_QI_CHUI && A.getHeldItemMainhand().isItemEnchanted()){
                event.setStrength(event.getStrength()*3);
            }
        }
    }

    //盾牌掉耐久
    @SubscribeEvent
    public void E(LivingAttackEvent event) {
        World world = event.getEntity().world;
        if (!event.getSource().isUnblockable() && !world.isRemote && event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase H = (EntityLivingBase) event.getEntity();
            ItemStack shield = H.isHandActive() ? H.getActiveItemStack() : ItemStack.EMPTY;
            if (!shield.isEmpty() && (shield.getItem() instanceof ItemShieldBase || shield.getItem() instanceof ItemShuangZiJian)) {
                if(shield.getItemDamage() < shield.getMaxDamage() - MathHelper.floor(event.getAmount())) {
                    if(shield.getItem() instanceof ISteamWeapon){
                        ISteamWeapon.shrinkSteam(shield,MathHelper.floor(event.getAmount())*((ISteamWeapon)shield.getItem()).maxItemSteam()/shield.getMaxDamage());
                    }else{
                        shield.setItemDamage(shield.getItemDamage() + MathHelper.floor(event.getAmount()));
                    }
                }else{
                    shield.setItemDamage(shield.getMaxDamage()-1);
                    EntityPlayer player = (EntityPlayer) H;
                    player.getCooldownTracker().setCooldown(shield.getItem(), 60);
                    world.setEntityState(player, (byte) 30);
                    player.resetActiveHand();

                    world.playSound(null, H.posX, H.posY, H.posZ, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);
                }

                if(ModEnchantmentInit.JING_JI_ZHI_DUN.getEnchantmentLevel(shield) > 0 && event.getSource().getTrueSource() != null){
                    EntityPlayer player = (EntityPlayer)H;
                    int f=ModEnchantmentInit.JING_JI_ZHI_DUN.getEnchantmentLevel(shield);
                    event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(player),MathHelper.clamp(event.getAmount(),f*5,f*20));
                }

                if(shield.getItem().equals(ModItems.CRYSTAL_SHIELD)){
                    if(shield.hasTagCompound()){
                        if(shield.getTagCompound().hasKey("num")){
                            int num=shield.getTagCompound().getInteger("num");
                            shield.getTagCompound().setInteger("num",num+1);
                            if(num+1>=2 && EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER,shield)==0) shield.addEnchantment(Enchantments.POWER,1);

                            if(num==1) world.playSound(null, H.posX, H.posY, H.posZ, ModSoundHandler.CRYSTAL_SHIELD, SoundCategory.PLAYERS, 2F, H.getRNG().nextFloat() * 0.1F + 0.9F);
                        }else{
                            shield.getTagCompound().setInteger("num",1);
                        }
                    }else{
                        shield.setTagCompound(new NBTTagCompound());
                        shield.getTagCompound().setInteger("num",1);
                    }
                }
            }
        }
    }

    //判断是否是盾牌，返回布尔值
    protected boolean IsShieldII(ItemStack Item, EntityLivingBase entity) {
        return (Item.getItem()==Items.SHIELD || Item.getItem() instanceof ItemShieldBase || Arrays.asList(NPCmodShield.NPCShields).contains(Item.getUnlocalizedName()) || Item.getItem() instanceof ItemShuangZiJian);
    }

    //百分比回血
    @SubscribeEvent
    public void E(LivingHealEvent event) {
        World world = event.getEntity().world;
        if(event.getEntityLiving().isPotionActive(ModPotions.ZAI_SHENG_TING_ZHI)){
            event.setCanceled(true);
        }
        if (!world.isRemote && event.getEntity() instanceof EntityPlayer && event.getAmount() <= 1) {
            event.setAmount(event.getEntityLiving().getMaxHealth() * 0.0225f * event.getAmount());
        }

        //古树盾
        ItemStack itemStack=event.getEntityLiving().getHeldItemMainhand().getItem()== ModItems.TREE_SHIELD?event.getEntityLiving().getHeldItemMainhand() : event.getEntityLiving().getHeldItemOffhand();
        if(itemStack.getItem() == ModItems.TREE_SHIELD && !event.getEntityLiving().isHandActive()){
            int amount= (int) Math.min(10*event.getAmount(),100);
            if(itemStack.getItemDamage()>=amount) {
                itemStack.setItemDamage(itemStack.getItemDamage() - amount);
            }else{
                itemStack.setItemDamage(0);
            }
        }
    }

    @SubscribeEvent
    public void E(LivingDeathEvent event) {
        World world = event.getEntity().world;
        EntityLivingBase E = event.getEntityLiving();
        if(event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase A = (EntityLivingBase) event.getSource().getTrueSource();
            //公正之刺爆炸
            if (E != null && A != null && event.getSource().getImmediateSource() instanceof EntityGongZhengZhiCi && ((EntityGongZhengZhiCi) event.getSource().getImmediateSource()).getExplode()) {
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                        && e.getDistanceSq(E)<81
                        && !e.equals(A)
                        && !e.equals(E)
                        && !(e instanceof EntityPlayer)
                        && !(e instanceof EntityArmorStand));
                for (EntityLivingBase entityLivingBase : G) {
                    entityLivingBase.attackEntityFrom(ModDamageSource.causeManaDamage(A,Objects.requireNonNull(event.getSource().getTrueSource())), 165);
                }
                world.playSound(null, E.posX, E.posY, E.posZ, ModSoundHandler.GONG_ZHENG_ZHI_CI1 , SoundCategory.PLAYERS, 2F, world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }

        //蝙蝠套死亡音效
        if(E.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.BAT_HEAD || E.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ModItems.BAT_CHEST || E.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ModItems.BAT_LEG || E.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ModItems.BAT_BOOT){
            world.playSound(null, E.posX, E.posY, E.posZ, ModSoundHandler.BAT, SoundCategory.PLAYERS, 2F, world.rand.nextFloat() * 0.1F + 0.9F);
        }
    }

    @SubscribeEvent
    public void E(AttackEntityEvent event){
        EntityPlayer A = (EntityPlayer)event.getEntity();
        if(event.getTarget() instanceof EntityLivingBase) {
            EntityLivingBase H = (EntityLivingBase) event.getTarget();
            //轻甲减攻击冷却
            if (ModEnchantmentInit.QIN_JIA.getEnchantmentLevel(A.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) > 0) {
                event.setCanceled(true);
                if(A.getHeldItem(EnumHand.MAIN_HAND).getItem().onLeftClickEntity(A.getHeldItem(EnumHand.MAIN_HAND), A, H)){
                    return;
                }
                float f = (float) A.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
                float f2 = A.getCooledAttackStrength(0);
                f = (float) (f * Math.pow(f2,1.5));
                boolean flag = false;
                if (A.fallDistance > 0.0F && !A.onGround && !A.isOnLadder() && !A.isInWater() && !A.isPotionActive(MobEffects.BLINDNESS) && !A.isRiding()) {
                    f *= 1.5F;
                    flag = true;
                }
                if (f2 == 1) {
                    if (!flag) {
                        A.world.playSound(null, A.posX, A.posY, A.posZ, SoundEvents.ENTITY_ARROW_HIT_PLAYER, A.getSoundCategory(), 1.0F, 0.5F);
                    } else {
                        A.world.playSound(null, A.posX, A.posY, A.posZ, SoundEvents.ENTITY_ARROW_HIT_PLAYER, A.getSoundCategory(), 1.0F, 1.0F);
                        for (int i = 0; i < 5; i++) {
                            A.world.spawnParticle(EnumParticleTypes.REDSTONE, H.posX + Math.random() - 0.5, H.posY + 0.5 + Math.random(), H.posZ + Math.random() - 0.5, 0, 0, 0);
                        }
                    }
                } else {
                    A.world.playSound(null, A.posX, A.posY, A.posZ, SoundEvents.ENTITY_MULE_CHEST, A.getSoundCategory(), 1.0F, 1.0F);
                }
                int j = EnchantmentHelper.getFireAspectModifier(A);
                if (j > 0)
                {
                    H.setFire(j * 4);
                }
                H.attackEntityFrom(DamageSource.causePlayerDamage(A), f);
                H.hurtResistantTime = 12;
            }

            //破伤
            if(ModEnchantmentInit.PO_SHANG.getEnchantmentLevel(A.getHeldItemMainhand())>0){
                event.setCanceled(true);
                if(A.getHeldItem(EnumHand.MAIN_HAND).getItem().onLeftClickEntity(A.getHeldItem(EnumHand.MAIN_HAND), A, H)){
                    return;
                }
                float f = ModEnchantmentInit.PO_SHANG.getEnchantmentLevel(A.getHeldItemMainhand())*5;
                float f2 = A.getCooledAttackStrength(0.5F);
                f=f*f2*f2;
                A.world.playSound(null, A.posX, A.posY, A.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, A.getSoundCategory(), 1.0F, 1.0F);
                int j = EnchantmentHelper.getFireAspectModifier(A);
                if (j > 0)
                {
                    H.setFire(j * 4);
                }
                H.attackEntityFrom(ModDamageSource.causeArmorBrokenDamage(A), f);
            }
        }
    }

    //火龙套
    @SubscribeEvent
    public void E(ArrowLooseEvent event){
        EntityPlayer player=event.getEntityPlayer();
        World world=event.getWorld();
        if(ModEnchantmentInit.isArmorAllEquiped(ModItems.HUO_LONG_TOU,ModItems.HUO_LONG_JIA,ModItems.HUO_LONG_TUI,ModItems.HUO_LONG_XIE,player)
                && event.getCharge()>=20
                && IWand.mana1(150,30,player)){
            EntityHuoLong.spawnParticle(player.posX,player.posY,player.posZ,0,world);
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.getDistance(player)<8
                    && !(e instanceof EntityArmorStand)
                    && e != player
                    && Math.abs(e.posY-player.posY) < 1);
            for (int i = 0; i <= G.size() - 1; i++) {
                G.get(i).attackEntityFrom(ModDamageSource.causeManaDamage(player,player),12);
                G.get(i).hurtResistantTime=0;
                G.get(i).setFire(3);
                if((player.posX - G.get(i).posX != 0) || (player.posZ - G.get(i).posZ != 0)) {
                    G.get(i).knockBack(player, 0.8f, player.posX - G.get(i).posX, player.posZ - G.get(i).posZ);
                }
            }
        }
    }

    //束缚BUFF
    @SubscribeEvent
    public void E(LivingEvent.LivingJumpEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity != null) {
            if(entity.isPotionActive(ModPotions.SU_FU)){
                entity.motionY=0;
            }
        }
    }

    //炸弹果
    @SubscribeEvent
    public void E(LivingEntityUseItemEvent.Finish event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity != null) {
            if(event.getItem().getItem() == ModItems.BOMB_FRUIT){
                entity.motionY+=1;
            }
        }
    }

    private boolean isHuman(EntityLivingBase H){
        return H.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() != ModItems.BIAO_JI && !H.isEntityUndead() && H.getCreatureAttribute() != EnumCreatureAttribute.ARTHROPOD;
    }
}