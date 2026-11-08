package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
import java.util.UUID;

public class EntityElephant extends EntityMob implements IAnimatable {
    private final AnimationFactory factory=new AnimationFactory(this);
    private static final DataParameter<Integer> CCBTimer = EntityDataManager.createKey(EntityElephant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> ROARTimer = EntityDataManager.createKey(EntityElephant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> ROARTimer1 = EntityDataManager.createKey(EntityElephant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> NoseTimer = EntityDataManager.createKey(EntityElephant.class, DataSerializers.VARINT);
    protected static final DataParameter<Optional<UUID>> TARGET_UNIQUE_ID = EntityDataManager.createKey(EntityElephant.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public int CCBCoolDown;
    public int RoarCoolDown;
    public int NoseCoolDown;
    public static final float CCBDamage=1000;
    public static final float ROARDamage=1000;
    public static final float NoseDamage=100;

    public EntityElephant(World worldIn) {
        super(worldIn);
        setSize(5,6);
        this.setHealth(this.getMaxHealth());
        this.stepHeight = 1.0F;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(5, new AIElephantAttack(this, 1.0D, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10000.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CCBTimer, 0);
        this.dataManager.register(ROARTimer, 0);
        this.dataManager.register(ROARTimer1, 0);
        this.dataManager.register(NoseTimer , 0);
        this.dataManager.register(TARGET_UNIQUE_ID, Optional.absent());
    }

    public void setCCBTimer(int T){
        this.dataManager.set(CCBTimer,T);
    }

    public int getCCBTimer(){
        return this.dataManager.get(CCBTimer);
    }

    public void setROARTimer(int T){
        this.dataManager.set(ROARTimer,T);
    }

    public int getROARTimer(){
        return this.dataManager.get(ROARTimer);
    }

    public void setROARTimer1(int T){
        this.dataManager.set(ROARTimer1,T);
    }

    public int getROARTimer1(){
        return this.dataManager.get(ROARTimer1);
    }

    public void setNoseTimer(int T){
        this.dataManager.set(NoseTimer,T);
    }

    public int getNoseTimer(){
        return this.dataManager.get(NoseTimer);
    }

    public UUID getTargetId()
    {
        return (UUID)((Optional)this.dataManager.get(TARGET_UNIQUE_ID)).orNull();
    }

    public void setTargetId(@Nullable UUID p_184754_1_) { this.dataManager.set(TARGET_UNIQUE_ID, Optional.fromNullable(p_184754_1_)); }

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
        return (double)this.height*0.875;
    }

    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull Block blockIn) {this.playSound(ModSoundHandler.ELEPHANT_STEP, 1.0F, 1.0F); }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event){
        if(getNoseTimer()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.nose", true));
            return PlayState.CONTINUE;
        }else if(getCCBTimer()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.ccb", true));
            return PlayState.CONTINUE;
        }else if(getROARTimer()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.roar", true));
            return PlayState.CONTINUE;
        }else if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", true));
            event.getController().animationSpeed=getROARTimer1()>0?3:1;
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

    public void onUpdate(){
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
                    e.attackEntityFrom(ModDamageSource.causeCCBDamage(this) , CCBDamage);
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

        EntityLivingBase target=getAttackTarget();
        if(target != null && RoarCoolDown<=0 && getCCBTimer()<=0 && getDistanceSq(target)>169 && getNoseTimer()<=0){
            setROARTimer(24);
            world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.ELEPHANT_ROAR , SoundCategory.MASTER, 10F, this.getRNG().nextFloat() * 0.1F + 0.9F);
            RoarCoolDown=444;
            addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,24,10,false,false));
        }

        int t2=getROARTimer();
        if(t2>0){
            setROARTimer(t2-1);

            if(t2==1){
                setROARTimer1(120);
                addPotionEffect(new PotionEffect(MobEffects.SPEED,120,4,false,false));
            }
        }

        int t3=getROARTimer1();
        if(t3>0){
            setROARTimer1(t3-1);

            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                    && e.getDistance(this)<5
                    && !e.equals(this)
                    && !(e instanceof EntityArmorStand));
            for (EntityLivingBase e:G) {
                e.attackEntityFrom(DamageSource.causeMobDamage(this),ROARDamage);
                e.motionY=1;
                if((posX - e.posX)*(posX - e.posX)+(posZ - e.posZ)*(posZ - e.posZ) != 0) {
                    e.knockBack(this, 3, posX - e.posX, posZ - e.posZ);
                }
            }
        }

        if(RoarCoolDown>0){
            RoarCoolDown--;
        }

