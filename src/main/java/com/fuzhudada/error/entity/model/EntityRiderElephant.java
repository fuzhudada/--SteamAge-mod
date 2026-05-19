package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EntityRiderElephant extends EntityHorse implements IAnimatable{
    private final AnimationFactory factory=new AnimationFactory(this);
    private static final DataParameter<Integer> CCBTimer = EntityDataManager.createKey(EntityRiderElephant.class, DataSerializers.VARINT);
    public int CCBCoolDown;

    public EntityRiderElephant(World worldIn) {
        super(worldIn);
        experienceValue=0;
        setSize(4,4);
        this.setHealth(this.getMaxHealth());
        this.stepHeight = 1.0F;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CCBTimer, 0);
    }

    @Override
    public void onUpdate() {
        int t1=getCCBTimer();

        if(t1>0){
            setCCBTimer(t1-1);
            addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,1,10,false,false));

            if(t1==1){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                        && e.getDistance(this)<6.5
                        && !e.equals(this)
                        && !(e instanceof EntityArmorStand));
                for (EntityLivingBase e:G) {
                    if((posX - e.posX)*(posX - e.posX)+(posZ - e.posZ)*(posZ - e.posZ) != 0) {
                        e.knockBack(this, 1.5F, posX - e.posX, posZ - e.posZ);
                    }
                }

                List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
                for (EntityPlayerMP player : P) {
                    PacketHandler.INSTANCE.sendTo(new MessageParticle(posX,posY,posZ,new Vec3d(0,0,0),3) ,player);
                }
                world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.SAND_GIANT_WALK , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
            }
        }

        if(CCBCoolDown>0){
            CCBCoolDown--;
        }

        super.onUpdate();

        if(ticksExisted>4800) setDead();
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return ModSoundHandler.ELEPHANT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSoundHandler.ELEPHANT_DEATH;
    }

    @Override
    public double getMountedYOffset() {
        return (double)this.height*1.3125;
    }

    public void setCCBTimer(int T){
        this.dataManager.set(CCBTimer,T);
    }

    public int getCCBTimer(){
        return this.dataManager.get(CCBTimer);
    }

    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull Block blockIn) {this.playSound(ModSoundHandler.ELEPHANT_STEP, 1.0F, 1.0F); }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event){
        if(getCCBTimer()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.ccb", true));
            return PlayState.CONTINUE;
        }else if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this,"controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void startCCB(){
        if(CCBCoolDown<=0){
            setCCBTimer(20);
            CCBCoolDown=32;
        }
    }
}
