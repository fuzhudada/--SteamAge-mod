package com.fuzhudada.error;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import javax.annotation.Nullable;

public class ModDamageSource {
    public enum EnumModDamageSource{
        ManaDamage,ArmorBrokenDamage,FireBulletDamage,RpgDamage,ShotGunDamage,Normal
    }

    public static DamageSource getDamageSource(EnumModDamageSource source,Entity attacker,Entity indirectEntityIn){
        if(source==EnumModDamageSource.ManaDamage){
            return causeManaDamage(indirectEntityIn,attacker);
        }else if(source==EnumModDamageSource.ArmorBrokenDamage){
            return causeArmorBrokenDamage(indirectEntityIn,attacker);
        }else if(source==EnumModDamageSource.FireBulletDamage){
            return causeFireBulletDamage(indirectEntityIn,attacker);
        }else if(source==EnumModDamageSource.RpgDamage){
            return causeRpgDamage(indirectEntityIn,attacker);
        }else if(source==EnumModDamageSource.ShotGunDamage) {
            return causeShotGunDamage(indirectEntityIn,attacker );
        }else if(source==EnumModDamageSource.Normal){
            return DamageSource.causeThrownDamage(indirectEntityIn,attacker);
        }
        return null;
    }

    public static DamageSource causeManaDamage(Entity source)
    {
        return new EntityDamageSource("mana", source);
    }

    public static DamageSource causeManaDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("mana", source, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeArmorBrokenDamage(Entity source)
    {
        return new EntityDamageSource("armorBrokenDamage", source);
    }

    public static DamageSource causeArmorBrokenDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("armorBrokenDamage", source, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeSteamDamage(Entity source)
    {
        return new EntityDamageSource("steam", source);
    }

    public static DamageSource causeSteamDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("steam", source, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeFireBulletDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("fireBullet", source, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeRpgDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("rpg", source, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeShotGunDamage(Entity source)
    {
        return new EntityDamageSource("shotGun", source);
    }

    public static DamageSource causeShotGunDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("shotGun", source, indirectEntityIn)).setProjectile();
    }

    public static DamageSource causeAssassinDamage(Entity source)
    {
        return new EntityDamageSource("assassin", source);
    }

    public static DamageSource causeElectricalDamage(Entity source)
    {
        return new EntityDamageSource("electrical", source).setDamageBypassesArmor();
    }

    public static DamageSource causeCCBDamage(Entity source)
    {
        return new EntityDamageSource("ccb", source);
    }
}
