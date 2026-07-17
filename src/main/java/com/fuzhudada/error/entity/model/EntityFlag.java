package com.fuzhudada.error.entity.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntityFlag extends EntityMob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public EntityFlag(World worldIn) {
        super(worldIn);
        this.setSize(this.width, this.height * 4.5F);
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("flow", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(Entity entityIn) {}

    protected void collideWithNearbyEntities() {}

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.isEntityAlive() ? this.getEntityBoundingBox() : null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
