package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.blocks.modblocks.BlockVentFan;
import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.entity.projectiles.EntityRobotBullet;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.Circle;
import com.fuzhudada.error.packet.ParticleAnimation.CircleShrink;
import com.fuzhudada.error.packet.ParticleAnimation.Line;
import com.fuzhudada.error.packet.ParticleAnimation.Point;
import com.fuzhudada.error.particle.ModParticleHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
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

import javax.annotation.Nonnull;
import java.util.List;

public class EntityRobotKiller extends EntityMob implements IAnimatable {
    public static final int STATE_IDLE = 0;
    public static final int STATE_SKILL1 = 1;
    public static final int STATE_SKILL2 = 2;
    public static final int STATE_SKILL3 = 3;
    public static final int STATE_COMBO1 = 4;
    public static final int STATE_COMBO2 = 5;
    public static final int STATE_RECOVERY = 6;
    public static final int STATE_ULTIMATE = 7;

    public static final float BULLET_DAMAGE = 128F;
    public static final float ROCKET_DAMAGE = 178F;
    public static final float MELEE_DAMAGE = 128F;
    public static final float STEAM_DAMAGE = 550F;
    public static final float SLAM_DAMAGE = 720F;
    public static final float SELF_DESTRUCT_DAMAGE = 1000F;

    // 与 robot_killer.animation.json 的 fire(14s=280tick) 严格对齐
    private static final int SKILL1_DURATION = 280;
    private static final int SKILL2_DURATION = 200;
    private static final int SKILL3_DURATION = 280;
    private static final int SKILL1_ROTATION_COUNT = 3;
    private static final int COMBO1_WARN = 60;
    // 与 jump 动画 (2.3333s≈47tick) 严格对齐
    private static final int RECOVERY_DURATION = 100;

    private static final double ARENA_SCAN_RADIUS = 36.0D;
    private static final double GAS_MAX_RADIUS = 32.0D;
    private static final int GAS_DURATION = 1200;
    private static final float VENT_SHRINK_RATE = 0.1F;
    private static final float VENT_REGROW_RATE = 0.05F;
    private static final double ONE_FAN_RADIUS = 16.0D;
    private static final double TWO_FAN_RADIUS = 8.0D;
    private static final int SELF_DESTRUCT_WARN = 60;

