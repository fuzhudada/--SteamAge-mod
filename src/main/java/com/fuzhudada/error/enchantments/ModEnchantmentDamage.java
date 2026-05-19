package com.fuzhudada.error.enchantments;

import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;

import javax.annotation.Nonnull;

public class ModEnchantmentDamage extends EnchantmentDamage {
    String name;
    int maxLevel;

    public ModEnchantmentDamage(String name , Rarity rarityIn , int damageTypeIn , EntityEquipmentSlot... slots) {
        super(rarityIn , damageTypeIn , slots);
        this.name=name;
        setRegistryName(com.somebody.idlframewok.IdlFramework.MODID, name);
        setName(name);
        ModEnchantmentInit.ENCHANTMENT_LIST.add(this);
    }

    public float calcDamageByCreature(int level, @Nonnull EnumCreatureAttribute creatureType)
    {
        if (this.damageType == 3 && creatureType.name().equals("machinery"))
        {
            return 6*level;
        }
        else if (this.damageType == 4 && creatureType != EnumCreatureAttribute.UNDEAD && creatureType != EnumCreatureAttribute.ARTHROPOD && !creatureType.name().equals("machinery"))
        {
            return 6*level;
        }
        else if (this.damageType == 5 && creatureType == EnumCreatureAttribute.UNDEAD)
        {
            return 6*level;
        }
        else if (this.damageType == 6 && creatureType == EnumCreatureAttribute.ARTHROPOD)
        {
            return 6*level;
        }
        else if (this.damageType == 7 && creatureType != EnumCreatureAttribute.UNDEAD)
        {
            return 6*level;
        }
        return 0;
    }

    @Nonnull
    public String getName()
    {
        return "enchantment." + this.name;
    }

    public ModEnchantmentDamage setMaxLevel(int maxLevel)
    {
        this.maxLevel = maxLevel;
        return this;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }
}
