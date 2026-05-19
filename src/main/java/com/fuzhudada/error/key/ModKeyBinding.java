package com.fuzhudada.error.key;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModKeyBinding extends KeyBinding {
    public ModKeyBinding(String description, IKeyConflictContext keyConflictContext, KeyModifier keyModifier, int keyCode, String category) {
        super(String.format("key.%s.%s", com.somebody.idlframewok.IdlFramework.MODID, description), keyConflictContext, keyModifier, keyCode, category);
        com.somebody.idlframewok.proxy.ClientProxy.KEY_BINDINGS.add(this);
    }
}