    private static final DataParameter<Integer> FireTimer = EntityDataManager.createKey(EntityRobotKiller.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> MeleeSwingTimer = EntityDataManager.createKey(EntityRobotKiller.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> JumpTimer = EntityDataManager.createKey(EntityRobotKiller.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> SkillState = EntityDataManager.createKey(EntityRobotKiller.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> ActionTimer = EntityDataManager.createKey(EntityRobotKiller.class, DataSerializers.VARINT);
    private static final DataParameter<BlockPos> TargetLockPos = EntityDataManager.createKey(EntityRobotKiller.class, DataSerializers.BLOCK_POS);

    private final AnimationFactory factory = new AnimationFactory(this);
    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public BlockPos spawnPoint;
    private int rocketShotTimer;

    private boolean ultimateStarted;
    private boolean exploded;
    private int gasTimer;
    private int selfDestructTimer = -1;
    private boolean ventScanned;
    private final BlockPos[] ventPositions = new BlockPos[4];
    private final float[] localRadius = new float[4];
    private double[] explodeAngles;

    public EntityRobotKiller(World worldIn) {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.stepHeight = 1.0F;
        this.experienceValue = 0;
    }

    protected void initEntityAI() {
        this.tasks.addTask(5, new EntityRobotKiller.AIRobotKillerAttack(this, 1.0D, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(MELEE_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15000.0D);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FireTimer, 0);
        this.dataManager.register(MeleeSwingTimer, 0);
        this.dataManager.register(JumpTimer, 0);
        this.dataManager.register(ActionTimer, 0);
        this.dataManager.register(SkillState, STATE_IDLE);
        this.dataManager.register(TargetLockPos, BlockPos.ORIGIN);
    }

    public void addTrackingPlayer(@Nonnull EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(@Nonnull EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        int state = getSkillState();

        if (state == STATE_ULTIMATE) {
            return PlayState.STOP;
        } else if (state == STATE_RECOVERY || getMeleeSwingTimer() == 8) {
            event.getController().clearAnimationCache();
        } else if (state == STATE_SKILL3 && getMeleeSwingTimer() > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("melee", false));
            return PlayState.CONTINUE;
        } else if (state == STATE_COMBO2) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jump", false));
            return PlayState.CONTINUE;
        } else if (state == STATE_SKILL1) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fire", false));
            return PlayState.CONTINUE;
        } else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk1", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void setFireTimer(int t) {
        this.dataManager.set(FireTimer, t);
    }

    public int getFireTimer() {
        return this.dataManager.get(FireTimer);
    }

    public void setMeleeSwingTimer(int t) {
        this.dataManager.set(MeleeSwingTimer, t);
    }

    public int getMeleeSwingTimer() {
        return this.dataManager.get(MeleeSwingTimer);
    }

    public void setJumpTimer(int t) {
        this.dataManager.set(JumpTimer, t);
    }

    public int getJumpTimer() {
        return this.dataManager.get(JumpTimer);
    }

    public void setActionTimer(int t) {
        this.dataManager.set(ActionTimer, t);
    }

    public int getActionTimer() {
        return this.dataManager.get(ActionTimer);
    }

    public void setSkillState(int s) {
        this.dataManager.set(SkillState, s);
    }

    public int getSkillState() {
        return this.dataManager.get(SkillState);
    }

    public void setTargetLockPos(double x, double y, double z) {
        this.dataManager.set(TargetLockPos, new BlockPos(x, y, z));
    }

    public BlockPos getTargetLockPos() {
        return this.dataManager.get(TargetLockPos);
    }

    public boolean canBePushed() {
        return false;
    }

    public void knockBack(@Nonnull net.minecraft.entity.Entity entityIn, float strength, double xRatio, double zRatio) {
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return ModSoundHandler.TANK_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSoundHandler.TANK_HURT;
    }

    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull Block blockIn) {this.playSound(ModSoundHandler.ROBOT_KILLER_WALK, 1.0F, 1.0F); }

    @Override
    public void setPosition(double x, double y, double z) {
        spawnPoint = new BlockPos(x, y, z);
        super.setPosition(x, y, z);
    }

    @Override
    public void onUpdate() {
        if (!ultimateStarted && this.getHealth() > 0F && this.getHealth() <= this.getMaxHealth() * 0.15F) {
            startUltimate();
        }

        EntityLivingBase target = this.getAttackTarget();

        switch (getSkillState()) {
            case STATE_IDLE:
                tickIdle(target);
                break;
            case STATE_SKILL1:
                tickSkill1();
                break;
            case STATE_SKILL2:
                tickSkill2(target);
                break;
            case STATE_SKILL3:
                tickSkill3();
                break;
            case STATE_COMBO1:
                tickCombo1();
                break;
            case STATE_COMBO2:
                tickCombo2(target);
                break;
            case STATE_RECOVERY:
                tickRecovery();
                break;
            case STATE_ULTIMATE:
                tickUltimate();
                break;
            default:
                break;
        }

        if(!world.isRemote){
            List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
            if(P.size()<=0 && getSkillState() != STATE_IDLE){
                setHealth(getMaxHealth());
                setSkillState(STATE_IDLE);
                setFireTimer(0);
                setMeleeSwingTimer(0);
                setJumpTimer(0);
                setActionTimer(0);
                motionX = 0;
                motionY = 0;
                motionZ = 0;
                if (spawnPoint != null) {
                    setPosition(spawnPoint.getX() + 0.5, spawnPoint.getY(), spawnPoint.getZ() + 0.5);
                }
                setNoGravity(false);
                setAttackTarget(null);
                ultimateStarted = false;
            }
        }

        this.bossInfo.setPercent(Math.max(0F, this.getHealth() / this.getMaxHealth()));

        super.onUpdate();
    }

    private void tickIdle(EntityLivingBase target) {
        if (target == null) return;

        int choice = this.rand.nextInt(3);
        if (choice == 0) {
            startSkill1();
        } else if (choice == 1) {
            startSkill2();
        } else {
            startSkill3();
        }
    }

    private void startSkill1() {
        setSkillState(STATE_SKILL1);
        setFireTimer(SKILL1_DURATION);
    }

    private void tickSkill1() {
        int t = getFireTimer();
        int elapsed = SKILL1_DURATION - t;
        int interval = 4;
        int shotsPerRotation = (SKILL1_DURATION - 40) / SKILL1_ROTATION_COUNT / interval;

        if(elapsed==1 || elapsed==SKILL1_DURATION-10){
            world.playSound(null, posX, posY, posZ, ModSoundHandler.ROBOT_KILLER_ARM_ROTATE, SoundCategory.HOSTILE, 2F, 0.6F);
        }

        if (elapsed >= 30 && elapsed < SKILL1_DURATION - 10 && elapsed % interval == 0) {
            int index = (elapsed - 30) / interval % shotsPerRotation;
            double angle = 1.0 * index / shotsPerRotation * 360;
            fireBulletRing(angle);
        }

        if (t > 0) {
            setFireTimer(t - 1);
        } else {
            endSkill();
        }
    }

    private void fireBulletRing(double angle) {
        if (this.world.isRemote) {
            return;
        }


        double angle1 = Math.toRadians(getRotationYawHead() + 60 + angle);
        double angle2 = Math.toRadians(getRotationYawHead() + 90 + angle);
        double dx1 = Math.cos(angle1), dz1 = Math.sin(angle1);
        double dx2 = Math.cos(angle1), dz2 = Math.sin(angle1);

        EntityRobotBullet bullet = new EntityRobotBullet(this.world, this, BULLET_DAMAGE);
        bullet.setPosition(posX + dx1, posY + this.getEyeHeight() - 0.2, posZ + dz1);
        bullet.motionX = dx2 * 0.4;
        bullet.motionY = 0;
        bullet.motionZ = dz2 * 0.4;
        bullet.setNoGravity(true);
        world.spawnEntity(bullet);

        dx1 = Math.cos(angle1 + Math.PI);
        dz1 = Math.sin(angle1 + Math.PI);
        dx2 = Math.cos(angle2 + Math.PI);
        dz2 = Math.sin(angle2 + Math.PI);
        EntityRobotBullet bullet1 = new EntityRobotBullet(this.world, this, BULLET_DAMAGE);
        bullet1.setPosition(posX + dx1, posY + this.getEyeHeight() - 0.2, posZ + dz1);
        bullet1.motionX = dx2 * 0.4;
        bullet1.motionY = 0;
        bullet1.motionZ = dz2 * 0.4;
        bullet1.setNoGravity(true);
        world.spawnEntity(bullet1);

        world.playSound(null, posX, posY, posZ, ModSoundHandler.GUN1, SoundCategory.HOSTILE, 2F, 0.6F);
    }

    private void startSkill2() {
        setSkillState(STATE_SKILL2);
        setActionTimer(SKILL2_DURATION);
        rocketShotTimer = 20;
    }

    private void tickSkill2(EntityLivingBase target) {
        if (target != null) {
            this.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
        }

        rocketShotTimer--;
        if (target != null && rocketShotTimer <= 0) {
            setTargetLockPos(target.posX, target.posY, target.posZ);
            fireRocket();
            rocketShotTimer = 20;
        }

        int t = getActionTimer();
        if(t > 0){
            setActionTimer(t - 1);
        }else{
            endSkill();
        }
    }

    private void fireRocket() {
        if (this.world.isRemote) {
            return;
        }

        BlockPos lockPos = getTargetLockPos();
        Vec3d F=getLookVec();
        EntityZhongDanPao bomb=new EntityZhongDanPao(world, this, lockPos, ROCKET_DAMAGE);
        bomb.setPosition(posX-0.5*F.x,posY+0.7*getEyeHeight(),posZ-0.5*F.z);
        bomb.motionX = 0;
        bomb.motionY = 2;
        bomb.motionZ = 0;
        bomb.setNoGravity(true);
        world.spawnEntity(bomb);
        this.world.playSound(null, posX, posY, posZ, ModSoundHandler.GUN3, SoundCategory.HOSTILE, 1.5F, 0.8F);
    }

    private void startSkill3() {
        setSkillState(STATE_SKILL3);
        setActionTimer(SKILL3_DURATION);
    }

    private void tickSkill3() {
        int at = getMeleeSwingTimer();
        EntityLivingBase target=getAttackTarget();

        if(at > 0){
            if(at == 4){
                if(target != null){
                    attackEntityAsMob(target);
                    this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_IRONGOLEM_ATTACK , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }
            setMeleeSwingTimer(at - 1);
        }

        int t = getActionTimer();
        if(t > 0){
            setActionTimer(t - 1);
        }else{
            endSkill();
        }
    }

    private void endSkill() {
        EntityLivingBase target = getAttackTarget();
        if (target == null) {
            startRecovery();
            return;
        }

        if (this.getDistance(target) <= 8.0D) {
            startCombo1();
        } else {
            startCombo2(target);
        }
    }

    private void startCombo1() {
        setSkillState(STATE_COMBO1);
        setActionTimer(COMBO1_WARN);
    }

    private void tickCombo1() {
        int t = getActionTimer();

        if (t % 10 == 0) {
            sendWarningRing();
        }

        if(t > 0){
            setActionTimer(t - 1);
        } else {
            dealAreaDamage(posX, posY, posZ, 8.0D, STEAM_DAMAGE, ModDamageSource.causeSteamDamage(this));
            sendSteamParticle();
            this.world.playSound(null, posX, posY, posZ, ModSoundHandler.STEAM, SoundCategory.HOSTILE, 2F, 0.6F);
            startRecovery();
        }
    }

    private void startCombo2(EntityLivingBase target) {
        setNoGravity(true);
        setSkillState(STATE_COMBO2);
        setJumpTimer(0);
        setTargetLockPos(target.posX, target.posY, target.posZ);
    }

    private void tickCombo2(EntityLivingBase target) {
        int jt = getJumpTimer();

        if (jt == 20) {
            motionY = 2;
            world.playSound(null, posX, posY, posZ, ModSoundHandler.STEAM, SoundCategory.HOSTILE, 2F, 0.7F);
            dealAreaDamage(posX, posY, posZ, 3.0D, STEAM_DAMAGE, ModDamageSource.causeSteamDamage(this));
        }

        if(jt >= 30 && jt <= 55){
            motionY = 0;
        }

        if(jt == 55){
            if (target != null) {
                setTargetLockPos(target.posX, target.posY, target.posZ);
            }

            BlockPos lockPos = getTargetLockPos();
            if (lockPos != null) {
                // 计算水平方向向量（目标中心 - 实体位置）
                double dx = (lockPos.getX() + 0.5) - this.posX;
                double dz = (lockPos.getZ() + 0.5) - this.posZ;

                // 使用 atan2 计算 Minecraft Yaw（单位：度）
                float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));

                // 设置头部和身体朝向
                this.rotationYawHead = yaw;
                this.rotationYaw = yaw;
            }

            world.playSound(null, posX, posY, posZ, ModSoundHandler.AAS, SoundCategory.HOSTILE, 2F, 0.7F);
        }

        if (jt >= 55) {
            BlockPos lockPos = getTargetLockPos();
            double dx = lockPos.getX() + 0.5 - posX;
            double dy = lockPos.getY() - posY;
            double dz = lockPos.getZ() + 0.5 - posZ;
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (dist > 0.5D) {
                motionX = dx / dist;
                motionY = dy / dist;
                motionZ = dz / dist;
            }
        }

        if(world.isRemote){
            if(jt >= 20 && jt <= 50){
                for(int i=0;i<5;i++){
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,posX+Math.random()*0.6-0.3,posY,posZ+Math.random()*0.6-0.3,0,-0.5,0);
                }
            }

            if(jt >= 50){
                double angle = Math.toRadians(rotationYawHead + 90);
                double dx = Math.cos(angle);
                double dy = 0.2886;
                double dz = Math.sin(angle);
                for(int i=0;i<5;i++){
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,posX-dx+Math.random()*0.6-0.3,posY+1.6,posZ-dz+Math.random()*0.6-0.3,-dx,dy,-dz);
                }
            }
        }

