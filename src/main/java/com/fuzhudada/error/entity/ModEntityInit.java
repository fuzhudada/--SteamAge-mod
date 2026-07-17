package com.somebody.idlframewok.entity;

import com.fuzhudada.error.entity.projectiles.EntityYunShi;
import com.fuzhudada.error.entity.model.*;
import com.fuzhudada.error.entity.projectiles.*;
import com.fuzhudada.error.entity.weapon.*;
import com.somebody.idlframewok.IdlFramework;
import com.somebody.idlframewok.util.Reference;
import its_meow.betteranimalsplus_sa.common.entity.EntityLammergeier;
import its_meow.betteranimalsplus_sa.common.entity.EntityMoose;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;
    public static void registerEntities()
    {
        //Examples
        RegisterEntityWithoutEgg("yun_shi", EntityYunShi.class);
        RegisterEntityWithoutEgg("zha_dan", EntityZhaDan.class);
        RegisterEntityWithoutEgg("stone", EntityStone.class);
        RegisterEntityWithoutEgg("huo_yan_zha_dan", EntityHuoYanZiDan.class);
        RegisterEntityWithoutEgg("po_jia_dan", EntityPoJiaDan.class);
        RegisterEntityWithoutEgg("shuang_zi", EntityShuangZi.class);
        registerEntity("shi_mo_xiang", EntityShiMoXiang.class);
        registerEntity("yu_deng", EntityYuDeng.class);
        registerEntity("da_pao", EntityDaPao.class);
        RegisterEntityWithoutEgg("gong_zheng_zhi_ci", EntityGongZhengZhiCi.class);
        RegisterEntityWithoutEgg("shou_xuan_zhe_zhi_bing", EntityShouXuanZheZhiBing.class);
        registerEntity("zheng_qi_che", EntityZhengQiChe.class);
        registerEntity("jian_zuo", EntityJianZuo.class);
        RegisterEntityWithoutEgg("nu_mu", EntityNuMu.class);
        registerEntity("fan_she_jing", EntityFanSheJing.class);
        RegisterEntityWithoutEgg("fei_dao", EntityFeiDao.class);
        RegisterEntityWithoutEgg("cui_du_dan", EntityCuiDuDan.class);
        RegisterEntityWithoutEgg("xian_dan", EntityXianDan.class);
        RegisterEntityWithoutEgg("di_ci", EntityDiCi.class);
        registerEntity("flower", EntityFlower.class);
        RegisterEntityWithoutEgg("bomb", EntityBomb.class);
        RegisterEntityWithoutEgg("wu_zuo_bian_ta", EntityWuZuoBianTa.class);
        RegisterEntityWithoutEgg("huo_long", EntityHuoLong.class);
        registerEntity("armor_stand_with_hand", EntityArmorStandWithHand.class);
        registerEntity("small_flower", EntitySmallFlower.class);
        RegisterEntityWithoutEgg("wind_wand", EntityWindWand.class);
        RegisterEntityWithoutEgg("wind", EntityWind.class);
        RegisterEntityWithoutEgg("dan_jia_qiang", EntityDanJiaQiang.class);
        RegisterEntityWithoutEgg("zhong_pao_dan", EntityZhongDanPao.class);
        RegisterEntityWithoutEgg("huang_jin_jian", EntityHuangJinJian.class);
        RegisterEntityWithoutEgg("te_xiao", EntityTeXiao.class);
        RegisterEntityWithoutEgg("bomb_fruit", EntityBombFruit.class);
        registerEntity("tank", EntityTank.class);
        RegisterEntityWithoutEgg("anti_tank_landmine", EntityAntiTankLandmine.class);
        RegisterEntityWithoutEgg("steam_age_projectiles", EntityGeneralProjectiles.class);
        RegisterEntityWithoutEgg("lie_feng_qi_qiang", EntityLieFengQiQiang.class);
        RegisterEntityWithoutEgg("pro_qi_shi_dao", EntityQiShiDao.class);
        registerEntity("gun_carrier", EntityGunCarrier.class);
        registerEntity("gun_carrier1", EntityGunCarrier1.class);
        registerEntity("steam_golem1", EntitySteamGolem1.class);
        registerEntity("steam_golem2", EntitySteamGolem2.class);
        registerEntity("steam_golem3", EntitySteamGolem3.class);
        RegisterEntityWithoutEgg("zhan_feng_da_jian", EntityZhanFengDaJian.class);
        RegisterEntityWithoutEgg("sand_cannon", EntitySandCannon.class);
        RegisterEntityWithoutEgg("poison", EntityPoison.class);
        RegisterEntityWithoutEgg("fu", EntityFu.class);
        RegisterEntityWithoutEgg("gatling", EntityGatling.class);
        registerEntity("sand_giant", EntitySandGiant.class);
        RegisterEntityWithoutEgg("bloody_wind", EntityBloodyWind.class);
        RegisterEntityWithoutEgg("mod_art", EntityModArt.class);
        registerEntity("arrow_pig", EntityArrowPig.class);
        registerEntity("lammergeier", EntityLammergeier.class,128);
        RegisterEntityWithoutEgg("golden_feather", EntityFeather.class);
        RegisterEntityWithoutEgg("crystal_shield", EntityCrystalShield.class);
        registerEntity("book", EntityBook.class);
        registerEntity("book_open", EntityBookOpen.class);
        registerEntity("book_close", EntityBookClose.class);
        RegisterEntityWithoutEgg("natural_soul", EntityNaturalSoul.class);
        RegisterEntityWithoutEgg("wind_charge", EntityWindCharge.class);
        RegisterEntityWithoutEgg("storm_sword", EntityStormSword.class);
        registerEntity("lightning_cow", EntityLightningCow.class);
        registerEntity("lightning_cow_horn", EntityLightningCowHorn.class);
        registerEntity("moose", EntityMoose.class);
        RegisterEntityWithoutEgg("white_snack_wand", EntityWhiteSnackWand.class);
        registerEntity("fire_lantern", EntityFireLantern.class);
        RegisterEntityWithoutEgg("mod_tipped_arrow", EntityModTippedArrow.class);
        registerEntity("elephant", EntityElephant.class);
        registerEntity("horse", EntityHorse.class);
        registerEntity("rider_elephant", EntityRiderElephant.class);
        registerEntity("fire_horse", EntityFireHorse.class);
        RegisterEntityWithoutEgg("winged_scepter", EntityWingedScepter.class);
        registerEntity("robot_killer", EntityRobotKiller.class);
        RegisterEntityWithoutEgg("robot_bullet", EntityRobotBullet.class);
        registerEntity("flag", EntityFlag.class);
//        registerEntity("moroon_tainter", EntityMoroonTainter.class,0xff00ff, 0x000033);
//        registerEntity("idealland_whitetower_core", EntityIDLWhiteTowerCore.class, ENTITY_NEXT_ID, 128, 0xeeee00, 0xffffff);

        //the bullet
        //registerEntity("bullet", EntityIdlProjectile.class);

        //Assign Dungeons
        //DungeonHooks.addDungeonMob(EntityList.getKey(EntityMoroonTainter.class), STANDARD_DUNGEON_MOB_RARITY);

        DataFixer datafixer = new DataFixer(1343);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, 0xff00ff, 0x000000);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity, int color1, int color2)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, color1, color2);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity,
                name,
                id,
                IdlFramework.instance,
                range,
                1,
                true,
                color1, color2
                );
        ENTITY_NEXT_ID++;
    }

    private  static  void RegisterEntityWithoutEgg(String name, Class<? extends Entity> entity){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity,
                name,
                ENTITY_NEXT_ID,
                IdlFramework.instance,
                50,
                1,
                true
        );
        ENTITY_NEXT_ID++;
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity,int range)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, range, 0xff00ff, 0x000000);
    }

    private  static  void RegisterEntityWithoutEgg(String name, Class<? extends Entity> entity,int range){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity,
                name,
                ENTITY_NEXT_ID,
                IdlFramework.instance,
                range,
                1,
                true
        );
        ENTITY_NEXT_ID++;
    }
}
