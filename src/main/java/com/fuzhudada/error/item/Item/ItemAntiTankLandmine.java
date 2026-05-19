package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.entity.model.EntityAntiTankLandmine;
import com.fuzhudada.error.entity.model.EntityBomb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAntiTankLandmine extends com.somebody.idlframewok.item.ItemBase {
    public ItemAntiTankLandmine(String name) {
        super(name);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        EntityAntiTankLandmine bomb=new EntityAntiTankLandmine(worldIn,player);
        bomb.setPosition(pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5);
        if(!worldIn.isRemote){
            worldIn.spawnEntity(bomb);
        }
        player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 60);
        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
