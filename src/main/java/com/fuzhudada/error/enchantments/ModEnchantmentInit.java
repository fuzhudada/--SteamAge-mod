package com.fuzhudada.error.enchantments;

import com.somebody.idlframewok.IdlFramework;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.somebody.idlframewok.util.CommonDef.TICK_PER_SECOND;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class ModEnchantmentInit {

    public static final EntityEquipmentSlot[] armorSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    public static final EntityEquipmentSlot[] allSlots = EntityEquipmentSlot.values();
    public static final EntityEquipmentSlot[] handSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
    public static final EntityEquipmentSlot[] mainHand = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};

    public static final RegistryNamespaced<ResourceLocation, Enchantment> REGISTRY = net.minecraftforge.registries.GameData.getWrapper(Enchantment.class);
    public static final List<Enchantment> ENCHANTMENT_LIST = new ArrayList<Enchantment>();

    //Example Enchant
    //public static final com.somebody.idlframewok.enchantments.ModEnchantmentBase TEST = new com.somebody.idlframewok.enchantments.ModEnchantmentBase("test", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  mainHand);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase JIN_SHU_QIN_SHI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("jin_shu_qin_shi", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON , mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentDamage AN_SHA = new com.fuzhudada.error.enchantments.ModEnchantmentDamage("an_sha", Enchantment.Rarity.UNCOMMON, 4,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase RONG_LIANG_SHENG_JI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("rong_liang_sheng_ji", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  armorSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentDamage SHI_SHU = new com.fuzhudada.error.enchantments.ModEnchantmentDamage("shi_shu", Enchantment.Rarity.UNCOMMON, 5,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentDamage BU_SHA = new com.fuzhudada.error.enchantments.ModEnchantmentDamage("bu_sha", Enchantment.Rarity.UNCOMMON, 6,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentDamage XU_SHU = new com.fuzhudada.error.enchantments.ModEnchantmentDamage("xu_shu", Enchantment.Rarity.UNCOMMON, 7,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase YU_CHA = new com.fuzhudada.error.enchantments.ModEnchantmentBase("yu_cha", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase ZHEN_FENG_XIANG_DUI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("zhen_feng_xiang_dui", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase FAN_SHE = new com.fuzhudada.error.enchantments.ModEnchantmentBase("fan_she", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  armorSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase QIN_JIA = new com.fuzhudada.error.enchantments.ModEnchantmentBase("qin_jia", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase SHI_XUE = new com.fuzhudada.error.enchantments.ModEnchantmentBase("shi_xue", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase ZHI_CAN_DA_JI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("zhi_can_da_ji", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase CEN_ZHONG = new com.fuzhudada.error.enchantments.ModEnchantmentBase("cen_zhong", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  mainHand).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase ZHEN_SHE = new com.fuzhudada.error.enchantments.ModEnchantmentBase("zhen_she", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  armorSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase MIDRESIS = new com.fuzhudada.error.enchantments.ModEnchantmentBase("middle_resistance", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  armorSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase HEAVYRESIS = new com.fuzhudada.error.enchantments.ModEnchantmentBase("heavy_resistance", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR, allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase GU_DAI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("gu_dai", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase LI_DA_SHI_CHEN = new com.fuzhudada.error.enchantments.ModEnchantmentBase("li_da_shi_chen", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase JIAN_SHOU_ZHEN_XIAN = new com.fuzhudada.error.enchantments.ModEnchantmentBase("jian_shou_zhen_xian", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.BREAKABLE,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase PEI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("pei", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase PO_SHANG = new com.fuzhudada.error.enchantments.ModEnchantmentBase("po_shang", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase JING_JI_ZHI_DUN = new com.fuzhudada.error.enchantments.ModEnchantmentBase("jing_ji_zhi_dun", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.BREAKABLE,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase XIN_SHOU_JIAO_CHENG = new com.fuzhudada.error.enchantments.ModEnchantmentBase("xin_shou_jiao_cheng", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase DU_YE_FU_JIA = new com.fuzhudada.error.enchantments.ModEnchantmentBase("du_ye_fu_jia", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase SHEN_JING_DU_SU = new com.fuzhudada.error.enchantments.ModEnchantmentBase("shen_jing_du_su", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ALL,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase HUO_LI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("huo_li", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase CHONG_FEN_RAN_SHAO = new com.fuzhudada.error.enchantments.ModEnchantmentBase("chong_fen_ran_shao", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase BU_JI_ZHUAN_JIA = new com.fuzhudada.error.enchantments.ModEnchantmentBase("bu_ji_zhuan_jia", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase ZHU_WANG = new com.fuzhudada.error.enchantments.ModEnchantmentBase("zhu_wang", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR_FEET,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase ZHA_DAN_DA_SHI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("zha_dan_da_shi", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase QIN_JIA_CI_KE = new com.fuzhudada.error.enchantments.ModEnchantmentBase("qin_jia_ci_ke", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase CHONG_JI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("chong_ji", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase LIAN_ZHAN = new com.fuzhudada.error.enchantments.ModEnchantmentBase("lian_zhan", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase PAO_HUO = new com.fuzhudada.error.enchantments.ModEnchantmentBase("pao_huo", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase BU_JI = new com.fuzhudada.error.enchantments.ModEnchantmentBase("bu_ji", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase CHONG_NENG = new com.fuzhudada.error.enchantments.ModEnchantmentBase("chong_neng", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.WEAPON,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase DIAN_CHANG = new com.fuzhudada.error.enchantments.ModEnchantmentBase("dian_chang", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase LOU_DIAN_BAO_HU = new com.fuzhudada.error.enchantments.ModEnchantmentBase("lou_dian_bao_hu", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
    public static final com.fuzhudada.error.enchantments.ModEnchantmentBase FA_SHU_BAO_HU = new com.fuzhudada.error.enchantments.ModEnchantmentBase("fa_shu_bao_hu", Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.ARMOR,  allSlots).setMaxLevel(2);
