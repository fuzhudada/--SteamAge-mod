package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockGlowingCanRotate extends BlockCanRotate{
    public BlockGlowingCanRotate(String name , Material material , SoundType type) {
        super(name , material , type);
        this.lightOpacity=7;
        this.lightValue=14;
    }
}
