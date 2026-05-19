package com.fuzhudada.error.item.food;

import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBombFruit extends com.somebody.idlframewok.item.food.ItemFoodBase {
    public ItemBombFruit(String name, int amount, float saturation, boolean isWolfFood) {
        super(name, amount, saturation, isWolfFood,12);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
        {
            EntityHuoLong.spawnParticle(player.posX,player.posY,player.posZ,2,worldIn);

            List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, e -> e.getDistance(player)<8
                    && !(e instanceof EntityArmorStand));
            for (int i = 0; i <= G.size() - 1; i++) {
                G.get(i).motionY+=1;
                Vec3d vec3d=new Vec3d(G.get(i).posX-player.posX,0,G.get(i).posZ - player.posZ).normalize();
                if(G.get(i).getDistance(player) != 0) {
                    double d=G.get(i).getDistance(player);
                    G.get(i).motionX=vec3d.x*4/d;
                    G.get(i).motionZ=vec3d.z*4/d;
                }
            }

            worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
            player.getCooldownTracker().setCooldown(stack.getItem(), 200);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(@Nonnull ItemStack stack)
    {
        return true;
    }
}
