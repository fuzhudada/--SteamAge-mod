package com.fuzhudada.error.init;

import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModRecipes {
	
	
	public static void Init() {
		//Only smelting recipes
		GameRegistry.addSmelting(ModItems.SHENG_YOU_YU, new ItemStack(ModItems.SHU_YOU_YU), 1f);
		GameRegistry.addSmelting(ModItems.SHENG_XIE_ROU, new ItemStack(ModItems.SHU_XIE_ROU), 1f);
		GameRegistry.addSmelting(ModItems.SHENG_SHA_YU, new ItemStack(ModItems.SHU_SHA_YU), 1f);
		GameRegistry.addSmelting(ModItems.SHENG_HAI_TUN, new ItemStack(ModItems.SHU_HAI_TUN), 1f);
		GameRegistry.addSmelting(ModItems.SHENG_HAI_XIANG, new ItemStack(ModItems.SHU_HAI_XIANG), 1f);
		GameRegistry.addSmelting(ModItems.LIGHTNING_BEEF_RAW, new ItemStack(ModItems.LIGHTNING_BEEF_COOKED), 1f);
	}
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
		IForgeRegistry<IRecipe> r = evt.getRegistry();
		//Example
		//r.register(new GobletFill().setRegistryName(new ResourceLocation(Reference.MOD_ID, "goblet_fill")));
	}
}
