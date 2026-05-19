package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.model.EntityBomb;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import com.fuzhudada.error.entity.model.EntityZhongDanPao;
import com.fuzhudada.error.entity.projectiles.*;
import com.fuzhudada.error.item.Item.ItemBomb;
import com.fuzhudada.error.item.Item.ItemBulletBag;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public interface IGun extends Shootable{
    float getDamage();

    default int bullet(EntityPlayer playerIn,int level,int...type){
        int j = -1;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);
            int k=Arrays.binarySearch(type,ItemBullet.GetBulletType(itemstack.getItem()));
            if (k>=0) {
                j = i;
                break;
            }

            if(itemstack.getItem() instanceof ItemBulletBag){
                NBTTagCompound nbt=itemstack.getTagCompound();
                if(nbt != null){
                    for(int slot=0;slot<9;slot++){
                        NBTTagCompound nbt1=((NBTTagCompound)nbt.getTag(Integer.toString(slot)));
                        ItemStack stack=new ItemStack(nbt1);
                        if(Arrays.binarySearch(type,ItemBullet.GetBulletType(stack.getItem()))>=0){
                            j = slot+41;
                            break;
                        }
                    }
                }
            }
        }
        return j;
    }

    default int bulletBomb(EntityPlayer playerIn,int...type){
        int j = -1;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);
            if(itemstack.getItem() instanceof ItemBomb){
                int r=Arrays.binarySearch(type,((ItemBomb)itemstack.getItem()).type);
                if(r >= 0){
                    j=i;
                    break;
                }
            }

            if(itemstack.getItem() instanceof ItemBulletBag){
                NBTTagCompound nbt=itemstack.getTagCompound();
                if(nbt != null){
                    for(int slot=0;slot<9;slot++){
                        NBTTagCompound nbt1=((NBTTagCompound)nbt.getTag(Integer.toString(slot)));
                        ItemStack stack=new ItemStack(nbt1);
                        if(stack.getItem() instanceof ItemBomb){
                            int r=Arrays.binarySearch(type,((ItemBomb)stack.getItem()).type);
                            if(r >= 0){
                                j=slot+41;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return j;
    }

    default ItemStack getItemStackFromIndex(EntityPlayer playerIn,int index){
        if(index <= 40){
            return playerIn.inventory.getStackInSlot(index);
        }else{
            for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

                if(itemstack.getItem() instanceof ItemBulletBag){
                    NBTTagCompound nbt=itemstack.getTagCompound();
                    if(nbt != null){
                        NBTTagCompound nbt1=((NBTTagCompound)nbt.getTag(Integer.toString(index-41)));
                        return new ItemStack(nbt1);
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    default void shoot(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn, int type, float damage){
        ItemStack gun=playerIn.getHeldItem(handIn);
        int k=ModEnchantmentInit.PAO_HUO.getEnchantmentLevel(gun);
        damage+=k*6;

        Vec3d D = playerIn.getLookVec();
        Vec3d F = D.normalize();
        //普通子弹
        if(type==0){
            com.fuzhudada.error.entity.creatures.EntityYunShi g = new com.fuzhudada.error.entity.creatures.EntityYunShi(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            g.setNoGravity(true);
            g.DAMAGE = damage;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN_BULLET , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

            //火焰子弹
        }else if(type==1){
            EntityHuoYanZiDan g = new EntityHuoYanZiDan(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            g.setNoGravity(true);
            g.Damage = damage;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            if (D.x * D.x + D.z * D.z != 0) {
                playerIn.knockBack(playerIn, 0.5f, D.x, D.z);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN_FIRE_BULLET , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

            //破甲弹
        }else if(type==2){
            EntityPoJiaDan g = new EntityPoJiaDan(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            g.setNoGravity(true);
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.DAMAGE=damage;
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN_BULLET , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

            //毒弹
        }else if(type==3){
            EntityCuiDuDan g = new EntityCuiDuDan(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            g.setNoGravity(true);
            g.DAMAGE=damage;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN_BULLET , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

            //爆弹果
        }else if(type==7){
            EntityBombFruit g = new EntityBombFruit(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.8F, 1.0F);
            g.DAMAGE=damage;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN_BULLET , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

            //霰弹枪
        }else if(type==4){
            if (!worldIn.isRemote) {
                for(int i=0;i<10;i++){
                    EntityDanJiaQiang g = new EntityDanJiaQiang(worldIn);
                    g.setNoGravity(true);
                    g.motionX=F.x+0.6*Math.random()-0.3;
                    g.motionY=F.y+0.6*Math.random()-0.3;
                    g.motionZ=F.z+0.6*Math.random()-0.3;
                    g.DAMAGE = damage;
                    g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
                    g.ATTACKER=playerIn;
                    worldIn.spawnEntity(g);
                }
            }

            //弹夹
        }else if(type==5){
            EntityDanJiaQiang g = new EntityDanJiaQiang(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0);
            g.setNoGravity(true);
            g.DAMAGE = damage;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + 1.625 + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }

            //RPG
        }else if(type==6){
            EntityZhongDanPao bomb=new EntityZhongDanPao(worldIn,playerIn,getTarget(worldIn,playerIn,8,32),damage);
            bomb.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            bomb.motionX = 2*F.x;
            bomb.motionY = 2*F.y;
            bomb.motionZ = 2*F.z;
            bomb.setRotationYawHead((float)Math.toDegrees(Math.atan(F.z/F.x)));
            bomb.setNoGravity(true);
            if(!worldIn.isRemote){
                worldIn.spawnEntity(bomb);
            }

            //福子弹
        }else if(type==8 || type==9){
            EntityFu g = new EntityFu(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            g.setNoGravity(true);
            g.DAMAGE = damage;
            g.flag=type==9;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }

            //蓝火加特零
        }else if(type==10){
            EntityGatling g = new EntityGatling(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0);
            g.setNoGravity(true);
            g.DAMAGE = damage;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + 1.625 + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
        }
    }

    default void shootBomb(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn, int type, float damage){
        ItemStack gun=playerIn.getHeldItem(handIn);
        int k=ModEnchantmentInit.PAO_HUO.getEnchantmentLevel(gun);
        damage+=k*6;

        Vec3d F = playerIn.getLookVec();

        EntityBomb bomb=new EntityBomb(worldIn,playerIn,damage);
        bomb.setType(type);
        bomb.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
        bomb.motionX = 2*F.x;
        bomb.motionY = 2*F.y;
        bomb.motionZ = 2*F.z;
        if(!worldIn.isRemote){
            worldIn.spawnEntity(bomb);
        }
    }

    default void shrinkBullet(EntityPlayer player,ItemStack gun,int j,int num){
        int level1=ModEnchantmentInit.BU_JI.getEnchantmentLevel(gun);
        if(ModEnchantmentInit.HasCiTiao(player,ModEnchantmentInit.BU_JI_ZHUAN_JIA)){
            int level=ModEnchantmentInit.GetCiTiaoLevel(player,ModEnchantmentInit.BU_JI_ZHUAN_JIA);
            if(player.getRNG().nextFloat()>level*0.025+level1*0.1){
                doShrink(player,j,num);
            }
        }else{
            if(player.getRNG().nextFloat()>level1*0.1){
                doShrink(player,j,num);
            }
        }
    }

    default void doShrink(EntityPlayer player,int j,int num){
        if(j<=40){
            getItemStackFromIndex(player,j).shrink(num);
        }else{
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if(itemstack.getItem() instanceof ItemBulletBag){
                    NBTTagCompound nbt=itemstack.getTagCompound();
                    if(nbt != null){
                        int count=((NBTTagCompound)nbt.getTag(Integer.toString(j-41))).getByte("Count");
                        ((NBTTagCompound)nbt.getTag(Integer.toString(j-41))).setByte("Count",(byte)Math.max(count-num,0));
                    }
                }
            }
        }
    }

    default EntityLivingBase getTarget(World world,EntityPlayer player,double Dmin,double Dmax){
        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                e -> e.getDistance(player)<Dmax && e.getDistance(player)>Dmin && e.isEntityAlive() && !(e instanceof EntityArmorStand));
        double min=Math.PI;
        EntityLivingBase target=null;
        for (EntityLivingBase entityLivingBase:G) {
            Vec3d v1=player.getLookVec();
            Vec3d v2=new Vec3d(entityLivingBase.posX-player.posX,entityLivingBase.posY+entityLivingBase.getEyeHeight()-player.posY-player.getEyeHeight(),entityLivingBase.posZ-player.posZ).normalize();
            double theta=Math.acos(v1.dotProduct(v2)/v1.lengthVector()/v2.lengthVector());
            if(theta<min){
                min=theta;
                target=entityLivingBase;
            }
        }
        if(target != null) {
            EntityHuoLong.spawnParticle(target.posX, target.posY, target.posZ, 4, world);
        }
        return target;
    }
}
