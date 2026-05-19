package com.fuzhudada.error.blocks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluid {
    public static final Fluid MANA = new Fluid("mana", new ResourceLocation("error:fluid/mana_still"), new ResourceLocation("error:fluid/mana_flow")).setLuminosity(10);
    public static final Fluid GOLDEN = new Fluid("golden", new ResourceLocation("error:fluid/golden_still"), new ResourceLocation("error:fluid/golden_flow")).setLuminosity(20);
    public static final Fluid BLOOD = new Fluid("blood", new ResourceLocation("error:fluid/blood_still"), new ResourceLocation("error:fluid/blood_flow")).setViscosity(6000);

    public static void registerFluids() {
        registerFluid(MANA);
        registerFluid(GOLDEN);
        registerFluid(BLOOD);
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
