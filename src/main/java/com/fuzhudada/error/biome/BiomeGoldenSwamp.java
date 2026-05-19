package com.fuzhudada.error.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.*;

public class BiomeGoldenSwamp extends BiomeSwamp {
    public BiomeGoldenSwamp(String name) {
        super(new Biome.BiomeProperties(name));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        return new Color(231, 189, 17).getRGB();
    }

    public int getWaterColorMultiplier()
    {
        return new Color(231, 189, 17).getRGB();
        //return new Color(100, 255, 17).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(231, 189, 17).getRGB();
        //return new Color(100, 255, 17).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(231, 189, 17).getRGB();
        //return new Color(255, 255, 17).getRGB();
    }
}
