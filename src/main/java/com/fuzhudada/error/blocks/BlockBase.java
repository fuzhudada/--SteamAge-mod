package com.fuzhudada.error.blocks;

import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.IdlFramework;
import com.somebody.idlframewok.init.ModCreativeTab;
import com.somebody.idlframewok.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Random;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material, SoundType type)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(type);
		setCreativeTab(ModCreativeTab.ITEM);
		
		com.fuzhudada.error.blocks.ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

		setHardness(5.0F);
		//setResistance(1000.0F);
		//setHarvestLevel("pickaxe", 1);
		//setLightLevel(1f);
		setLightOpacity(1);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return super.quantityDropped(rand);
	}
	
	@Override
	public void registerModels() {
		IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
