package com.fuzhudada.error.blocks.tileEntity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCenser.class, new ResourceLocation("error:censer"));
    }
}
