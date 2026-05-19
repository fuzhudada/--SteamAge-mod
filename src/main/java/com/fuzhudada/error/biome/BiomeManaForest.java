package com.fuzhudada.error.biome;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.*;

public class BiomeManaForest extends BiomeForest {
    public BiomeManaForest(String name) {
        super(Type.FLOWER,new Biome.BiomeProperties(name));
        ModBiomeHandler.BIOMES.add(this);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        //return new Color(0, 244 , 255).getRGB();
        return new Color(135, 206 , 255).getRGB();
    }

//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(@Nonnull BlockPos pos)
//    {
//        return new Color((int)(128+64*Math.cos(((pos.getX()+pos.getY())%32)*Math.PI/16)), (int)(128+64*Math.cos(((pos.getY()+pos.getZ())%32)*Math.PI/16+Math.PI*2/3)), (int)(128+64*Math.cos(((pos.getX()+pos.getZ())%32)*Math.PI/16-Math.PI*2/3))).getRGB();
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getFoliageColorAtPos(@Nonnull BlockPos pos)
//    {
//        return new Color((int)(128+64*Math.cos(((pos.getX()+pos.getY())%32)*Math.PI/16)), (int)(128+64*Math.cos(((pos.getY()+pos.getZ())%32)*Math.PI/16+Math.PI*2/3)), (int)(128+64*Math.cos(((pos.getX()+pos.getZ())%32)*Math.PI/16-Math.PI*2/3))).getRGB();
//    }

        @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(135, 206 , 255).getRGB();
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(@Nonnull BlockPos pos)
    {
        return new Color(135, 206 , 255).getRGB();
    }

    public int getWaterColorMultiplier()
    {
        return new Color(102, 153, 204).getRGB();
    }

}
