package com.fuzhudada.error.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.*;

public class BiomeWindIsland extends BiomeJungle {
    public BiomeWindIsland(String name) {
        super(false,new Biome.BiomeProperties(name));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        //return new Color(12, 16, 239).getRGB();
        return new Color(50, 50, 50).getRGB();
    }

    public int getWaterColorMultiplier()
    {
        return new Color(102, 153, 204).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(@Nonnull BlockPos pos)
    {
        //return new Color(102, 153, 204).getRGB();
        return new Color(0, 170, 255).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(0, 170, 255).getRGB();
    }
}
