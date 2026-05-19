package com.fuzhudada.error.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

public class BiomeDarkForest extends BiomeForest {
    public BiomeDarkForest(String name) {
        super(Type.ROOFED,new Biome.BiomeProperties(name));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        return new Color(0, 0, 0).getRGB();
    }
}
