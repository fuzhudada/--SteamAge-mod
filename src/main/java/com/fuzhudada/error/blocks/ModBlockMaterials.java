package com.fuzhudada.error.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ModBlockMaterials extends Material {
    public ModBlockMaterials(MapColor color) {
        super(color);
    }

    public boolean blocksLight()
    {
        return false;
    }
}