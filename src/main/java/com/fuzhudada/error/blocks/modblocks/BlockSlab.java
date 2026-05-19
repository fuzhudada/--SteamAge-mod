package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public abstract class BlockSlab extends net.minecraft.block.BlockSlab {
    public static final PropertyEnum<BlockSlab.Variant> VARIANT = PropertyEnum.create("variant", BlockSlab.Variant.class);
    public BlockSlab(Material material,SoundType soundType){
        super(material,MapColor.STONE);
        IBlockState iblockstate = this.blockState.getBaseState();
        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, net.minecraft.block.BlockSlab.EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(iblockstate.withProperty(VARIANT, BlockSlab.Variant.DEFAULT));
        //this.translucent=true;
    }

//    @SideOnly(Side.CLIENT)
//    public boolean isTranslucent(IBlockState state)
//    {
//        return true;
//    }

    public int getLightOpacity(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos)
    {
        return 0;
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune)
    {
        if(isDouble()){
            return Objects.requireNonNull(Item.getByNameOrId("error:"+getUnlocalizedName().substring(5).replace('d', 'h')));
        }else{
            return Item.getItemFromBlock(this);
        }
    }

    @Nonnull
    public ItemStack getItem(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        if(isDouble()){
            return new ItemStack(Objects.requireNonNull(Item.getByNameOrId("error:" + getUnlocalizedName().substring(5).replace('d', 'h'))));
        }else{
            return new ItemStack(this);
        }
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockSlab.Variant.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? net.minecraft.block.BlockSlab.EnumBlockHalf.BOTTOM : net.minecraft.block.BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    public int getMetaFromState(@Nonnull IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == net.minecraft.block.BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    @Nonnull
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    @Nonnull
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    @Nonnull
    public Comparable<?> getTypeForItem(@Nonnull ItemStack stack)
    {
        return BlockSlab.Variant.DEFAULT;
    }

    public static class Double extends BlockSlab
    {
        public Double(String name,Material material,SoundType soundType){
            super(material,soundType);

            setUnlocalizedName(name);
            setRegistryName(name);

            ModBlocks.BLOCKS.add(this);
        }
        public boolean isDouble()
        {
            return true;
        }
    }

    public static class Half extends BlockSlab implements com.somebody.idlframewok.util.IHasModel
    {
        public Half(String name, Material material, SoundType soundType, Block doubleSlab){
            super(material,soundType);

            setUnlocalizedName(name);
            setRegistryName(name);

            ModBlocks.BLOCKS.add(this);
            ModItems.ITEMS.add(new ItemSlab(this,this,(net.minecraft.block.BlockSlab) doubleSlab).setRegistryName(this.getRegistryName()));
        }

        public boolean isDouble()
        {
            return false;
        }

        @Override
        public void registerModels() {
            com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
        }
    }

    public enum Variant implements IStringSerializable
    {
        DEFAULT;

        @Nonnull
        public String getName()
        {
            return "default";
        }
    }
}