//            .setMaxLevel(10).setValue(0.3f, 0.3f);

    public static boolean HasCiTiao(EntityLivingBase entity,com.fuzhudada.error.enchantments.ModEnchantmentBase ench){
        Predicate<com.fuzhudada.error.enchantments.ModEnchantmentBase> pd=e ->
                e.getEnchantmentLevel(entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD))>0
                && e.getEnchantmentLevel(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST))>0
                && e.getEnchantmentLevel(entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS))>0
                && e.getEnchantmentLevel(entity.getItemStackFromSlot(EntityEquipmentSlot.FEET))>0;
        return pd.test(ench);
    }

    public static int GetCiTiaoLevel(EntityLivingBase player,com.fuzhudada.error.enchantments.ModEnchantmentBase ench){
        return ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD))
                +ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST))
                +ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS))
                +ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.FEET));
    }

    public static int GetCountOfCiTiaoArmor(EntityLivingBase player,com.fuzhudada.error.enchantments.ModEnchantmentBase ench){
        boolean[] hasEnch={ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD))>0
                , ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST))>0
                , ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS))>0
                , ench.getEnchantmentLevel(player.getItemStackFromSlot(EntityEquipmentSlot.FEET))>0};
        int s=0;
        for(boolean bl:hasEnch){
            if(bl){
                s++;
            }
        }
        return s;
    }

    public static boolean isArmorAllEquiped(Item head,Item chest,Item leg,Item feet,EntityLivingBase player){
        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem()==head
                && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()==chest
                && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem()==feet
                && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem()==leg;
    }

    //Conflict groups
    public static final Enchantment[] WEAPON_DAMAGE_CONFLICT_GROUP = new Enchantment[]
            {
                    Enchantments.SHARPNESS,
                    Enchantments.SMITE,
                    Enchantments.BANE_OF_ARTHROPODS,
                    //ANTI_VANILLA,
            };


    public static void BeforeRegister()
    {
        //ANTI_AOA.setHidden(!MetaUtil.isLoaded_AOA3);

        ApplyConflictGroup(WEAPON_DAMAGE_CONFLICT_GROUP);
    }

    private static void ApplyConflictGroup(Enchantment[] group)
    {
        for (Enchantment ench:
                group
        ) {
            //is my enchants
            if (ench instanceof ModEnchantmentBase)
            {
                ((ModEnchantmentBase) ench).setConflicts(group);
            }
        }
    }


    @SubscribeEvent
    public static void onCreatureHurt(LivingHurtEvent evt) {
        World world = evt.getEntity().getEntityWorld();
        EntityLivingBase hurtOne = evt.getEntityLiving();

        if (evt.isCanceled() || world.isRemote)
        {
            return;
        }

        float dmgModifier = 1f;

        Entity trueSource = evt.getSource().getTrueSource();
        if (trueSource instanceof EntityLivingBase) {

            EntityLivingBase attacker = (EntityLivingBase) trueSource;

        }

        evt.setAmount(evt.getAmount() * dmgModifier);
    }

    @SubscribeEvent
    public static void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase livingBase = event.getEntityLiving();

        for (EntityEquipmentSlot slot:
                EntityEquipmentSlot.values()) {
            checkItemForTicking(livingBase, slot);
        }
    }

    public static void checkItemForTicking(EntityLivingBase living, EntityEquipmentSlot slot)
    {
        World world = living.world;

        ItemStack stack = living.getItemStackFromSlot(slot);
        if (!stack.isEmpty())
        {
            if (world.getWorldTime() % TICK_PER_SECOND == 0)
            {

            }
        }
    }

    void OnJump(LivingEvent.LivingJumpEvent event)
    {

    }
}
