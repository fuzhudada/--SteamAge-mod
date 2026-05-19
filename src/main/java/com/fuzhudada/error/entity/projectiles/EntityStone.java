package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityStone extends EntityThrowable {
    public EntityLivingBase ATTACKER;

    public EntityStone(World worldIn) {
        super(worldIn);
        this.setSize(3,3);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != ATTACKER)
        {
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
            for (int i = 0; i <= G.size() - 1; i++) {
                if (Math.sqrt((G.get(i).posX - this.posX) * (G.get(i).posX - this.posX) + (G.get(i).posY - this.posY) * (G.get(i).posY - this.posY) + (G.get(i).posZ - this.posZ) * (G.get(i).posZ - this.posZ)) < 3
                        && G.get(i) != ATTACKER && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand)) {
                    G.get(i).attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), 120);
                    G.get(i).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,10));
                }
            }
            for(int a=0;a<100;a++){
                this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + (this.rand.nextDouble() - 0.5D) * 3, this.posY + (this.rand.nextDouble() - 0.5D) * 3, this.posZ + (this.rand.nextDouble() - 0.5D) * 3, 0.0D, 0.0D, 0.0D);
            }
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
