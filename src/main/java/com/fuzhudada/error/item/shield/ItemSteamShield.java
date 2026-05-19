package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.item.sword.ISteamWeapon;

public class ItemSteamShield extends ItemShieldBase implements ISteamWeapon {
    public ItemSteamShield(String name, int damage) {
        super(name, damage);
    }

    @Override
    public float maxItemSteam() {
        return 100;
    }
}
