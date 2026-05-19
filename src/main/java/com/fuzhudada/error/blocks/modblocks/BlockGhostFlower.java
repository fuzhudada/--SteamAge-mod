package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BlockGhostFlower extends BlockBush implements com.somebody.idlframewok.util.IHasModel {
    private static final AxisAlignedBB GHOST_FLOWER_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    public BlockGhostFlower(String name){
        super(Material.PLANTS, MapColor.GRAY);
        this.setSoundType(SoundType.PLANT);
        setUnlocalizedName(name);
        setRegistryName(name);

        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        ModBlocks.BLOCKS.add(this);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        if(entityIn instanceof EntityLivingBase) {
            EntityLivingBase e=(EntityLivingBase) entityIn;
            if(!e.isPotionActive(MobEffects.BLINDNESS) && e.getHeldItemMainhand().getItem()!=ModItems.HUANG_JIN_SHUI_HU && e.getHeldItemOffhand().getItem()!=ModItems.HUANG_JIN_SHUI_HU){
                e.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,40,0));
                worldIn.playSound(null,pos,SoundEvents.ENTITY_GHAST_HURT, SoundCategory.BLOCKS,1,1);
            }
        }
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nonnull
    public ItemStack getPickBlock(@Nonnull IBlockState state, @Nonnull RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player)
    {
        return new ItemStack(ModBlocks.GHOST_FLOWER);
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState p_getBoundingBox_1_, @Nonnull IBlockAccess p_getBoundingBox_2_, @Nonnull BlockPos p_getBoundingBox_3_) {
        return GHOST_FLOWER_AABB;
    }
}
