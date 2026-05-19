package com.fuzhudada.error.item.shield;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSteam extends com.somebody.idlframewok.item.ItemSwordBase {
    public ItemSteam(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(!playerIn.world.isRemote){
            if(stack.getItemDamage()<5) {
            this.setDamage(stack, stack.getItemDamage() + 1);
            target.setFire(10);
        }
    }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
}

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!player.world.isRemote) {
            BlockPos P =this.rayTrace(worldIn,player,true).getBlockPos();
            if(worldIn.getBlockState(P).getMaterial()== Material.WATER){
                ItemStack I = (ItemStack) player.getHeldItemMainhand();
                I.setItemDamage(0);
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
