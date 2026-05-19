package com.fuzhudada.error.item.sword;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemChongJiChui extends com.somebody.idlframewok.item.ItemSwordBase implements ISteamWeapon{
    public ItemChongJiChui(String name, ToolMaterial material) {
        super(name, material);
        this.setMaxDamage(300);
        this.addPropertyOverride(new ResourceLocation("running"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isSprinting() && entityIn.getHeldItemMainhand() == stack && stack.getItemDamage()<stack.getMaxDamage() ? 1.0F : 0.0F;
            }
        });
    }

    public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        return true;
    }

    @Override
    public float maxItemSteam() {
        return 300;
    }

    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entityLiving)
    {
        return true;
    }
}
