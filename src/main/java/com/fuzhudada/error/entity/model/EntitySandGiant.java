package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.projectiles.EntitySandCannon;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.Circle;
import com.fuzhudada.error.packet.ParticleAnimation.Line;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
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

import javax.annotation.Nonnull;
import java.util.List;

public class EntitySandGiant extends EntityMob implements IAnimatable {
    public static final float fallDamage=500;
    public static final float lightDamage=1000;
    public static final float sandDamage=60;
    public static final double range=16;
    public BlockPos spawnPoint;
    private final AnimationFactory factory=new AnimationFactory(this);
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    private static final DataParameter<Integer> AttackTimer = EntityDataManager.createKey(EntitySandGiant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> JumpTimer1 = EntityDataManager.createKey(EntitySandGiant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> JumpTimer2 = EntityDataManager.createKey(EntitySandGiant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> shootReadyTimer = EntityDataManager.createKey(EntitySandGiant.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> shootTimer = EntityDataManager.createKey(EntitySandGiant.class, DataSerializers.VARINT);
    private static final DataParameter<BlockPos> jumpPos = EntityDataManager.createKey(EntitySandGiant.class, DataSerializers.BLOCK_POS);
    public int jumpCoolDown;
    public boolean isJumping;
    public int sandCoolDown;
    public boolean isShooting;
    public boolean flag;
    public double sandWindRange;
    EntityLivingBase tg;

    public EntitySandGiant(World worldIn) {
        super(worldIn);
        setSize(3,4);
        this.setHealth(this.getMaxHealth());
        this.stepHeight = 1.0F;
        sandCoolDown=200;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(5, new AISandGiantAttack(this, 1.0D, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10000.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(AttackTimer, 0);
        this.dataManager.register(JumpTimer1, 0);
        this.dataManager.register(JumpTimer2, 0);
        this.dataManager.register(shootReadyTimer, 0);
        this.dataManager.register(shootTimer, 0);
        this.dataManager.register(jumpPos, new BlockPos(0,0,0));
    }

    public void addTrackingPlayer(@Nonnull EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(@Nonnull EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event){
        if(getShootTimer()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("rotate", false));
            return PlayState.CONTINUE;
        }else if(getJumpTimer2()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jumpdown", false));
            return PlayState.CONTINUE;
        }else if(getJumpTimer1()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jump", false));
            return PlayState.CONTINUE;
        }else if(getAttackTimer()>0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            return PlayState.CONTINUE;
        }else if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
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

    public void setAttackTimer(int T){
        this.dataManager.set(AttackTimer,T);
    }

    public int getAttackTimer(){
        return this.dataManager.get(AttackTimer);
    }

    public void setJumpTimer1(int T){
        this.dataManager.set(JumpTimer1,T);
    }

    public int getJumpTimer1(){
        return this.dataManager.get(JumpTimer1);
    }

    public void setJumpTimer2(int T){
        this.dataManager.set(JumpTimer2,T);
    }

    public int getJumpTimer2(){
        return this.dataManager.get(JumpTimer2);
    }

    public void setShootReadyTimer(int T){
        this.dataManager.set(shootReadyTimer,T);
    }

    public int getShootReadyTimer(){
        return this.dataManager.get(shootReadyTimer);
    }

    public void setShootTimer(int T){
        this.dataManager.set(shootTimer,T);
    }

    public int getShootTimer(){
        return this.dataManager.get(shootTimer);
    }

    public void setJumpPos(double x,double y,double z){
        this.dataManager.set(jumpPos,new BlockPos(x,y,z));
    }

    public BlockPos getJumpPos(){
        return this.dataManager.get(jumpPos);
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return ModSoundHandler.SAND_GIANT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSoundHandler.SAND_GIANT_DEATH;
    }

    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull Block blockIn) {this.playSound(ModSoundHandler.SAND_GIANT_WALK, 1.0F, 1.0F); }

    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(Entity entityIn) {
        if(getShootReadyTimer()<=0 && getShootTimer()<=0){
            super.collideWithEntity(entityIn);
        }
    }

    protected void collideWithNearbyEntities() {
        if(getShootReadyTimer()<=0 && getShootTimer()<=0){
            super.collideWithNearbyEntities();
        }
    }

    @Override
    public void setPosition(double x , double y , double z) {
        spawnPoint=new BlockPos(x,y,z);

        super.setPosition(x , y , z);
    }

    public void onUpdate()
    {
        int at=getAttackTimer();
        EntityLivingBase target=getAttackTarget();

        if(at>0){
            if(at==9){
                if(target != null){
                    attackEntityAsMob(target);
                    target.motionY=1;
                    this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_IRONGOLEM_ATTACK , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }
            setAttackTimer(at-1);
        }

        if(target != null && getShootReadyTimer()<=0 && getShootTimer()<=0){
            if(jumpCoolDown==0 && !isJumping){
                isJumping=true;
                jumpCoolDown=300;
                setJumpTimer1(100);
            }

            if(sandCoolDown==0){
                for(int i=0;i<3;i++){
                    EntitySandCannon g = new EntitySandCannon(world,2,this,50);
                    g.shoot(this, 0, 0, 0.0F, 0.5F, 1.0F);
                    g.motionX=0;
                    g.motionZ=0;
                    g.setNoGravity(false);

                    double a=Math.PI*2*Math.random();
                    double x=spawnPoint.getX()+(range-1)*Math.random()*Math.cos(a);
                    double z=spawnPoint.getZ()+(range-1)*Math.random()*Math.sin(a);
                    g.setPosition(x, spawnPoint.getY()+12, z);
                    if (!world.isRemote) {
                        world.spawnEntity(g);
                    }
                    world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_SAND_BREAK, SoundCategory.PLAYERS, 2F, getRNG().nextFloat() * 0.1F + 0.9F);
                }
                sandCoolDown=200;
            }
        }

        int jp1=getJumpTimer1();
        int jp2=getJumpTimer2();
        int srt=getShootReadyTimer();
        int st=getShootTimer();

        if(jumpCoolDown>0) jumpCoolDown--;

        if(jp1>0){
            if(jp1==75){
                if(target != null){
                    setJumpPos(target.posX,target.posY,target.posZ);
                }else{
                    setJumpPos(posX,posY,posZ);
                }
            }

            BlockPos pos=getJumpPos();
            if(pos != null && jp1==60){
                motionX=0.1625*(pos.getX()-posX);
                motionY=1;
                motionZ=0.1625*(pos.getZ()-posZ);
                this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BLAZE_SHOOT , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
            }

            if(jp1==1){
                isJumping=false;
            }

            setJumpTimer1(jp1-1);
        }

        if(jp2>0){
            if(jp2==54){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                        && e.getDistance(this)<6
                        && !e.equals(this)
                        && !(e instanceof EntityArmorStand));
                for (EntityLivingBase e:G) {
                    e.attackEntityFrom(DamageSource.causeMobDamage(this), fallDamage);
                }

                List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
                for (EntityPlayerMP player : P) {
                    PacketHandler.INSTANCE.sendTo(new MessageParticle(posX,posY,posZ,new Vec3d(0,0,0),3) ,player);
                }
                this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
            }

            setJumpTimer2(jp2-1);
        }

        if(sandCoolDown>0) sandCoolDown--;

        if(srt>0){
            if(tg != null){
                double d0=Math.sqrt(spawnPoint.distanceSq(tg.posX,tg.posY,tg.posZ));

                if(srt==1){
                    sandWindRange=d0;
                    setShootTimer(192);
                    this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.SAND_GIANT_LIGHT , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }

                List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
                for (EntityPlayerMP player : P) {
                    PacketHandler.INSTANCE.sendTo(new Circle("bloodyWind",spawnPoint.getX(),spawnPoint.getY()+0.1,spawnPoint.getZ(),0.0,0.0,0.0,0.0,1.0,0.0,d0,(int)d0*10) ,player);
                }
            }

            setPosition(spawnPoint.getX(),spawnPoint.getY(),spawnPoint.getZ());
            setShootReadyTimer(srt-1);
        }

        if(st>0){
            if(st==1){
                isShooting=false;
                flag=true;
            }

            double x=spawnPoint.getX()+sandWindRange*Math.cos((0.010416667*st+0.00555556*getRotationYawHead()+0.5)*Math.PI);
            double z=spawnPoint.getZ()+sandWindRange*Math.sin((0.010416667*st+0.00555556*getRotationYawHead()+0.5)*Math.PI);
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                    && e.getDistance(x,spawnPoint.getY(),z)<1
                    && !e.equals(this)
                    && !(e instanceof EntityArmorStand));
            for (EntityLivingBase e:G) {
                e.attackEntityFrom(ModDamageSource.causeManaDamage(this) , lightDamage);
            }

            List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new Circle("bloodyWind",spawnPoint.getX(),spawnPoint.getY()+0.1,spawnPoint.getZ(),0.0,0.0,0.0,0.0,1.0,0.0,sandWindRange,(int)sandWindRange*10) ,player);
                PacketHandler.INSTANCE.sendTo(new Line("bloodyWind",spawnPoint.getX(),spawnPoint.getY()+getEyeHeight()+0.1,spawnPoint.getZ(),x,spawnPoint.getY(),z,0,0,0,0,(int)sandWindRange*15) ,player);
            }

            setPosition(spawnPoint.getX(),spawnPoint.getY(),spawnPoint.getZ());
            setShootTimer(st-1);
        }

        List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
        if(P.size()<=0){
            setHealth(getMaxHealth());
            flag=false;
        }

        if(!flag && !isShooting && getHealth()<getMaxHealth()/2){
            if(!world.isRemote){
                if(P.size()>0){
                    tg=P.get((int)(P.size()*Math.random()));

                    for (EntityPlayerMP player : P) {
                        player.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.TITLE,new TextComponentTranslation("sandGiant.shoot",tg.getName())));
                        player.connection.sendPacket(new SPacketTitle(SPacketTitle.Type.SUBTITLE,new TextComponentTranslation("sandGiant.shoot1")));
                    }
                }else{
                    setHealth(getMaxHealth());
                }
            }

            setRotationYawHead(0);
            setShootReadyTimer(100);
            isShooting=true;
        }

        if(!world.isRemote && flag){
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                    && Math.abs(spawnPoint.getY()-e.posY)<3
                    && !e.equals(this)
                    && !(e instanceof EntityArmorStand));
            for (EntityLivingBase e:G) {
                double d=e.getDistance(spawnPoint.getX(),spawnPoint.getY(),spawnPoint.getZ());
                if(d<range && d>sandWindRange){
                    e.attackEntityFrom(DamageSource.GENERIC , sandDamage);
                    e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,30,1));
                    e.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,30,0));
                }
            }

            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new MessageParticle(spawnPoint.getX(),spawnPoint.getY(),spawnPoint.getZ(),new Vec3d(sandWindRange,range,0),4) ,player);
            }
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

        super.onUpdate();
    }

    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if(isJumping && source.getDamageType().equals("fall")) {
            setJumpTimer1(0);
            setJumpTimer2(60);
            isJumping = false;

            return false;
        }else if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer && spawnPoint.distanceSq(source.getTrueSource().posX,source.getTrueSource().posY,source.getTrueSource().posZ) > range*range) {
            com.somebody.idlframewok.util.CommonFunctions.SafeSendMsgToPlayer((EntityPlayer) source.getTrueSource() , new TextComponentTranslation("sandGiant.damage").getUnformattedText());
            return false;
        }else if(getShootReadyTimer()>0 || getShootTimer()>0){
            return false;
        }

        if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && getRNG().nextDouble()<0.5){
            setAttackTarget((EntityLivingBase) source.getTrueSource());
        }

        return super.attackEntityFrom(source,amount);
    }

    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);
        if(cause.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) cause.getTrueSource();
            if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.WEN_XIAN_SHI_BAN)))
            {
                player.dropItem(new ItemStack(ModItems.WEN_XIAN_SHI_BAN), false);
            }
        }else{
            EntityItem entityItem=new EntityItem(this.world);
            entityItem.setItem(new ItemStack(ModItems.WEN_XIAN_SHI_BAN));
            entityItem.setPosition(this.posX,this.posY,this.posZ);
            if(! this.world.isRemote){
                this.world.spawnEntity(entityItem);
            }
        }
    }

    static class AISandGiantAttack extends EntityAIAttackMelee
    {
        public AISandGiantAttack(EntityCreature creature , double speedIn , boolean useLongMemory) {
            super(creature , speedIn , useLongMemory);
        }

        @Override
        public boolean shouldExecute() {
            EntitySandGiant sg=(EntitySandGiant) this.attacker;
            if(sg.isJumping || sg.getJumpTimer1()>0 || sg.getJumpTimer2()>0 || sg.getShootReadyTimer()>0 || sg.getShootTimer()>0) return false;

            return super.shouldExecute();
        }

        @Override
        public boolean shouldContinueExecuting() {
            EntitySandGiant sg=(EntitySandGiant) this.attacker;
            if(sg.isJumping || sg.getJumpTimer1()>0 || sg.getJumpTimer2()>0 || sg.getShootReadyTimer()>0 || sg.getShootTimer()>0) return false;

            return super.shouldContinueExecuting();
        }

        protected void checkAndPerformAttack(@Nonnull EntityLivingBase p_190102_1_, double p_190102_2_)
        {
            double d0 = this.getAttackReachSqr(p_190102_1_);

            if (p_190102_2_ <= d0 && this.attackTick <= 0)
            {
                this.attackTick = 32;
                ((EntitySandGiant)attacker).setAttackTimer(12);
            }
        }

        protected double getAttackReachSqr(@Nonnull EntityLivingBase attackTarget)
        {
            return 9;
        }
    }
}