        if(target != null && NoseCoolDown<=0 && getCCBTimer()<=0 && getROARTimer()<=0 && getROARTimer1()<=0){
            double d=getDistanceSq(target);
            if(d>25 && d<121){
                setNoseTimer(300);
                NoseCoolDown=300;
                if(target instanceof EntityPlayer) setTargetId(target.getUniqueID());
            }
        }

        int t4=getNoseTimer();
        UUID uuid=getTargetId();
        if((target != null || uuid != null) && t4>0){
            EntityPlayer entityOnNose=null;
            if(uuid != null) entityOnNose=world.getPlayerEntityByUUID(uuid);
            if(entityOnNose != null) target=entityOnNose;

            if(target != null && (getDistanceSq(target)>100 || !target.isEntityAlive())){
                target=null;
                t4=0;
            }

            if(target != null){
                if(t4>280){
                    Vec3d F=getLookVec();
                    double x=posX+5.5*F.x;
                    double y=posY+0.225*(300-t4);
                    double z=posZ+5.5*F.z;
                    //target.sendMessage(new TextComponentTranslation(x+" "+y+" "+z));
                    target.setPosition(x,y,z);
                }

                if(t4<280){
                    if(ticksExisted%20==0) target.attackEntityFrom(DamageSource.causeMobDamage(this),NoseDamage);
                    Vec3d F=getLookVec();
                    double x=posX+5.5*F.x;
                    double y=posY+4.5;
                    double z=posZ+5.5*F.z;
                    //target.sendMessage(new TextComponentTranslation(x+" "+y+" "+z));
                    target.setPosition(x,y,z);
                }

                addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,1,10,false,false));
            }

            setNoseTimer(t4-1);
        }

        if(NoseCoolDown>0){
            NoseCoolDown--;
        }

        super.onUpdate();
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source , float amount) {
        EntityLivingBase target=getAttackTarget();
        if(target != null && source.getTrueSource() != null && source.getTrueSource().equals(target) && getNoseTimer()>0){
            setNoseTimer(0);
            CCBCoolDown=32;
            NoseCoolDown=300;
        }

        if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && getRNG().nextDouble()<0.5){
            setAttackTarget((EntityLivingBase) source.getTrueSource());
        }

        return super.attackEntityFrom(source , amount);
    }

    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        if(cause.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) cause.getTrueSource();
            if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.ELEPHANT_TEETH,2)))
            {
                player.dropItem(new ItemStack(ModItems.ELEPHANT_TEETH,2), false);
            }

            if (getRNG().nextDouble()<0.1 && !player.inventory.addItemStackToInventory(new ItemStack(ModItems.TOPAZ)))
            {
                player.dropItem(new ItemStack(ModItems.TOPAZ), false);
            }
        }else{
            EntityItem entityItem=new EntityItem(this.world);
            entityItem.setItem(new ItemStack(ModItems.ELEPHANT_TEETH,2));
            entityItem.setPosition(this.posX,this.posY,this.posZ);
            if(! this.world.isRemote){
                this.world.spawnEntity(entityItem);
            }

            if(getRNG().nextDouble()<0.1){
                EntityItem entityItem1=new EntityItem(this.world);
                entityItem1.setItem(new ItemStack(ModItems.TOPAZ));
                entityItem1.setPosition(this.posX,this.posY,this.posZ);
                if(! this.world.isRemote){
                    this.world.spawnEntity(entityItem1);
                }
            }
        }
    }

    static class AIElephantAttack extends EntityAIAttackMelee
    {
        public AIElephantAttack(EntityCreature creature , double speedIn , boolean useLongMemory) {
            super(creature , speedIn , useLongMemory);
        }

        @Override
        public boolean shouldExecute() {
            EntityElephant sg=(EntityElephant) this.attacker;
            if(sg.CCBCoolDown>0) return false;

            return super.shouldExecute();
        }

        @Override
        public boolean shouldContinueExecuting() {
            EntityElephant sg=(EntityElephant) this.attacker;
            if(sg.CCBCoolDown>0) return false;

            return super.shouldContinueExecuting();
        }

        protected void checkAndPerformAttack(@Nonnull EntityLivingBase p_190102_1_, double p_190102_2_)
        {
            double d0 = this.getAttackReachSqr(p_190102_1_);
            EntityElephant sg=(EntityElephant) this.attacker;

            if (p_190102_2_ <= d0 && this.attackTick <= 0 && sg.getROARTimer()<=0 && sg.getROARTimer1()<=0 && sg.getNoseTimer()<=0)
            {
                this.attackTick = 32;
                ((EntityElephant)attacker).setCCBTimer(20);
                ((EntityElephant)attacker).CCBCoolDown=32;
            }
        }

        protected double getAttackReachSqr(@Nonnull EntityLivingBase attackTarget)
        {
            return 25;
        }
    }
}
