package com.fuzhudada.error.item;

import com.fuzhudada.error.blocks.itemBlock.ItemBlockCampfire;
import com.fuzhudada.error.item.Item.ItemTeXiao;
import com.fuzhudada.error.item.Item.*;
import com.fuzhudada.error.item.XunXiang.ItemXiangLu;
import com.fuzhudada.error.item.XunXiang.ItemXunXiang;
import com.fuzhudada.error.item.armor.*;
import com.fuzhudada.error.item.bow.*;
import com.fuzhudada.error.item.food.*;
import com.fuzhudada.error.item.gun.*;
import com.fuzhudada.error.item.shield.*;
import com.fuzhudada.error.item.shield.ItemBiaoJi;
import com.fuzhudada.error.item.shield.ItemRenYuJia;
import com.fuzhudada.error.item.shield.ItemRenYuTou;
import com.fuzhudada.error.item.shield.ItemRenYuTui;
import com.fuzhudada.error.item.shield.ItemRenYuXie;
import com.fuzhudada.error.item.shield.ItemSteam;
import com.fuzhudada.error.item.shield.ItemXieYi;
import com.fuzhudada.error.item.shield.ItemYangQiBeiBao;
import com.fuzhudada.error.item.sword.*;
import com.fuzhudada.error.item.wand.*;
import com.somebody.idlframewok.init.ModCreativeTab;
import com.somebody.idlframewok.item.ItemArmorBase;
import com.somebody.idlframewok.item.ItemBase;
import com.somebody.idlframewok.item.ItemSwordBase;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.init.SoundEvents.ENTITY_ZOMBIE_DEATH;
import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_REN_YU = EnumHelper.addArmorMaterial("ren_yu","error:renyu",1000,new int[] {3,6,8,3},10,ITEM_ARMOR_EQUIP_DIAMOND,2);
	public static final Item.ToolMaterial STEAM1 = EnumHelper.addToolMaterial("steam",1,5,1,5,1);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_XIE_YI = EnumHelper.addArmorMaterial("xie_yi","error:xie_yi",1000,new int[] {3,6,8,3},10,ITEM_ARMOR_EQUIP_DIAMOND,2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BIAO_JI = EnumHelper.addArmorMaterial("biao_ji","error:biao_ji",1000,new int[] {3,6,8,3},10,ITEM_ARMOR_EQUIP_DIAMOND,2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_SEA = EnumHelper.addArmorMaterial("sea","error:sea",1875,new int[] {3,6,8,3},10,ITEM_ARMOR_EQUIP_DIAMOND,2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_ZHAN_NU = EnumHelper.addArmorMaterial("zhan_nu","error:zhan_nu",2400,new int[] {3,6,8,3},10,ENTITY_ZOMBIE_DEATH,2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_ZHEN_QI = EnumHelper.addArmorMaterial("zhen_qi","error:zhen_qi",2400,new int[] {3,6,8,3},10,ITEM_ARMOR_EQUIP_DIAMOND,2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_TEST = EnumHelper.addArmorMaterial("armor_material_test", "error:model_armor", 150, new int[]{20, 40, 50, 22}, 105, ITEM_ARMOR_EQUIP_DIAMOND, 15.0F);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_FIRE_DRAGON = EnumHelper.addArmorMaterial("armor_material_fire_dragon", "error:fire_dragon", 1000, new int[]{3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_SA = EnumHelper.addArmorMaterial("armor_material_sa", "error:sav1", 2400,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LIFU = EnumHelper.addArmorMaterial("armor_material_lifu", "error:lifu", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_DONGLIJIA = EnumHelper.addArmorMaterial("armor_material_donglijia", "error:donglijia", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_AAS = EnumHelper.addArmorMaterial("armor_material_aas", "error:aas", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BAIJIN1 = EnumHelper.addArmorMaterial("armor_material_bai_jin1", "error:bai_jin1", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BAIJIN2 = EnumHelper.addArmorMaterial("armor_material_bai_jin2", "error:bai_jin2", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_JCSL = EnumHelper.addArmorMaterial("armor_material_jcsl", "error:jcsl", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BIRD = EnumHelper.addArmorMaterial("armor_material_bird", "error:bird", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_COLOR_FEATHER = EnumHelper.addArmorMaterial("armor_material_color_feather", "error:color_feather", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LIGHTNING_COW = EnumHelper.addArmorMaterial("armor_material_lightning_cow", "error:lightning_cow", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_CHINESE_ARMOR = EnumHelper.addArmorMaterial("armor_material_chinese_armor", "error:chinese_armor", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BAT = EnumHelper.addArmorMaterial("armor_material_bat", "error:bat_armor", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BLOODY = EnumHelper.addArmorMaterial("armor_material_bloody", "error:bloody_armor", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);
	public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_BLOODY1 = EnumHelper.addArmorMaterial("armor_material_bloody1", "error:bloody_armor1", 1000,new int[] {3,6,8,3}, 10, ITEM_ARMOR_EQUIP_DIAMOND, 2);


	//虚数界限武器
	public static final Item RONG_HUI_ZHAN_FU = new ItemRongHui("rong_hui_zhan_fu", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item CANG_BAI_ZHI_CHUI = new com.somebody.idlframewok.item.ItemSwordBase("cang_bai_zhi_chui" , Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item REN_YU_JIAN = new com.somebody.idlframewok.item.ItemSwordBase("ren_yu_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item CHI_ZHEN = new com.somebody.idlframewok.item.ItemSwordBase("chi_zhen", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item DAO_CHAO = new ItemDaoChao("dao_chao", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);


	//近战武器
	public static final Item TU_CI_DAO = new ItemTuCi("tu_ci_dao",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item QI_SHI_DAO = new ItemQiShiDao("qi_shi_dao",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHEN_QI_LIAN_JU = new ItemSteamChain("zhen_qi_lian_ju",Item.ToolMaterial.DIAMOND,0).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SHUANG_ZI_JIAN = new ItemShuangZiJian("shuang_zi_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SHUANG_ZI_FU = new ItemShuangZiFu("shuang_zi_fu", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item JIAO_TAN_LIAN_DAO = new ItemJiaoTan("jiao_tan_lian_dao", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item GONG_ZHENG_ZHI_CI = new ItemGongZhengZhiCi("gong_zheng_zhi_ci", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SHOU_XUAN_ZHE = new ItemShouXuanZhe("shou_xuan_zhe", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZLSword = new com.somebody.idlframewok.item.ItemSwordBase("zlsword", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item FuWenJuJian = new ItemFuWenJuJian("fu_wen_ju_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item FuWenJuLian = new ItemJiaoTan("fu_wen_ju_lian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SONG_MU = new ItemSongMu("song_mu", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SUO_MU = new ItemSuoMu("suo_mu", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item DUAN_JIAN = new ItemDuanJian("duan_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item TIAN_SHI_CHUI = new ItemTianShiChui("tian_shi_chui", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item XIANG_LU_JIAN = new ItemXiangLuJian("xiang_lu_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHENG_QI_CHUI = new ItemSteamHammer("zheng_qi_chui", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item WU_ZUO_BIAN_TA = new ItemWuZuoBianTa("wu_zuo_bian_ta", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item FU_RONG_JIAN = new ItemFuRongJian("fu_rong_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item CHONG_JI_CHUI = new ItemChongJiChui("chong_ji_chui", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BA_WANG_QUAN_JIAN = new ItemFuWenJuJian("ba_wang_quan_jian", Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item TIAN_SHI_DAO = new ItemQiShiDao("tian_shi_dao",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item YIN_FU = new ItemSwordBase("yin_fu",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item DI_JIAN = new ItemSwordBase("di_jian",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIE_FENG_QI_QIANG = new ItemLieFengQiQiang("lie_feng_qi_qiang",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHEN_TONG = new ItemZhenTong("zhen_tong",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item YU_HUA_DE_QUAN_BING = new ItemWingedScepter("yu_hua_de_quan_bing",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item DIAN_FAN_JIAN = new ItemZhanFengJuJian("dian_fan_jian",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item NEW_YEAR_HAMMER = new ItemNewYearHammer("new_year_hammer",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BLOODY_SWORD = new ItemSwordBase("bloody_sword",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item RED_CHAIN = new ItemSteamChain("red_chain",Item.ToolMaterial.DIAMOND,1).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BLUE_WIZARD_SWORD = new ItemBlueWizardSword("blue_wizard_sword",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item STORM_SWORD = new ItemStormSword("storm_sword",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item WMC_SWORD = new ItemSwordBase("wmc_sword",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ROCKET_GLOVES = new ItemRocketGloves("rocket_gloves",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item JIE_LV_JIAN = new ItemJieLvJian("jie_lv_jian",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SPEAR = new ItemSpear("spear",Item.ToolMaterial.DIAMOND).setCreativeTab(ModCreativeTab.WEAPON);

	//箭
	public static final Item JIAN2 = new ItemArrowBase("jian2",new Byte("0")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item HUO_YAN_JIAN2 = new ItemArrowBase("huo_yan_jian2",new Byte("1")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item DAO_CI_JIAN2 = new ItemArrowBase("dao_ci_jian2",new Byte("2")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item JIAN3 = new ItemArrowBase("jian3",new Byte("0")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item HUO_YAN_JIAN3 = new ItemArrowBase("huo_yan_jian3",new Byte("1")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item DAO_CI_JIAN3 = new ItemArrowBase("dao_ci_jian3",new Byte("2")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZHU_WANG_JIAN3 = new ItemArrowBase("zhu_wang_jian3",new Byte("3")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item HUANG_JIN_JIAN = new ItemArrowBase("huang_jin_jian",new Byte("4")).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item LIGHTNING_ARROW = new ItemArrowBase("lightning_arrow",new Byte("5")).setCreativeTab(ModCreativeTab.PROJECT);

	//弓
	public static final Item FAN_QU_GONG = new ItemBowBase("fan_qu_gong",8,new Item[]{JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIE_SHOU_GONG = new ItemBowBase("lie_shou_gong",8,new Item[]{JIAN2,DAO_CI_JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHEN_QI_GONG = new ItemZhenQiGong("zhen_qi_gong",8,2,new Item[]{JIAN2,HUO_YAN_JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item NU_MU = new ItemNuMu("nu_mu").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SHI_ZI_NU = new ItemShiZiNu("shi_zi_nu",38,new Item[]{JIAN2,HUO_YAN_JIAN2,DAO_CI_JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SPIDER_BOW = new ItemBowBase("spider_bow",18,new Item[]{JIAN3,ZHU_WANG_JIAN3}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item HUO_YAN_NU = new ItemShiZiNu("huo_yan_nu",48,new Item[]{JIAN3,HUO_YAN_JIAN3}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item YI_JI_GONG = new ItemBowBase("yi_ji_gong",24,new Item[]{JIAN3,HUANG_JIN_JIAN}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SHA_MO_GONG = new ItemBowBase("sha_mo_gong",24,new Item[]{JIAN3}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item MAN_HUANG_DA_GONG = new ItemManHuangDaGong("man_huang_da_gong",32,new Item[]{JIAN3,DAO_CI_JIAN3,HUO_YAN_JIAN3,ZHU_WANG_JIAN3,HUANG_JIN_JIAN}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item PIG_HAMMER = new ItemPigHammer("pig_hammer",20,new Item[]{JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BOW = new ItemBowBase("bow",2,new Item[]{JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIGHTNING_BOW = new ItemBowBase("lightning_bow",56,new Item[]{JIAN2,LIGHTNING_ARROW}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIGHTNING_CROSSBOW = new ItemShiZiNu("lightning_crossbow",84,new Item[]{JIAN3,LIGHTNING_ARROW}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item RUNE_CROSSBOW = new ItemShiZiNu("rune_crossbow",84,new Item[]{JIAN3,HUO_YAN_JIAN3,DAO_CI_JIAN3,HUANG_JIN_JIAN,ZHU_WANG_JIAN3}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item STONE_BOW = new ItemBowBase("stone_bow",13,new Item[]{JIAN2,DAO_CI_JIAN2,HUO_YAN_JIAN2}).setCreativeTab(ModCreativeTab.WEAPON);

	//子弹
	public static final Item ZI_DAN1 = new ItemBullet("zi_dan1",1,0).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN2 = new ItemBullet("zi_dan2",2,0).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN1_HUO_YAN = new ItemBullet("zi_dan1_huo_yan",1,1).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN2_HUO_YAN = new ItemBullet("zi_dan2_huo_yan",2,1).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN2_PO_JIA = new ItemBullet("zi_dan2_po_jia",2,2).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN3 = new ItemBullet("zi_dan3",3,0).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN3_CUI_DU = new ItemBullet("zi_dan3_cui_du",3,3).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZI_DAN3_XIAN_DAN = new ItemBullet("zi_dan3_xian_dan",3,4).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item BOMB = new ItemBomb("bomb",0).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item FIRE_BOMB = new ItemBomb("fire_bomb",1).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item DUST_BOMB = new ItemBomb("dust_bomb",2).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item DAN_JIA = new ItemBullet("dan_jia",3,5).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZHONG_PAO_DAN = new ItemBullet("zhong_pao_dan",3,6).setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ANTI_TANK_LANDMINE = new ItemAntiTankLandmine("anti_tank_landmine").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item WIND_CHARGE = new ItemWindCharge("wind_charge").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ELECTRICIAL_BOMB = new ItemBomb("electricial_bomb",3,580).setCreativeTab(ModCreativeTab.PROJECT);

	//枪
	public static final Item YI_ZU_ZHI_SHI = new ItemGun("yi_zu_zhi_shi",1,new int[]{0,1},new float[]{20,35},new int[]{20,50}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item PO_JIA_QIANG = new ItemPoJiaQiang("po_jia_qiang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item Biao_Zun_Bu_Qiang = new ItemGun("biao_zun_bu_qiang",2,new int[]{0},new float[]{24},new int[]{20}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item Lian_Fa_Zhen_Qi_Bu_Qiang = new ItemLianFaZhenQiBuQiang("lian_fa_zhen_qi_bu_qiang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SAN_FA_BU_QIANG = new ItemSanFaBuQiang("san_fa_bu_qiang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item JIU_HUO_CHONG = new ItemGun("jiu_huo_chong",1,new int[]{0},new float[]{12},new int[]{20}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item KUI_SHE = new ItemGun("kui_she",3,new int[]{0,3},new float[]{74,74},new int[]{20,20}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHENG_ZHI_ZHE_ZHI_SI = new ItemXianDanQiang("zheng_zhi_zhe_zhi_si",36,3).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item XIAN_DAN_QIANG = new ItemXianDanQiang("xian_dan_qiang",27,3).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIU_DAN_PAO = new ItemLiuDanPao("liu_dan_pao").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item DAN_JIA_QIANG = new ItemDanJiaQiang("dan_jia_qiang",10,22,20).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHONG_PAO = new ItemZhongPao("zhong_pao").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BAO_DAN_QIANG = new ItemGun("bao_dan_qiang",3,new int[]{0,7},new float[]{100,175},new int[]{20,50}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BENG_DONG_XIAN_DAN_QIANG = new ItemXianDanQiang("beng_dong_xian_dan_qiang",58,3).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item NEW_YEAR_KUI_SHE = new ItemNewYearKuiShe("new_year_kui_she",6,112,60).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item GATLING = new ItemGatling("gatling",100,32,200).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item YELLOW_KUI_SHE = new ItemBase("yellow_kui_she").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item NATURAL_SOUL = new ItemNaturalSoul("natural_soul",1,new int[]{0,1},new float[]{148,260},new int[]{20,50}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIGHTNING_GUN = new ItemLightningGun("lightning_gun",1,new int[]{0,2},new float[]{162,108},new int[]{20,20}).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item GUARD_SHOTGUN = new ItemXianDanQiang("guard_shotgun",9,1).setCreativeTab(ModCreativeTab.WEAPON);


	//法杖
	public static final Item SHE_JIAN_FA_ZHANG = new ArrowWand("she_jian_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHA_DAN_FA_ZHANG = new ExpWand("zha_dan_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item Tou_Shi_Suo = new TouShiSuoWand("tuo_shi_suo").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item You_Hun_Zhao_Huan = new YouHunZhaoHuan("you_hun_zhao_huan").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item FEI_DAN_FA_ZHANG = new SuSheFeiDan("su_she_fei_dan_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHI_LIAO_FA_ZHANG = new ZhiLiaoWand("zhi_liao_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item MENG_DU_FA_ZHANG = new MengDuFaZhang("meng_du_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item JING_JU_ZHI_SHOU = new YouHunZhaoHuan("jing_ju_zhi_shou").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item DI_CI_FA_ZHANG = new DiCiWand("di_ci_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item TENG_WAN_FA_ZHANG = new TengWanWand("teng_wan_fa_zhang").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item SPARKLING_WAND = new YouHunZhaoHuan("sparkling_wand",3300,600, SoundEvents.BLOCK_END_PORTAL_SPAWN,100).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item COAL_WAND = new CoalWand("coal_wand").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item WIND_WAND = new WindWand("wind_wand").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BONE_WAND = new BoneWand("bone_wand").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item SAND_CANNON = new SandCannon("sand_cannon").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BLOODY_WAND = new BloodyWand("bloody_wand").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIGHTNING_WAND = new LightningWand("lightning_wand",false).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item LIGHTNING_WAND_CREATIVE = new LightningWand("lightning_wand_creative",true).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item WHITE_SNACK_WAND = new WhiteSnackWand("white_snack_wand").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item EYE_WAND = new WhiteSnackWand("eye_wand").setCreativeTab(ModCreativeTab.WEAPON);
	//三级急迫用于标记破绽是否消失


	//魔力聚集器
	public static final Item MO_LI_JU_JI_QI = new MoLiJuJiQi("mo_li_ju_ji_qi",2,2400).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item QIANG_HUA_MO_LI_JU_JI_QI = new MoLiJuJiQi("qiang_hua_mo_li_ju_ji_qi",3,3600).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item KUO_CHONG_JU_JI_QI = new MoLiJuJiQi("kuo_chong_ju_ji_qi",3,4500).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item HU_XIAO_JU_JI_QI = new MoLiJuJiQi("hu_xiao_ju_ji_qi",4,3600).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item PO_SUI_JU_JI_QI = new MoLiJuJiQi("po_sui_ju_ji_qi",1,1200).setCreativeTab(ModCreativeTab.WEAPON);


	//盾牌
	public static final Item XIAO_YUAN_DUN = new ItemShieldBase("xiao_yuan_dun",750).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item GANG_BAN = new ItemShieldBase("gang_ban",1500,SoundEvents.BLOCK_ANVIL_LAND).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZU_ZHOU_SHI_BAN_DUN = new ItemZuZhouShiBanDun("zu_zhou_shi_ban_dun",1500).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item FAN_SHANG_DUN = new ItemShieldBase("fan_shang_dun",3300,SoundEvents.BLOCK_STONE_BREAK).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item ZHEN_QI_DUN = new ItemSteamShield("zhen_qi_dun",3300).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item TREE_SHIELD = new ItemShieldBase("tree_shield",3000).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item BEAR_PAW = new ItemBearPaw("bear_paw",1500).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item CRYSTAL_SHIELD = new ItemCrystalShield("crystal_shield",10000).setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item JIE_LV_DUN = new ItemJieLvDun("jie_lv_dun",18000).setCreativeTab(ModCreativeTab.WEAPON);

	//食物
	public static final Item CHOCOLATE = new HealFoodBase("chocolate",4,0.4F,true, 40).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item JIANG_TANG = new HealFoodBase("jiang_tang",20,1.0F,false, 250).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item HAI_XIAN_TANG = new HealFoodBase("hai_xian_tang",15,1.0F,false, 20).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item XIE_ROU_FAN = new HealFoodBase("xie_rou_fan",8,0.8F,false, 5).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item YOU_YU_CHUAN1 = new HealFoodBase("you_yu_chuan1",8,0.8F,false, 5).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item YOU_YU_CHUAN2 = new EffectFoodBase("you_yu_chuan2",8,0.8F,false, new PotionEffect(MobEffects.INSTANT_DAMAGE,5,7)).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item YOU_YU_CHUAN3 = new com.somebody.idlframewok.item.ItemBase("you_yu_chuan3").setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHENG_YOU_YU = new com.somebody.idlframewok.item.food.ItemFoodBase("sheng_you_yu",3,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHENG_XIE_ROU = new com.somebody.idlframewok.item.food.ItemFoodBase("sheng_xie_rou",3,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHENG_SHA_YU = new com.somebody.idlframewok.item.food.ItemFoodBase("sheng_sha_yu",3,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHENG_HAI_TUN = new com.somebody.idlframewok.item.food.ItemFoodBase("sheng_hai_tun",3,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHENG_HAI_XIANG = new com.somebody.idlframewok.item.food.ItemFoodBase("sheng_hai_xiang",3,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHU_YOU_YU = new com.somebody.idlframewok.item.food.ItemFoodBase("shu_you_yu",8,0.8F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHU_XIE_ROU = new com.somebody.idlframewok.item.food.ItemFoodBase("shu_xie_rou",8,0.8F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHU_SHA_YU = new com.somebody.idlframewok.item.food.ItemFoodBase("shu_sha_yu",8,0.8F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHU_HAI_TUN = new com.somebody.idlframewok.item.food.ItemFoodBase("shu_hai_tun",8,0.8F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item SHU_HAI_XIANG = new com.somebody.idlframewok.item.food.ItemFoodBase("shu_hai_xiang",8,0.8F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item XIE_QIAN = new HealFoodBase("xie_qian",12,0.8F,false,20).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item HAI_XIAN_TANG_DI_LIAO = new com.somebody.idlframewok.item.food.ItemFoodBase("hai_xian_tang_di_liao",2,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item HAI_TAI = new HealFoodBase("hai_tai",4,0.1F,false,12).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item XUN_XIANG_GUO = new EffectFoodBase("xun_xiang_guo",4,0.4F,false, new PotionEffect(ModPotions.QU_SAN_XIANG,200,0)).setAlwaysEdible().setCreativeTab(ModCreativeTab.FOOD);
	public static final Item MANA_COOKIE = new ManaCookie("mana_cookie",2,0.1F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item BOMB_FRUIT = new ItemBombFruit("bomb_fruit",4,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item GOLDEN_FRUIT = new ItemGoldenFruit("golden_fruit",4,0.4F,false,new PotionEffect(ModPotions.CEN_DIAN,600,0)).setAlwaysEdible().setCreativeTab(ModCreativeTab.FOOD);
	public static final Item MANA_PUMPKIN_PIE = new ManaCookie("mana_pumpkin_pie",2,0.1F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item WIND_FRUIT = new ItemWindFruit("wind_fruit",4,0.3F,false).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item LIGHTNING_BEEF_RAW = new com.somebody.idlframewok.item.food.ItemFoodBase("lightning_beef_raw",3,0.3F,true).setCreativeTab(ModCreativeTab.FOOD);
	public static final Item LIGHTNING_BEEF_COOKED = new com.somebody.idlframewok.item.food.ItemFoodBase("lightning_beef_cooked",8,0.8F,true).setCreativeTab(ModCreativeTab.FOOD);


	//工具
	public static final Item STEAM = new ItemSteam("steam",STEAM1).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item DIAN_CHI = new ItemDianChi("dian_chi").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item YIN_SHI_DAI = new ItemPotionPack("yin_shi_dai",new PotionEffect(MobEffects.GLOWING,100,0)).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item YOU_PIN = new ItemPotionPack("you_pin",new PotionEffect(ModPotions.RAN_SHAO_KONG_QI,100,0)).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item SHI_HUI_DAI = new ItemPotionPack("shi_hui_dai",new PotionEffect(MobEffects.BLINDNESS,100,0)).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item ZHEN_QI_GUAN_ZI = new ItemZhenQiGuanZi("zhen_qi_guan_zi").setCreativeTab(ModCreativeTab.MACHINE);
	public static final Item HUANG_JIN_SHUI_HU = new GoldenKettle("huang_jin_shui_hu").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item ZI_DAN_BEI_BAO = new ItemBulletBag("zi_dan_bei_bao").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item PEN_HUO_QI = new ItemPenHuoQi("pen_huo_qi",4).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item BAG_BLUE = new ItemBag("bag_blue").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item BAG_YELLOW = new ItemBag("bag_yellow").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item BAG_GREEN = new ItemBag("bag_green").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item BAG_RED = new ItemBag("bag_red").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item WATER_JAR = new ItemWaterJar("water_jar").setCreativeTab(ModCreativeTab.MACHINE);
	public static final Item ARROW_BAG_BLUE = new ItemArrowBag("arrow_bag_blue").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item ARROW_BAG_YELLOW = new ItemArrowBag("arrow_bag_yellow").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item ARROW_BAG_GREEN = new ItemArrowBag("arrow_bag_green").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item ARROW_BAG_RED = new ItemArrowBag("arrow_bag_red").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item PEN_HUO_QI2 = new ItemPenHuoQi("pen_huo_qi2",30).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item DEER_WAND = new com.somebody.idlframewok.item.ItemBase("deer_wand").setCreativeTab(ModCreativeTab.TOOL).setMaxStackSize(1);
	public static final Item DEER_HORN = new com.somebody.idlframewok.item.ItemBase("deer_horn").setCreativeTab(ModCreativeTab.TOOL).setMaxStackSize(1);
	public static final Item CAMPFIRE = new ItemBlockCampfire("campfire",1).setCreativeTab(ModCreativeTab.NEW_VERSION_BLOCK);
	public static final Item SOUL_CAMPFIRE = new ItemBlockCampfire("soul_campfire",2).setCreativeTab(ModCreativeTab.NEW_VERSION_BLOCK);


	//材料
	public static final Item HUO_OU = new com.somebody.idlframewok.item.ItemBase("huo_ou").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item NIU_NAI = new com.somebody.idlframewok.item.ItemBase("niu_nai").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item YAN_JIN_JIANG = new com.somebody.idlframewok.item.ItemBase("yan_jin_jiang").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item ZAO_GUI = new com.somebody.idlframewok.item.ItemBase("zao_gui").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item REN_ZAO_XIAN_WEI = new com.somebody.idlframewok.item.ItemBase("ren_zao_xian_wei").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item LIU_HUANG = new com.somebody.idlframewok.item.ItemBase("liu_huang").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item WO_LUN = new com.somebody.idlframewok.item.ItemBase("wo_lun").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item QI_FA = new com.somebody.idlframewok.item.ItemBase("qi_fa").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item GUAN_ZI = new ItemBase("guan_zi").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item LIU_XI_JUN = new com.somebody.idlframewok.item.ItemBase("liu_xi_jun").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item YIN_WU_LUO_KE = new com.somebody.idlframewok.item.ItemBase("yin_wu_luo_ke").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item SHA_YU_SI_LIAO = new com.somebody.idlframewok.item.ItemBase("sha_yu_si_liao").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item SHA_YU_YA = new com.somebody.idlframewok.item.ItemBase("sha_yu_ya").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item WU_GUI_KE = new com.somebody.idlframewok.item.ItemBase("wu_gui_ke").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item KONG_GUAN_ZI = new ItemEmptyJar("kong_guan_zi").setCreativeTab(ModCreativeTab.MACHINE);
	public static final Item WU_ZI_ZHI_GUO = new com.somebody.idlframewok.item.ItemBase("wu_zi_zhi_guo").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item BOMB_FRUIT_SEED = new ItemBombFruitSeed("bomb_fruit_seed").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item SANDY_THORNS_SEED = new ItemSandyThornsSeed("sandy_thorns_seed").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item SANDY_THORNS = new ItemSandyThornsSeed("sandy_thorns").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item TANK_ARMOR = new com.somebody.idlframewok.item.ItemBase("tank_armor").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item GOLDEN_BRANCH = new com.somebody.idlframewok.item.ItemBase("golden_branch").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item WEN_XIAN_SHI_BAN = new com.somebody.idlframewok.item.ItemBase("wen_xian_shi_ban").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item MANA_PAPER = new com.somebody.idlframewok.item.ItemBase("mana_paper").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item MANA_SUGAR = new com.somebody.idlframewok.item.ItemBase("mana_sugar").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item CRYSTAL = new com.somebody.idlframewok.item.ItemBase("crystal").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item DEENCHANT_STONE = new com.somebody.idlframewok.item.ItemBase("deenchant_stone").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item BREEZE_ROD = new com.somebody.idlframewok.item.ItemBase("breeze_rod").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item WIND_FRUIT_SEED = new ItemBombFruitSeed("wind_fruit_seed", 1).setCreativeTab(ModCreativeTab.ITEM);
	public static final Item LIGHTNING_COW_HORN = new ItemLightningCowHorn("lightning_cow_horn").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item ELEPHANT_TEETH = new ItemBase("elephant_teeth").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item TOPAZ = new ItemBase("topaz").setCreativeTab(ModCreativeTab.ITEM);
	public static final Item BLOODY_MEET = new ItemBloodyMeet("bloody_meet").setCreativeTab(ModCreativeTab.ITEM);


	//护甲
	public static final Item REN_YU_TOU = new ItemRenYuTou("ren_yu_tou", ARMOR_MATERIAL_REN_YU,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item REN_YU_JIA = new ItemRenYuJia("ren_yu_jia", ARMOR_MATERIAL_REN_YU,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item REN_YU_TUI = new ItemRenYuTui("ren_yu_tui", ARMOR_MATERIAL_REN_YU,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item REN_YU_XIE = new ItemRenYuXie("ren_yu_xie", ARMOR_MATERIAL_REN_YU,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item XIE_YI_TOU = new ItemXieYi("xie_yi_tou", ARMOR_MATERIAL_XIE_YI,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item XIE_YI_JIA = new ItemXieYi("xie_yi_jia", ARMOR_MATERIAL_XIE_YI,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item XIE_YI_TUI = new ItemXieYi("xie_yi_tui", ARMOR_MATERIAL_XIE_YI,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item XIE_YI_XIE = new ItemXieYi("xie_yi_xie", ARMOR_MATERIAL_XIE_YI,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item YANG_QI_BEI_BAO = new ItemYangQiBeiBao("yang_qi_bei_bao",ARMOR_MATERIAL_SEA,6,EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item YE_SHI_YAN_JING = new ItemYangQiBeiBao("ye_shi_yan_jing",ARMOR_MATERIAL_SEA,6,EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item TUI_JIN_QI = new ItemYangQiBeiBao("tui_jin_qi",ARMOR_MATERIAL_SEA,6,EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item ZHAN_NU_FU_MIAN_LONG = new ItemZhanNuMianLong("zhan_nu_mian_long",ARMOR_MATERIAL_ZHAN_NU,6,EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item ZHEN_QI_TOU = new ItemZhenQi("zhen_qi_tou", ARMOR_MATERIAL_ZHEN_QI,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item ZHEN_QI_JIA = new ItemZhenQi("zhen_qi_jia", ARMOR_MATERIAL_ZHEN_QI,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item ZHEN_QI_TUI = new ItemZhenQi("zhen_qi_tui", ARMOR_MATERIAL_ZHEN_QI,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item ZHEN_QI_XIE = new ItemZhenQi("zhen_qi_xie", ARMOR_MATERIAL_ZHEN_QI,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item HU_DUN_JIA = new ItemHuDun("hu_dun_jia", ARMOR_MATERIAL_ZHAN_NU,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item PEN_QI_XIE = new ItemPenQi("pen_qi_xie",ARMOR_MATERIAL_ZHAN_NU,6,EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item HUO_LONG_TOU = new ItemHuoLong("huo_long_tou", ARMOR_MATERIAL_FIRE_DRAGON,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item HUO_LONG_JIA = new ItemHuoLong("huo_long_jia", ARMOR_MATERIAL_FIRE_DRAGON,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item HUO_LONG_TUI = new ItemHuoLong("huo_long_tui", ARMOR_MATERIAL_FIRE_DRAGON,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item HUO_LONG_XIE = new ItemHuoLong("huo_long_xie", ARMOR_MATERIAL_FIRE_DRAGON,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item SA_HEAD = new ItemSaArmor("sa_head", ARMOR_MATERIAL_SA,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item SA_CHEST = new ItemSaArmor("sa_chest", ARMOR_MATERIAL_SA,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item SA_LEG = new ItemSaArmor("sa_leg", ARMOR_MATERIAL_SA,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item SA_BOOT = new ItemSaArmor("sa_boot", ARMOR_MATERIAL_SA,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIFU_HEAD = new ItemLiFu("lifu_head", ARMOR_MATERIAL_LIFU,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIFU_CHEST = new ItemLiFu("lifu_chest", ARMOR_MATERIAL_LIFU,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIFU_BOOT = new ItemLiFu("lifu_boot", ARMOR_MATERIAL_LIFU,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIFU_LEG = new ItemLiFu("lifu_leg", ARMOR_MATERIAL_LIFU,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item DONGLIJIA_HEAD = new ItemDongLiJia("donglijia_head", ARMOR_MATERIAL_DONGLIJIA,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item DONGLIJIA_CHEST = new ItemDongLiJia("donglijia_chest", ARMOR_MATERIAL_DONGLIJIA,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item DONGLIJIA_BOOT = new ItemDongLiJia("donglijia_boot", ARMOR_MATERIAL_DONGLIJIA,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item AAS = new ItemAAS("aas", ARMOR_MATERIAL_AAS,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAIJIN_HEAD = new ItemBaiJin("baijin_head", ARMOR_MATERIAL_BAIJIN1,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAIJIN_CHEST = new ItemBaiJin("baijin_chest", ARMOR_MATERIAL_BAIJIN1,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAIJIN_BOOT = new ItemBaiJin("baijin_boot", ARMOR_MATERIAL_BAIJIN2,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAIJIN_LEG = new ItemBaiJin("baijin_leg", ARMOR_MATERIAL_BAIJIN1,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item POSION_BOOT = new ItemPoisonBoot("poison_boot", ARMOR_MATERIAL_JCSL,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BIRD_CHEST = new ItemBirdChest("bird_chest", ARMOR_MATERIAL_BIRD,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item CRYSTAL_LEGGING = new ItemCrystalLegging("crystal_legging", ARMOR_MATERIAL_JCSL,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item COLOR_FEATHER_HEAD = new ItemLiFu("color_feather_head", ARMOR_MATERIAL_COLOR_FEATHER,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item COLOR_FEATHER_CHEST = new ItemLiFu("color_feather_chest", ARMOR_MATERIAL_COLOR_FEATHER,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item COLOR_FEATHER_BOOT = new ItemLiFu("color_feather_boot", ARMOR_MATERIAL_COLOR_FEATHER,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item COLOR_FEATHER_LEG = new ItemLiFu("color_feather_leg", ARMOR_MATERIAL_COLOR_FEATHER,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIGHTNING_COW_HEAD = new ItemLightningCow("lightning_cow_head", ARMOR_MATERIAL_LIGHTNING_COW,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIGHTNING_COW_CHEST = new ItemLightningCow("lightning_cow_chest", ARMOR_MATERIAL_LIGHTNING_COW,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIGHTNING_COW_BOOT = new ItemLightningCow("lightning_cow_boot", ARMOR_MATERIAL_LIGHTNING_COW,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item LIGHTNING_COW_LEG = new ItemLightningCow("lightning_cow_leg", ARMOR_MATERIAL_LIGHTNING_COW,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item CHINESE_ARMOR_HEAD = new ItemLightningCow("chinese_armor_head", ARMOR_MATERIAL_CHINESE_ARMOR,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item CHINESE_ARMOR_CHEST = new ItemLightningCow("chinese_armor_chest", ARMOR_MATERIAL_CHINESE_ARMOR,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item CHINESE_ARMOR_BOOT = new ItemLightningCow("chinese_armor_boot", ARMOR_MATERIAL_CHINESE_ARMOR,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAT_HEAD = new ItemArmorBase("bat_head", ARMOR_MATERIAL_BAT,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAT_CHEST = new ItemArmorBase("bat_chest", ARMOR_MATERIAL_BAT,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAT_BOOT = new ItemArmorBase("bat_boot", ARMOR_MATERIAL_BAT,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BAT_LEG = new ItemArmorBase("bat_leg", ARMOR_MATERIAL_BAT,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BLOODY_HEAD = new ItemBloodyArmor("bloody_head", ARMOR_MATERIAL_BLOODY,6, EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BLOODY_CHEST = new ItemBloodyArmor("bloody_chest", ARMOR_MATERIAL_BLOODY,6, EntityEquipmentSlot.CHEST).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BLOODY_LEG = new ItemBloodyArmor("bloody_leg", ARMOR_MATERIAL_BLOODY1,6, EntityEquipmentSlot.LEGS).setCreativeTab(ModCreativeTab.ARMOR);
	public static final Item BLOODY_BOOT = new ItemBloodyArmor("bloody_boot", ARMOR_MATERIAL_BLOODY1,6, EntityEquipmentSlot.FEET).setCreativeTab(ModCreativeTab.ARMOR);


	//创造模式物品
	public static final Item TEST = new ItemBase("test").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item BIAO_JI = new ItemBiaoJi("biao_ji",ARMOR_MATERIAL_BIAO_JI,6,EntityEquipmentSlot.HEAD).setCreativeTab(ModCreativeTab.TOOL);
	public static final Item TE_XIAO = new ItemTeXiao("te_xiao").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item BI_HUA = new ItemBiHua("bi_hua").setCreativeTab(ModCreativeTab.TOOL);
	public static final Item CRYSTAL_SHIELD_CREATIVE = new ItemCrystalShieldCreative("crystal_shield_creative").setCreativeTab(ModCreativeTab.WEAPON);
	public static final Item CREATIVE_MANA_COLLECTOR = new MoLiJuJiQi("creative_mana_collector",1000,100000).setCreativeTab(ModCreativeTab.WEAPON);


	//弹幕
	public static final Item PROJECT1 = new com.somebody.idlframewok.item.ItemBase("project1").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item ZHA_DAN = new com.somebody.idlframewok.item.ItemBase("zha_dan").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item FEI_DAN = new com.somebody.idlframewok.item.ItemBase("fei_dan").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item JI_GUANG = new com.somebody.idlframewok.item.ItemBase("ji_guang").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item FEI_DAO = new com.somebody.idlframewok.item.ItemBase("fei_dao").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item FU = new com.somebody.idlframewok.item.ItemBase("fu").setCreativeTab(ModCreativeTab.PROJECT);
	public static final Item GOLDEN_FEATHER = new com.somebody.idlframewok.item.ItemBase("golden_feather").setCreativeTab(ModCreativeTab.PROJECT);

	//熏香
	public static final Item QU_SAN_XIANG = new ItemXunXiang("qu_san_xiang",0,3600,new PotionEffect(ModPotions.QU_SAN_XIANG, 20, 0)).setCreativeTab(ModCreativeTab.XUN_XIANG);
	public static final Item MIN_XIANG_XIANG = new ItemXunXiang("min_xiang_xiang",1,3600,new PotionEffect(ModPotions.MIN_XIANG_XIANG, 20, 0)).setCreativeTab(ModCreativeTab.XUN_XIANG);
	public static final Item XIU_BAI_XIANG = new ItemXunXiang("xiu_bai_xiang",2,3600,new PotionEffect(ModPotions.XIU_BAI_XIANG, 20, 0)).setCreativeTab(ModCreativeTab.XUN_XIANG);
	public static final Item JIE_YAN_XIANG = new ItemXunXiang("jie_yan_xiang",3,3600,new PotionEffect(ModPotions.JIE_YAN_XIANG, 20, 0)).setCreativeTab(ModCreativeTab.XUN_XIANG);
	public static final Item JI_PO_XIANG = new ItemXunXiang("ji_po_xiang",4,3600,new PotionEffect(MobEffects.HASTE, 20, 1)).setCreativeTab(ModCreativeTab.XUN_XIANG);
	public static final Item JI_FENG_XIANG = new ItemXunXiang("ji_feng_xiang",5,3600,new PotionEffect(MobEffects.SPEED, 20, 0)).setCreativeTab(ModCreativeTab.XUN_XIANG);

	//香炉
	public static final Item HUANG_JIN_XIANG_LU = new ItemXiangLu("huang_jin_xiang_lu",new int[] {0,1,2,4,5}).setCreativeTab(ModCreativeTab.XUN_XIANG);
	public static final Item BAI_YIN_XIANG_LU = new ItemXiangLu("bai_yin_xiang_lu",new int[] {2,3,5}).setCreativeTab(ModCreativeTab.XUN_XIANG);
}
