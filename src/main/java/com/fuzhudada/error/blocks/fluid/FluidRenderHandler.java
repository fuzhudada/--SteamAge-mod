package com.fuzhudada.error.blocks.fluid;

import com.fuzhudada.error.blocks.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class FluidRenderHandler {
    public static void registerCustomMeshesAndStates() {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.FLUID_MANA), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("error:mana", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(ModBlocks.FLUID_MANA, new StateMapperBase()
        {
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation("error:mana", "fluid");
            }
        });

        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.FLUID_GOLDEN), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("error:golden", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(ModBlocks.FLUID_GOLDEN, new StateMapperBase()
        {
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation("error:golden", "fluid");
            }
        });

        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.FLUID_BLOOD), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("error:blood", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(ModBlocks.FLUID_BLOOD, new StateMapperBase()
        {
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation("error:blood", "fluid");
            }
        });
    }
}
