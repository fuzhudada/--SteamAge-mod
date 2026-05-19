package com.fuzhudada.error.biome;

import net.minecraft.world.biome.Biome;

public class BiomeNull extends Biome {
    public BiomeNull(int i) {
        super(new Biome.BiomeProperties("null"+i).setBaseHeight(-0.2F).setHeightVariation(0.1F));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName("null"+i);
    }
}
