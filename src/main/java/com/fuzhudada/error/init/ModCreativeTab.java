package com.somebody.idlframewok.init;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab {
	public static final CreativeTabs WEAPON = new CreativeTabs(CreativeTabs.getNextID(), "weaponTab")
	{
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.REN_YU_JIAN);
        }
    };
    public static final CreativeTabs ARMOR = new CreativeTabs(CreativeTabs.getNextID(), "armorTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.REN_YU_JIA);
        }
    };
    public static final CreativeTabs FOOD = new CreativeTabs(CreativeTabs.getNextID(), "foodTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.CHOCOLATE);
        }
    };
    public static final CreativeTabs TOOL = new CreativeTabs(CreativeTabs.getNextID(), "toolTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.STEAM);
        }
    };
    public static final CreativeTabs ITEM = new CreativeTabs(CreativeTabs.getNextID(), "itemTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.HUO_OU);
        }
    };
    public static final CreativeTabs PROJECT = new CreativeTabs(CreativeTabs.getNextID(), "projectTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.PROJECT1);
        }
    };
    public static final CreativeTabs BLOCK = new CreativeTabs(CreativeTabs.getNextID(), "blockTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.SHAN_HU1);
        }
    };
    public static final CreativeTabs XUN_XIANG = new CreativeTabs(CreativeTabs.getNextID(), "XunXiangTab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.QU_SAN_XIANG);
        }
    };
    public static final CreativeTabs MACHINE = new CreativeTabs(CreativeTabs.getNextID(), "Machine")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.BREWING_MACHINE);
        }
    };
    public static final CreativeTabs DRINK = new CreativeTabs(CreativeTabs.getNextID(), "Drink")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.FU_TE_JIA);
        }
    };
    public static final CreativeTabs NEW_VERSION_BLOCK = new CreativeTabs(CreativeTabs.getNextID(), "NewVersionBlock")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.STAIR7);
        }
    };
}
