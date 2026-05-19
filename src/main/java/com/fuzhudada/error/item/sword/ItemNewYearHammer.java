package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemNewYearHammer extends com.somebody.idlframewok.item.ItemSwordBase {
    public static final NBTTagCompound nbt1=new NBTTagCompound();
    private static final Vec3d vec=new Vec3d(0,0,0);
    static {
        int[] color=new int[]{11743532};
        NBTTagCompound nbt=new NBTTagCompound();
        nbt.setByte("Type",(byte)0);
        nbt.setBoolean("Trail",true);
        nbt.setIntArray("Colors",color);
        NBTTagList list=new NBTTagList();
        list.appendTag(nbt);
        nbt1.setTag("Explosions",list);
    }

    public ItemNewYearHammer(String name , ToolMaterial material) {
        super(name , material);
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack , @Nonnull EntityLivingBase target , @Nonnull EntityLivingBase attacker) {
        List<EntityLivingBase> P = attacker.world.getEntities(EntityLivingBase.class, e->e.getDistance(attacker)<64);
        for(EntityLivingBase e:P){
            if(e instanceof EntityPlayerMP){
                EntityPlayerMP player=(EntityPlayerMP) e;
                PacketHandler.INSTANCE.sendTo(new MessageParticle(target.posX,target.posY+target.getEyeHeight(),target.posZ, vec,2),player);
            }else if(e != target && e.getDistance(target)<3){
                e.attackEntityFrom(DamageSource.causeMobDamage(attacker) , (float)(attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));
            }
        }
        attacker.world.playSound(null, target.posX, target.posY, target.posZ, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.PLAYERS, 2F, target.getRNG().nextFloat() * 0.1F + 0.9F);

        return super.hitEntity(stack , target , attacker);
    }
}
