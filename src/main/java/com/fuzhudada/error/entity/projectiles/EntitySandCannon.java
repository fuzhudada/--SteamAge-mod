package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntitySandCannon extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float damage;
    private static final DataParameter<Integer> size = EntityDataManager.createKey(EntitySandCannon.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> flag = EntityDataManager.createKey(EntitySandCannon.class, DataSerializers.VARINT);

    public EntitySandCannon(World worldIn) {
        super(worldIn);
        this.setSize(3,3);
    }

    public EntitySandCannon(World worldIn,int size,EntityLivingBase ATTACKER,float damage) {
        super(worldIn);
        this.setSize(size,size);
        this.ATTACKER=ATTACKER;
        setSize(size);
        this.damage=damage;
    }

    public void setSize(int size)
    {
        this.dataManager.set(EntitySandCannon.size,size);
    }

    public int getSize()
    {
        return (this.dataManager.get(size));
    }

    public void setFlag(int flag)
    {
        this.dataManager.set(EntitySandCannon.flag,flag);
    }

    public int getFlag()
    {
        return (this.dataManager.get(flag));
    }

    protected void entityInit()
    {
        this.dataManager.register(size, 3);
        this.dataManager.register(flag,0);
    }

    protected void onImpact(@Nonnull RayTraceResult result)
    {
        if (getFlag()<=1 && !this.world.isRemote && result.entityHit==null && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            int size=getSize();
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                    && e.getDistance(this)<Math.pow(2,size-1)
                    && !e.equals(ATTACKER)
                    && !(e instanceof EntityArmorStand)
                    && (!(ATTACKER instanceof EntityPlayer) || !(e instanceof EntityPlayer)));
            for (EntityLivingBase e:G) {
                e.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), damage);
                //e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,10));
                for(int i=0;i<3;i++){
                    world.spawnParticle(EnumParticleTypes.CLOUD, e.posX + this.rand.nextDouble() - 0.5D, e.posY+this.rand.nextDouble()-0.5D, e.posZ + this.rand.nextDouble() - 0.5D, 0.0D, 0.0D, 0.0D);
                }
            }
            world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 2F, world.rand.nextFloat() * 0.1F + 0.9F);
            setFlag(2);
        }
    }

    public void onUpdate()
    {
        if(this.ticksExisted >= 100){
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }else if(getFlag()==2){
            int s=getSize();
            if(!world.isRemote){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                        e -> e.getDistance(this)<s*2
                                && e.isEntityAlive()
                                && !(e instanceof EntityArmorStand)
                                && !e.equals(ATTACKER)
                                && (!(ATTACKER instanceof EntityPlayer) || !(e instanceof EntityPlayer)));
                for(EntityLivingBase e:G){
                    e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,2,3));
                }
            }

            this.world.spawnParticle(EnumParticleTypes.CLOUD,this.posX+s*(2*world.rand.nextDouble()-1),this.posY+0.625,this.posZ+s*(2*world.rand.nextDouble()-1),0,0,0);
        }else{
            super.onUpdate();
        }
    }
}
