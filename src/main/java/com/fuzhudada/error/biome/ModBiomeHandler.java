package com.fuzhudada.error.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBiomeHandler {
    public static final List<Biome> BIOMES = new ArrayList<>();
    static {
        for(int i=0;i<11;i++){
            BIOMES.add(new BiomeNull(i));
        }
    }

    public static BiomeDarkForest DARK_FOREST = new BiomeDarkForest("dark_forest");
    public static BiomeBloodyDesert BLOODY_DESERT = new BiomeBloodyDesert("bloody_desert");
    public static BiomeWindIsland WIND_ISLAND = new BiomeWindIsland("wind_island");
    public static BiomeGoldenSwamp GOLDEN_SWAMP = new BiomeGoldenSwamp("golden_swamp");
    public static BiomeManaForest MANA_FOREST = new BiomeManaForest("mana_forest");
    public static BiomeWasteWater WASTE_WATER = new BiomeWasteWater("waste_water");

    public static void biomeRegister()
    {
        com.somebody.idlframewok.IdlFramework.Log("Registering %s biome.", BIOMES.size());
        ForgeRegistries.BIOMES.registerAll(BIOMES.toArray(new Biome[0]));
        com.somebody.idlframewok.IdlFramework.Log("Registered %s biome.", BIOMES.size());
    }
}
