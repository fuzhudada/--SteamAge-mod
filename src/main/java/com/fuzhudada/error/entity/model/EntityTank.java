package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.entity.projectiles.EntityDanJiaQiang;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityTank extends EntityMob {
    private static final DataParameter<Float> Rotation = EntityDataManager.createKey(EntityTank.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> Smoke = EntityDataManager.createKey(EntityTank.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> Move = EntityDataManager.createKey(EntityTank.class, DataSerializers.BOOLEAN);
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public EntityTank(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 11.0F, this.height * 1.3F);
        this.setHealth(this.getMaxHealth());
        this.stepHeight = 1.0F;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(4, new EntityTank.AIFireballAttack(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3000.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(Rotation, (float)Math.PI);
        this.dataManager.register(Smoke, false);
        this.dataManager.register(Move, false);
    }

    public void setRotation(float T){
        this.dataManager.set(Rotation,T);
    }

    public float getRotation(){
        return this.dataManager.get(Rotation);
    }

    public void setSmoke(boolean smoke){
        this.dataManager.set(Smoke,smoke);
    }

    public boolean getSmoke(){
        return this.dataManager.get(Smoke);
    }

    public void setMove(boolean move){
        this.dataManager.set(Move,move);
    }

    public boolean getMove(){
        return this.dataManager.get(Move);
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.isEntityAlive() ? this.getEntityBoundingBox() : null;
    }

    protected void collideWithEntity(@Nonnull Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),270);
    }

    public boolean canBePushed()
    {
        return false;
    }

    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);
        if(cause.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) cause.getTrueSource();
            if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.TANK_ARMOR)))
            {
                player.dropItem(new ItemStack(ModItems.TANK_ARMOR), false);
            }
        }else{
            EntityItem entityItem=new EntityItem(this.world);
            entityItem.setItem(new ItemStack(ModItems.TANK_ARMOR));
            entityItem.setPosition(this.posX,this.posY,this.posZ);
            if(! this.world.isRemote){
                this.world.spawnEntity(entityItem);
            }
        }
    }

    public void onUpdate()
    {
        Vec3d F1=this.getVectorForRotation(this.rotationPitch,this.rotationYawHead);
        F1=F1.addVector(0,-F1.y,0).normalize();
        Vec3d F2=new Vec3d(-F1.z,F1.y,F1.x);
        if(getSmoke()){
            this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,this.posX-1.3*F1.x+F2.x,this.posY+1.3,this.posZ-1.3*F1.z+F2.z,0,0.2,0);
            this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,this.posX-1.3*F1.x-F2.x,this.posY+1.3,this.posZ-1.3*F1.z-F2.z,0,0.2,0);
        }

        if(getMove()){
            double x,z;
            for(int i=0;i<20;i++){
                x=(6*Math.random()-3)*F1.x+(6*Math.random()-3)*F2.x;
                z=(6*Math.random()-3)*F1.z+(6*Math.random()-3)*F2.z;
                this.world.spawnParticle(EnumParticleTypes.CRIT,this.posX+x,this.posY,this.posZ+z,0.2*x,0.3,0.2*z);
            }
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

        super.onUpdate();
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.TANK_HURT, SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);

        if(!source.canHarmInCreative() && !source.getDamageType().equals("rpg")){
            amount=0;
            if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer){
                com.somebody.idlframewok.util.CommonFunctions.SafeSendMsgToPlayer((EntityPlayer) source.getTrueSource(), new TextComponentTranslation("tank.damage").getUnformattedText());
            }
        }else{
            for(int i=0;i<3;i++){
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,this.posX+6*Math.random()-3,this.posY+2*Math.random(),this.posZ+6*Math.random()-3,0,0,0);
            }
        }

        if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && getRNG().nextDouble()<0.5){
            setAttackTarget((EntityLivingBase) source.getTrueSource());
        }

        return super.attackEntityFrom(source,amount);
    }

    public void addTrackingPlayer(@Nonnull EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
    }

    public void removeTrackingPlayer(@Nonnull EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    static class AIFireballAttack extends EntityAIBase
    {
        private final EntityTank tank;
        private int attackTime;
        private int rotateTime;
        private int moveTime;
        private int attackStep;
        private int moveDirection;
        private float targetYaw;

        public AIFireballAttack(EntityTank tank)
        {
            this.tank = tank;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.tank.getAttackTarget();
            if(entitylivingbase instanceof EntityPlayer){
                tank.bossInfo.addPlayer((EntityPlayerMP)entitylivingbase);
            }
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        public void startExecuting()
        {
            this.attackStep = 0;
            this.attackTime = 0;
            this.moveTime = 0;
            this.rotateTime = 0;
            this.moveDirection = 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.tank.getAttackTarget();
            double d0 = this.tank.getDistanceSq(entitylivingbase);
            Vec3d F = new Vec3d(entitylivingbase.posX-tank.posX,entitylivingbase.posY+entitylivingbase.getEyeHeight()-tank.posY-2.5,entitylivingbase.posZ-tank.posZ).normalize();
            Vec3d F1=tank.getVectorForRotation(this.tank.rotationPitch,this.tank.rotationYawHead);
            F1=F1.addVector(0,-F1.y,0).normalize();
            Vec3d F2=F.addVector(0,-F.y,0).normalize();
            float angle=(float)(Math.acos(F1.dotProduct(F2)));

//            if(entitylivingbase instanceof EntityPlayer) {
//                com.somebody.idlframewok.util.CommonFunctions.SafeSendMsgToPlayer((EntityPlayer) entitylivingbase, "angle:"+Math.toDegrees(angle)
//                        +"\nRenderYawOffset:"+tank.renderYawOffset
//                        +"\nRotationYawHead:"+tank.rotationYawHead
//                        +"\nRotationYaw:"+tank.rotationYaw
//                        +"\nangle2:"+Math.toDegrees(Math.acos(tank.getLookVec().dotProduct(F2))));
//            }

            int flag=F1.crossProduct(F2).y>0?1:-1;
            if(Math.toDegrees(angle)<135 && d0>14*14) {
                tank.setRotation((float) Math.PI - flag * angle);
            }

            if(moveTime>0) {
                move(F1.scale(moveDirection));
                tank.setSmoke(true);
                tank.setMove(true);
            }
            if(rotateTime>0) {
                rotate();
                tank.setSmoke(true);
            }
            if(attackTime>0){
                --this.attackTime;
            }

            if(moveTime <=0 && rotateTime <=0){
                tank.setSmoke(false);
            }

            if(moveTime <=0){
                tank.setMove(false);
            }

            check(entitylivingbase,d0,angle,F1);

            super.updateTask();
        }

        public void check(EntityLivingBase entitylivingbase,double d0,float angle,Vec3d F1){
            if(d0<14*14){
                if(Math.toDegrees(angle)<20.8 && moveTime<=0 && rotateTime<=0){
                    moveTime=60;
                    moveDirection=1;
                }else if(Math.toDegrees(angle)>159.2 && moveTime<=0 && rotateTime<=0){
                    moveTime=60;
                    moveDirection=-1;
                }else if(rotateTime<=0 && moveTime<=0){
                    double d1=entitylivingbase.posX-tank.posX;
                    double d2=entitylivingbase.posZ-tank.posZ;
                    float f1=(float)Math.toDegrees(Math.atan(d2/d1));
                    float f2=d1>0?f1-90:f1+90;
                    float f3=f2>0?f2-180:f2+180;
                    float f4=getDeltaYaw(tank.getRotationYawHead(),f2);
                    float f5=getDeltaYaw(tank.getRotationYawHead(),f3);
                    if(f4>f5){
                        targetYaw=f3;
                    }else{
                        targetYaw=f2;
                    }
                    rotateTime=(int) getDeltaYaw(tank.getRotationYawHead(),targetYaw)/2;
                }
            }else if(d0<32*32){
                if(Math.toDegrees(angle)<135 && attackTime<=0 && rotateTime<=0){
                    fire(entitylivingbase.posX,entitylivingbase.posY+entitylivingbase.getEyeHeight(),entitylivingbase.posZ,F1);
                }else if(attackTime<=0 && rotateTime<=0){
                    double d1=entitylivingbase.posX-tank.posX;
                    double d2=entitylivingbase.posZ-tank.posZ;
                    float f1=(float)Math.toDegrees(Math.atan(d2/d1));
                    targetYaw=d1>0?f1-90:f1+90;
                    rotateTime=(int) getDeltaYaw(tank.getRotationYawHead(),targetYaw)/2;
                }
            }
        }

        public void move(Vec3d F){
            tank.motionX=0.5*F.x;
            tank.motionZ=0.5*F.z;
            tank.world.playSound(null,tank.posX,tank.posY,tank.posZ,ModSoundHandler.TANK_MOVE, SoundCategory.MASTER, 1F, tank.getRNG().nextFloat() * 0.1F + 0.9F);
            moveTime--;
        }

        public void rotate(){
            if(Math.abs(tank.rotationYawHead-targetYaw)<=180){
                if(tank.rotationYawHead>targetYaw){
                    tank.rotationYawHead-=2;
                    tank.renderYawOffset-=2;
                    tank.rotationYaw-=2;
                }else{
                    tank.rotationYawHead+=2;
                    tank.renderYawOffset+=2;
                    tank.rotationYaw+=2;
                }
            }else{
                if(tank.rotationYawHead>targetYaw){
                    if(tank.rotationYawHead>178){
                        tank.rotationYawHead-=358;
                        tank.renderYawOffset-=358;
                        tank.rotationYaw-=358;
                    }else{
                        tank.rotationYawHead+=2;
                        tank.renderYawOffset+=2;
                        tank.rotationYaw+=2;
                    }
                }else{
                    if(tank.rotationYawHead<-178){
                        tank.rotationYawHead+=358;
                        tank.renderYawOffset+=358;
                        tank.rotationYaw+=358;
                    }else{
                        tank.rotationYawHead-=2;
                        tank.renderYawOffset-=2;
                        tank.rotationYaw-=2;
                    }
                }
            }
            tank.world.playSound(null,tank.posX,tank.posY,tank.posZ,ModSoundHandler.TANK_MOVE, SoundCategory.MASTER, 1F, tank.getRNG().nextFloat() * 0.1F + 0.9F);
            rotateTime--;
        }

        public void fire(double x,double y,double z,Vec3d F1){
            if (this.attackStep < 20) {
                Vec3d pos0 = new Vec3d(tank.posX + 1.6 * F1.x,tank.posY + 2.5,tank.posZ + 1.6 * F1.z);
                Vec3d pos1 = new Vec3d(x-pos0.x,0,z-pos0.z).normalize();
                Vec3d pos = new Vec3d(pos0.x + 3.5 * pos1.x, pos0.y, pos0.z + 3.5 * pos1.z);
                Vec3d motion = new Vec3d(x - pos.x, y - pos.y, z - pos.z).normalize();

                EntityDanJiaQiang g = new EntityDanJiaQiang(tank.world);
                g.setPosition(pos.x, pos.y, pos.z);
                g.motionX = motion.x;
                g.motionY = motion.y;
                g.motionZ = motion.z;
                g.ATTACKER = tank;
                g.DAMAGE = 62;
                g.setNoGravity(true);
                if (!tank.world.isRemote) {
                    tank.world.spawnEntity(g);
                }

                tank.world.playSound(null, tank.posX, tank.posY, tank.posZ, ModSoundHandler.TANK_FIRE, SoundCategory.PLAYERS, 2F, tank.getRNG().nextFloat() * 0.1F + 0.9F);
                this.attackStep++;
                this.attackTime = 10;
            } else {
                this.attackStep = 0;
                this.attackTime = 120;
            }
        }

        public float getDeltaYaw(float f1,float f2){
            return Math.min(Math.abs(f2-f1),360-Math.abs(f2-f1));
        }
    }
}
