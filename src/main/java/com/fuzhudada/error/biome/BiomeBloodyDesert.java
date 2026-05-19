package com.fuzhudada.error.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.*;

public class BiomeBloodyDesert extends BiomeDesert {
    public BiomeBloodyDesert(String name) {
        super(new Biome.BiomeProperties(name));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        return new Color(239, 12, 12).getRGB();
    }

    public int getWaterColorMultiplier()
    {
        return new Color(100, 0, 0).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(186, 12, 47).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(186, 12, 47).getRGB();
    }
}
