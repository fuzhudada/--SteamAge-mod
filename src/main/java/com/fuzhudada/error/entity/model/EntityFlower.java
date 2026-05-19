package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.entity.projectiles.EntityGongZhengZhiCi;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class EntityFlower extends EntityMob implements IAnimatable {
    AnimationFactory factory=new AnimationFactory(this);
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    private int timer;

    public EntityFlower(World worldIn){
        super(worldIn);
        this.setSize(this.width*2,this.height);
        this.setHealth(this.getMaxHealth());
        this.isImmuneToFire = true;
        this.experienceValue=50;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100D);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.BLOCK_GRASS_BREAK;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.BLOCK_GRASS_BREAK;
    }

    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(Entity entityIn) {}

    protected void collideWithNearbyEntities() {}

    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        if(cause.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) cause.getTrueSource();
            if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.WU_ZI_ZHI_GUO)))
            {
                player.dropItem(new ItemStack(ModItems.WU_ZI_ZHI_GUO), false);
            }
        }else{
            EntityItem entityItem=new EntityItem(this.world);
            entityItem.setItem(new ItemStack(ModItems.WU_ZI_ZHI_GUO));
            entityItem.setPosition(this.posX,this.posY,this.posZ);
            if(! this.world.isRemote){
                this.world.spawnEntity(entityItem);
            }
        }
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event){
        if (this.hurtResistantTime >0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flower_close", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private <T extends IAnimatable> PlayState predicate1(AnimationEvent<T> event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("stem_rotate", true));
        return PlayState.CONTINUE;
    }

    private <T extends IAnimatable> PlayState predicate2(AnimationEvent<T> event){
        if (this.world.getBlockState(this.getPosition().down()).getBlock() == ModBlocks.SHAN_HU1 || (this.getHealth()<2000 && timer <= 200)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("head_rotate", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this,"controller",0,this::predicate));
        data.addAnimationController(new AnimationController(this,"controller1",0,this::predicate1));
        data.addAnimationController(new AnimationController(this,"controller2",0,this::predicate2));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void onUpdate()
    {

        if (this.hurtResistantTime >0) {
            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,this.posX+Math.random()*0.2-0.1,this.posY+this.getEyeHeight()+0.2,this.posZ+Math.random()*0.2-0.1,Math.random()*0.1,2,Math.random()*0.1,1);
        }

        if (this.getHealth() < 2000){
            List<EntityPlayer> list=this.world.getEntities(EntityPlayer.class, e -> !e.getIsInvulnerable());
            if(list.size()>0 && timer %2 ==0 && timer <= 200){
                spawnArrow();
            }

            if(timer <0){
                timer=0;
            }

            if(timer > 400){
                timer=0;
            }

            timer++;
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

        super.onUpdate();
    }

    private void spawnArrow(){
        double angle=Math.random()*Math.PI*2;
        Vec3d F=new Vec3d(Math.sin(angle),0,Math.cos(angle));
        if(!this.world.isRemote){
            EntityGongZhengZhiCi nuMu=new EntityGongZhengZhiCi(this.world);
            nuMu.setExplode(false);
            nuMu.setFire(true);
            if(this.isPotionActive(MobEffects.WATER_BREATHING)){
                nuMu.DAMAGE=20;
            }else{
                nuMu.DAMAGE=40;
            }
            nuMu.setNoGravity(true);
            nuMu.ATTACKER=this;
            nuMu.setPosition(this.posX+F.x,this.posY+this.getEyeHeight(),this.posZ+F.z);
            nuMu.motionX=F.x;
            nuMu.motionY=0;
            nuMu.motionZ= F.z;
            this.world.spawnEntity(nuMu);
        }
    }

    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isNonBoss()
    {
        return false;
    }
}