        if(jt >= 55 && onGround){
            dealAreaDamage(posX, posY, posZ, 4.0D, SLAM_DAMAGE, DamageSource.causeMobDamage(this));
            world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 2F, 0.6F);
            motionX = 0;
            motionY = 0;
            motionZ = 0;

            List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new MessageParticle(posX,posY,posZ,new Vec3d(0,0,0),3) ,player);
            }
            if(world.isRemote){
                for(int i=0;i<20;i++){
                    world.spawnParticle(EnumParticleTypes.LAVA,posX+Math.random()*1.2-0.6,posY+Math.random()*0.5,posZ+Math.random()*1.2-0.6,0,0,0);
                }
            }

            startRecovery();
        }
        setJumpTimer(jt + 1);
    }

    private void startRecovery() {
        setNoGravity(false);
        setSkillState(STATE_RECOVERY);
        setActionTimer(RECOVERY_DURATION);
    }

    private void tickRecovery() {
        int t = getActionTimer();
        if(t > 0){
            setActionTimer(t - 1);
        }else{
            setSkillState(STATE_IDLE);
        }
    }

    private void dealAreaDamage(double x, double y, double z, double radius, float damage, DamageSource source) {
        if (this.world.isRemote) {
            return;
        }

        List<EntityLivingBase> targets = this.world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                && e.getDistance(x, y, z) <= radius
                && !e.equals(this)
                && !(e instanceof EntityArmorStand));
        for (EntityLivingBase e : targets) {
            e.attackEntityFrom(source, damage);
            if(source.getDamageType().equals("steam") && !e.isPotionActive(MobEffects.FIRE_RESISTANCE)) e.setFire(10);
        }
    }

    private void sendWarningRing() {
        List<EntityPlayerMP> players = this.world.getEntities(EntityPlayerMP.class, e -> this.getDistance(e) < 64);
        for (EntityPlayerMP p : players) {
            PacketHandler.INSTANCE.sendTo(new Circle("CLOUD", posX, posY + 0.1, posZ, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 8.0, (int) (8.0 * 8)), p);
        }
    }

    private void sendSteamParticle() {
        List<EntityPlayerMP> players = this.world.getEntities(EntityPlayerMP.class, e -> this.getDistance(e) < 64);
        for (EntityPlayerMP p : players) {
            PacketHandler.INSTANCE.sendTo(new CircleShrink("CLOUD", posX, posY + 1, posZ, 0.5, 0, 1, 0, 0, 120, false), p);
        }
    }

    private void startUltimate() {
        ultimateStarted = true;
        setSkillState(STATE_ULTIMATE);
        setFireTimer(0);
        setMeleeSwingTimer(0);
        setJumpTimer(0);
        setActionTimer(0);
        motionX = 0;
        motionY = 0;
        motionZ = 0;
        if (spawnPoint != null) {
            setPosition(spawnPoint.getX() + 0.5, spawnPoint.getY(), spawnPoint.getZ() + 0.5);
        }

        gasTimer = GAS_DURATION;
        selfDestructTimer = -1;
        ventScanned = false;
        exploded = false;
        for (int i = 0; i < 4; i++) {
            localRadius[i] = 0F;
        }

        world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.HOSTILE, 2F, 0.6F);
    }

    private void tickUltimate() {
        if (exploded) {
            return;
        }

        if (!ventScanned) {
            scanVentFans();
            ventScanned = true;
        }

        if (gasTimer > 0) {
            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                if (isVentOpen(i) && isVentOpen((i + 1) % 4)) {
                    if(Math.abs(localRadius[i] - TWO_FAN_RADIUS) > 0.01){
                        if(localRadius[i] < TWO_FAN_RADIUS){
                            localRadius[i] += VENT_REGROW_RATE;
                        }else{
                            localRadius[i] -= VENT_SHRINK_RATE;
                        }
                    }
                }else if(isVentOpen(i) || isVentOpen((i + 1) % 4)){
                    if(Math.abs(localRadius[i] - ONE_FAN_RADIUS) > 0.01){
                        if(localRadius[i] < ONE_FAN_RADIUS){
                            localRadius[i] += VENT_REGROW_RATE;
                        }else{
                            localRadius[i] -= VENT_SHRINK_RATE;
                        }
                    }
                    flag = false;
                }else{
                    if(localRadius[i] < GAS_MAX_RADIUS){
                        localRadius[i] += VENT_REGROW_RATE;
                    }
                    flag = false;
                }
            }

            if (!this.world.isRemote && gasTimer % 10 == 0) {
                applyGasDamage();
            }
            if (world.isRemote && gasTimer % 5 == 0) {
                spawnGasParticles();
            }

            if(flag && gasTimer > 10){
                gasTimer = 10;
            }

            gasTimer--;
            if (gasTimer <= 0) {
                prepareExplosionRays(flag);
                selfDestructTimer = SELF_DESTRUCT_WARN;
            }
        } else if (selfDestructTimer > 0) {
            if (selfDestructTimer % 8 == 0) {
                sendExplosionWarning();
            }
            selfDestructTimer--;
            if (selfDestructTimer <= 0) {
                triggerSelfDestruct();
            }
        }
    }

    private void scanVentFans() {
        if (spawnPoint == null) {
            return;
        }

        int r = (int) ARENA_SCAN_RADIUS;
        for (int x = -r; x <= r; x++) {
            for (int y = -4; y <= 4; y++) {
                for (int z = -r; z <= r; z++) {
                    if (x == 0 && z == 0) {
                        continue;
                    }
                    BlockPos pos = spawnPoint.add(x, y, z);
                    if (this.world.getBlockState(pos).getBlock() == ModBlocks.VENT_FAN) {
                        double angle = Math.toDegrees(Math.atan2(z, x));
                        if (angle < 0) angle += 360;
                        int quadrant = ((int) Math.round(angle / 90.0)) % 4;
                        ventPositions[quadrant] = pos;

                        if(world.getBlockState(pos).getValue(BlockVentFan.ACTIVATED)){
                            IBlockState newState = world.getBlockState(pos).withProperty(BlockVentFan.ACTIVATED, false);
                            world.setBlockState(pos, newState, 2);
                        }
                    }
                }
            }
        }
    }

    private boolean isVentOpen(int quadrant) {
        BlockPos pos = ventPositions[quadrant];
        if (pos == null || !world.getBlockState(pos).getBlock().equals(ModBlocks.VENT_FAN)) {
            return false;
        }
        return world.getBlockState(pos).getValue(BlockVentFan.ACTIVATED);
    }

    private void applyGasDamage() {
        List<EntityLivingBase> players = this.world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                && spawnPoint != null
                && e.getDistance(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ()) < GAS_MAX_RADIUS + 2);
        for (EntityLivingBase p : players) {
            if (isInArea(p.posX, p.posZ)) {
                p.addPotionEffect(new PotionEffect(ModPotions.JU_DU_KONG_QI,100,7));
            }
        }
    }

    private void spawnGasParticles() {
        if(spawnPoint == null) return;
        double avg = (localRadius[0] + localRadius[1] + localRadius[2] + localRadius[3]) / 4;
        for(int i = 0; i <= avg * 4 + 25; i++){
            double x = spawnPoint.getX() + 0.5 + rand.nextDouble() * 2 * GAS_MAX_RADIUS - GAS_MAX_RADIUS;
            double z = spawnPoint.getZ() + 0.5 + rand.nextDouble() * 2 * GAS_MAX_RADIUS - GAS_MAX_RADIUS;
            if(isInArea(x, z)){
                world.spawnParticle(ModParticleHandler.GREEN_GAS, x, spawnPoint.getY() + 0.5 + rand.nextDouble() * 2, z, 0, 0, 0);
            }
        }
    }

    private boolean isInArea(double x,double z){
        if(spawnPoint == null) return false;

        double angle = Math.toDegrees(Math.atan2(z - spawnPoint.getZ() - 0.5, x - spawnPoint.getX() - 0.5));
        if (angle < 0) angle += 360;
        int i = ((int) Math.round(angle / 90.0)) % 4;

        double r1 = localRadius[i], r2 = localRadius[(i + 3) % 4];
        if(angle > 90 * i + 50) angle -= 360;
        double detAngle = 90 * i + 45 - angle;
        double r = (1 - detAngle / 90.0D) * r1 + detAngle / 90.0D * r2;
        if((spawnPoint.getX() + 0.5 - x) * (spawnPoint.getX() + 0.5 - x) + (spawnPoint.getZ() + 0.5 - z) * (spawnPoint.getZ() + 0.5 - z) <= r * r){
            return true;
        }

        if(isVentOpen(i)){
            double angle1 = Math.toRadians(90 * i + 45), angle2 = Math.toRadians(90 * i - 45);
            double x1 = spawnPoint.getX() + 0.5 + localRadius[i] * Math.cos(angle1);
            double z1 = spawnPoint.getZ() + 0.5 + localRadius[i] * Math.sin(angle1);
            double x2 = spawnPoint.getX() + 0.5 + localRadius[(i + 3) % 4] * Math.cos(angle2);
            double z2 = spawnPoint.getZ() + 0.5 + localRadius[(i + 3) % 4] * Math.sin(angle2);
            double x3 = ventPositions[i].getX() + 0.5;
            double z3 = ventPositions[i].getZ() + 0.5;

            double t1 = (x - x1) * (z - z2) - (x - x2) * (z - z1);
            double t2 = (x - x2) * (z - z3) - (x - x3) * (z - z2);
            double t3 = (x - x3) * (z - z1) - (x - x1) * (z - z3);

            return (t1 > 0 && t2 > 0 && t3 > 0) || (t1 < 0 && t2 < 0 && t3 < 0);
        }

        return false;
    }

    private void prepareExplosionRays(boolean flag) {
        int rayCount = flag ? 15 : 100;
        explodeAngles = new double[rayCount];
        for (int i = 0; i < rayCount; i++) {
            explodeAngles[i] = this.rand.nextDouble() * 360.0;
        }
    }

    private void sendExplosionWarning() {
        if (explodeAngles == null) {
            return;
        }
        List<EntityPlayerMP> players = this.world.getEntities(EntityPlayerMP.class, e -> this.getDistance(e) < 64);
        for (double angle : explodeAngles) {
            double rad = Math.toRadians(angle);
            double x2 = posX + GAS_MAX_RADIUS * Math.cos(rad);
            double z2 = posZ + GAS_MAX_RADIUS * Math.sin(rad);
            for (EntityPlayerMP p : players) {
                PacketHandler.INSTANCE.sendTo(new Line("SMOKE_LARGE", posX, posY + 0.2, posZ, x2, posY + 0.2, z2, 0, 0, 0, 0, (int)GAS_MAX_RADIUS * 2), p);
            }
        }
    }

    private void triggerSelfDestruct() {
        if (exploded) {
            return;
        }
        exploded = true;

        if (!this.world.isRemote && explodeAngles != null) {


            List<EntityPlayer> players = this.world.getEntities(EntityPlayer.class, e -> e.isEntityAlive() && this.getDistance(e) < 24);
            for (EntityPlayer p : players) {
                double dx = p.posX - posX;
                double dz = p.posZ - posZ;
                double dist = Math.sqrt(dx * dx + dz * dz);
                double angle = Math.toDegrees(Math.atan2(dz, dx));
                if (angle < 0) angle += 360;

                boolean hit = false;
                for (double rayAngle : explodeAngles) {
                    if (dist < GAS_MAX_RADIUS + 2 && Math.abs(normalizeAngleDeg(angle - rayAngle)) < 9.0) {
                        hit = true;
                        break;
                    }
                }
                if (hit) {
                    p.attackEntityFrom(DamageSource.causeMobDamage(this), SELF_DESTRUCT_DAMAGE);
                }
            }

            sendSelfDestructParticles();
            world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 4F, 0.5F);

            List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64
                    && e.isEntityAlive());
            if(P.size() > 0){
                setHealth(0F);
                onDeath(DamageSource.causeMobDamage(this));
            }
        }
    }

    private void sendSelfDestructParticles(){
        List<EntityPlayerMP> players = this.world.getEntities(EntityPlayerMP.class, e -> this.getDistance(e) < 64);
        for (double angle : explodeAngles) {
            double rad = Math.toRadians(angle);
            double x2 = posX + GAS_MAX_RADIUS * Math.cos(rad);
            double z2 = posZ + GAS_MAX_RADIUS * Math.sin(rad);
            for (EntityPlayerMP p : players) {
                PacketHandler.INSTANCE.sendTo(new Line("EXPLOSION_LARGE", posX, posY + 1, posZ, x2, posY + 0.2, z2, 0, 0, 0, 0, (int)GAS_MAX_RADIUS / 2), p);
            }
        }

        for (EntityPlayerMP p : players) {
            PacketHandler.INSTANCE.sendTo(new Point("EXPLOSION_HUGE", posX, posY + 1, posZ, 0, 0, 0), p);
        }
    }

    private static double normalizeAngleDeg(double a) {
        a = a % 360;
        if (a > 180) a -= 360;
        if (a < -180) a += 360;
        return a;
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if (getSkillState() == STATE_ULTIMATE || source.getDamageType().equals("fall")) {
            return false;
        }

        if (this.getAttackTarget() == null && source.getTrueSource() instanceof EntityLivingBase) {
            this.setAttackTarget((EntityLivingBase) source.getTrueSource());
        }

        return super.attackEntityFrom(source, amount);
    }

    static class AIRobotKillerAttack extends EntityAIAttackMelee
    {
        public AIRobotKillerAttack(EntityCreature creature , double speedIn , boolean useLongMemory) {
            super(creature , speedIn , useLongMemory);
        }

        @Override
        public boolean shouldExecute() {
            EntityRobotKiller rk=(EntityRobotKiller) this.attacker;
            if(rk.getSkillState() != STATE_SKILL3) return false;

            return super.shouldExecute();
        }

        @Override
        public boolean shouldContinueExecuting() {
            EntityRobotKiller rk=(EntityRobotKiller) this.attacker;
            if(rk.getSkillState() != STATE_SKILL3) return false;

            return super.shouldContinueExecuting();
        }

        protected void checkAndPerformAttack(@Nonnull EntityLivingBase p_190102_1_, double p_190102_2_)
        {
            double d0 = this.getAttackReachSqr(p_190102_1_);

            if (p_190102_2_ <= d0 && this.attackTick <= 0)
            {
                this.attackTick = 20;
                ((EntityRobotKiller)attacker).setMeleeSwingTimer(8);
            }
        }

        protected double getAttackReachSqr(@Nonnull EntityLivingBase attackTarget)
        {
            return 4;
        }
    }
}