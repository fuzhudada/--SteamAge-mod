package com.fuzhudada.error.util;

import com.fuzhudada.error.util.sound.ModSoundEvent;
import com.somebody.idlframewok.IdlFramework;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModSoundHandler {
    //To add a sound, remember assets.idlframewok.sounds.json
    public static final List<ModSoundEvent> SOUNDS = new ArrayList<>();

    public static SoundEvent WIND = new ModSoundEvent("item.wand.wind");
    public static SoundEvent GUN1 = new ModSoundEvent("item.gun.gun1");
    public static SoundEvent GUN2 = new ModSoundEvent("item.gun.gun2");
    public static SoundEvent GUN3 = new ModSoundEvent("item.gun.gun3");
    public static SoundEvent TANK_FIRE = new ModSoundEvent("entity.tank.fire");
    public static SoundEvent TANK_MOVE = new ModSoundEvent("entity.tank.move");
    public static SoundEvent TANK_HURT = new ModSoundEvent("entity.tank.hurt");
    public static SoundEvent AAS = new ModSoundEvent("item.armor.aas");
    public static SoundEvent ASSASSIN = new ModSoundEvent("item.armor.assassin");
    public static SoundEvent HUANG_JIN_BAN_HU = new ModSoundEvent("item.sword.huang_jin_ban_hu");
    public static SoundEvent GONG_ZHENG_ZHI_CI = new ModSoundEvent("item.sword.gong_zheng_zhi_ci");
    public static SoundEvent GONG_ZHENG_ZHI_CI1 = new ModSoundEvent("item.sword.gong_zheng_zhi_ci1");
    public static SoundEvent GUN4 = new ModSoundEvent("item.gun.gun4");
    public static SoundEvent GUN5 = new ModSoundEvent("item.gun.gun5");
    public static SoundEvent SWORD_BLOCK = new ModSoundEvent("item.sword.sword_block");
    public static SoundEvent GUN6 = new ModSoundEvent("item.gun.gun6");
    public static SoundEvent SAND_GIANT_LIGHT = new ModSoundEvent("entity.sand_giant.light");
    public static SoundEvent SAND_GIANT_HURT = new ModSoundEvent("entity.sand_giant.hurt");
    public static SoundEvent SAND_GIANT_DEATH = new ModSoundEvent("entity.sand_giant.death");
    public static SoundEvent SAND_GIANT_WALK = new ModSoundEvent("entity.sand_giant.walk");
    public static SoundEvent STEAM = new ModSoundEvent("item.sword.steam");
    public static SoundEvent SHOU_XUAN_ZHE = new ModSoundEvent("item.sword.shou_xuan_zhe");
    public static SoundEvent BIRD = new ModSoundEvent("item.armor.bird");
    public static SoundEvent GUN_BULLET = new ModSoundEvent("item.gun.gun_bullet");
    public static SoundEvent GUN_FIRE_BULLET = new ModSoundEvent("item.gun.gun_fire_bullet");
    public static SoundEvent WAND_POISON = new ModSoundEvent("item.wand.poison_wand");
    public static SoundEvent CRYSTAL_SHIELD = new ModSoundEvent("item.shield.crystal_shield");
    public static SoundEvent WU_ZUO_BIAN_TA = new ModSoundEvent("item.sword.wu_zuo_bian_ta");
    public static SoundEvent STEAM_HAMMER = new ModSoundEvent("item.sword.steam_hammer");
    public static SoundEvent NATURAL_SOUL = new ModSoundEvent("item.gun.natural_soul");
    public static SoundEvent STORM_SWORD = new ModSoundEvent("item.sword.storm_sword");
    public static SoundEvent ELECTRICITY = new ModSoundEvent("item.wand.electricity");
    public static SoundEvent MOOSE_HURT = new ModSoundEvent("entity.moose.hurt");
    public static SoundEvent MOOSE_DEATH = new ModSoundEvent("entity.moose.death");
    public static SoundEvent MOOSE_AMBIENT = new ModSoundEvent("entity.moose.ambient");
    public static SoundEvent MOOSE_HORN = new ModSoundEvent("item.wand.moose_horn");
    public static SoundEvent BAT = new ModSoundEvent("item.armor.bat");
    public static SoundEvent ELEPHANT_HURT = new ModSoundEvent("entity.elephant.hurt");
    public static SoundEvent ELEPHANT_DEATH = new ModSoundEvent("entity.elephant.death");
    public static SoundEvent ELEPHANT_ROAR = new ModSoundEvent("entity.elephant.roar");
    public static SoundEvent ELEPHANT_STEP = new ModSoundEvent("entity.elephant.step");
    public static SoundEvent JIE_LV_DUN = new ModSoundEvent("item.shield.jie_lv_dun");
    public static SoundEvent FIRE_HORSE = new ModSoundEvent("entity.fire_horse.run");
//    public static SoundEvent SOUND_2 = new ModSoundEvent("entity.moroon.hurt");

    public static void soundRegister()
    {
        IdlFramework.Log("Registering %s sounds.", SOUNDS.size());
        ForgeRegistries.SOUND_EVENTS.registerAll(ModSoundHandler.SOUNDS.toArray(new SoundEvent[0]));
        IdlFramework.Log("Registered %s sounds.", SOUNDS.size());
    }

}
