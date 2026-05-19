package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.entity.model.EntityBomb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBomb extends com.somebody.idlframewok.item.ItemBase {
    public int type;
    public float damage;
    public ItemBomb(String name,int type) {
        super(name);
        this.type=type;
        this.damage=185;
    }

    public ItemBomb(String name,int type,float damage) {
        super(name);
        this.type=type;
        this.damage=damage;
    }

    @Nonnull
    public EnumActionResult onItemUse(@Nonnull EntityPlayer player, @Nonnull World worldIn, BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        EntityBomb bomb=new EntityBomb(worldIn,player,damage);
        bomb.setType(type);
        bomb.setPosition(pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5);
        if(!worldIn.isRemote){
            worldIn.spawnEntity(bomb);
        }
        player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 60);
        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
