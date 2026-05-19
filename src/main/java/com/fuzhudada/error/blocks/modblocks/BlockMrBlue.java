package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMrBlue extends BlockBase {
    public BlockMrBlue(String name, Material material, SoundType type) {
        super(name, material, type);
        this.lightOpacity=7;
        this.lightValue=14;
    }
}
