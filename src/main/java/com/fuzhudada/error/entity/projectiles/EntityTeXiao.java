package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityTeXiao extends EntityThrowable {
    public float damage;
    private EntityLivingBase target;
    public EntityLivingBase ATTACKER;

    public EntityTeXiao(World worldIn)
    {
        super(worldIn);
        this.damage=35;
    }

    public EntityTeXiao(World world,EntityLivingBase attacker,EntityLivingBase target){
        super(world);
        this.ATTACKER=attacker;
        this.target=target;
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != ATTACKER && result.entityHit instanceof EntityLivingBase && !(result.entityHit instanceof EntityArmorStand))
        {
            EntityLivingBase E = (EntityLivingBase) result.entityHit;
            result.entityHit.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), 300);
        }

        if (!this.world.isRemote && result.entityHit == null && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
            for (int i = 0; i <= G.size() - 1; i++) {
                if (Math.sqrt((G.get(i).posX - result.getBlockPos().getX()) * (G.get(i).posX - result.getBlockPos().getX()) + (G.get(i).posY - result.getBlockPos().getY()) * (G.get(i).posY - result.getBlockPos().getY()) + (G.get(i).posZ - result.getBlockPos().getZ()) * (G.get(i).posZ - result.getBlockPos().getZ())) < 3
                        && G.get(i) != ATTACKER && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand) && !(G.get(i) instanceof EntityHuoLong)) {
                    G.get(i).attackEntityFrom(ModDamageSource.causeFireBulletDamage(this, ATTACKER), 300);
                    G.get(i).hurtResistantTime = 0;
                }
            }

            EntityHuoLong.spawnParticle(this.posX,this.posY,this.posZ,6,this.world);
            this.setDead();
        }
    }

    public void onUpdate()
    {
        //生成末地烛粒子效果
        this.world.spawnParticle(EnumParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0, 0, 0);
        //设为不受重力
        this.setNoGravity(true);
        //关键部分，如果定点P（target）存在，则每tick添加一个速度增量，比例系数为0.004
        if(target != null && this.getDistance(target) != 0) {
            double d=this.getDistance(target);
            this.motionX = this.motionX + 0.4*(target.posX-this.posX)/d/d;
            this.motionY = this.motionY + 0.4*(target.posY-this.posY)/d/d;
            this.motionZ = this.motionZ + 0.4*(target.posZ-this.posZ)/d/d;
        }
        //30秒后自动清除，防止卡服
        if(this.ticksExisted > 600){
            this.setDead();
        }

        this.motionX /= 0.99;
        this.motionY /= 0.99;
        this.motionZ /= 0.99;

        super.onUpdate();
    }
}
