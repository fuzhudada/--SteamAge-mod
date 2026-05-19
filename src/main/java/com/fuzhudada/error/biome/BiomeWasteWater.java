package com.fuzhudada.error.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;

import java.awt.*;

public class BiomeWasteWater extends BiomeOcean {
    public BiomeWasteWater(String name) {
        super(new Biome.BiomeProperties(name));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName(name);
    }

    public int getWaterColorMultiplier()
    {
        return new Color(7 , 7 , 7).getRGB();
    }
}
